package br.com.adaptaconsultoria.amr.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.mozilla.universalchardet.UniversalDetector;

/**
 *
 * @author Will Zaniol
 * @version 1
 *
 */
public class EncodeUtil {

	public static String getEncodeFromFile(String fileName) throws Exception {
		byte[] buf = new byte[4096];
		java.io.FileInputStream fis = new java.io.FileInputStream(fileName);

		UniversalDetector detector = new UniversalDetector(null);

		int nread;
		while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}
		fis.close();

		detector.dataEnd();

		String encoding = detector.getDetectedCharset();

		detector.reset();

		return encoding;
	}

	public static File convertFile(String fileName, String encType) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encType));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName + encType), "UTF-8"));
		for (String linha = input.readLine(); linha != null; linha = input.readLine()) {
			writer.write(linha);
			writer.newLine();
		}
		writer.close();
		input.close();
		return new File(fileName + encType);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String fileName = "/home/adapta/projetos/movex/modules/br.com.adaptaconsultoria.core/res/produto-ANSI.csv";
		System.out.println(EncodeUtil.getEncodeFromFile(fileName));

		System.out.println(EncodeUtil.convertFile(fileName, "UTF-8"));
	}

}