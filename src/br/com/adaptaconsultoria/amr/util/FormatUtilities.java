package br.com.adaptaconsultoria.amr.util;

public class FormatUtilities {

	public static String sha1Base64(String text) throws Exception {
		if (text == null || text.trim().equals(""))
			return "";
		String result = text;
		result = CryptoSHA1BASE64.hash(text);
		return result;
	}

}