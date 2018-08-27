package br.com.adaptaconsultoria.amr.test;

import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class Teste {

	public static void main(String[] args) throws Exception {
		String texto = "NUTRAC%C3%8AUTICOS";

		String decodedResponse = URLDecoder.decode(texto, "UTF-8");
		System.out.println(decodedResponse);

		String[] encode = new String[] { "UTF-8", "UTF-16", "UTF-32", "GB18030", "ISO-8859-1", "ASCII", "UNICODE" };

		for (int i = 0; i < encode.length; i++) {
			Charset utf8charset = Charset.forName(encode[i]);
			Charset iso88591charset = Charset.forName("ISO-8859-1");

			ByteBuffer inputBuffer = ByteBuffer.wrap(texto.getBytes());

			// decode UTF-8
			CharBuffer data = utf8charset.decode(inputBuffer);
			System.out.println(data.toString());

			// encode ISO-8559-1
			ByteBuffer outputBuffer = iso88591charset.encode(data);
			byte[] outputData = outputBuffer.array();

			System.out.println(new String(outputData));
		}

	}

}