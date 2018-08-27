/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adaptaconsultoria.amr.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class FileUtil {

	public static void save(String text, String fileName) {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			BufferedWriter out = new BufferedWriter(pw);
			out.write(text);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(InputStream inputStream, String fileName) throws IOException {
		OutputStream out = new FileOutputStream(fileName);
		byte buf[] = new byte[1024];
		int len;
		while ((len = inputStream.read(buf)) > 0)
			out.write(buf, 0, len);
		out.close();
	}

	@SuppressWarnings("resource")
	public static String toString(String fileName) {
		String text = "";
		try {
			FileReader file = new FileReader(new File(fileName));
			BufferedReader reader = new BufferedReader(file);

			for (String linha = reader.readLine(); linha != null; linha = reader.readLine()) {
				text += linha + "\n";
			}
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado\n(" + e + ")");
		}
		return text;
	}

	@SuppressWarnings("resource")
	public static List<String> toStringList(String fileName) {
		List<String> list = new ArrayList<String>();
		try {
			FileReader file = new FileReader(new File(fileName));
			BufferedReader reader = new BufferedReader(file);

			for (String linha = reader.readLine(); linha != null; linha = reader.readLine()) {
				list.add(linha);
			}
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado\n(" + e + ")");
		}
		return list;
	}

	public static List<String> toStringListWithEncode(String fileName) throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader inputReader = null;
		try {

			String encType = EncodeUtil.getEncodeFromFile(fileName);

			inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encType));

			for (String linha = inputReader.readLine(); linha != null; linha = inputReader.readLine()) {
				list.add(linha);
			}
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado\n(" + e + ")");
		} finally {
			inputReader.close();
		}
		return list;
	}

	public static String getExtensao(String arquivo) throws Exception {
		int index = arquivo.lastIndexOf('.');
		return arquivo.substring(index + 1, arquivo.length());
	}
	
	public static void copy(String src, String dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst); // Transferindo bytes de
		// entrada para saída
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
	
	public static void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst); // Transferindo bytes de
		// entrada para saída
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}


}