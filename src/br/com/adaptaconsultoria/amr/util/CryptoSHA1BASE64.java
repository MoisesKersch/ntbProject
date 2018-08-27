package br.com.adaptaconsultoria.amr.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CryptoSHA1BASE64 {

	public static String hash(String plaintext) throws Exception {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		}

		try {
			md.update(plaintext.getBytes("UTF-8"));
			// generation
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}

		byte raw[] = md.digest();
		try {
			String hash = new String(org.apache.commons.codec.binary.Base64.encodeBase64(raw), "UTF-8");
			return hash;
		} catch (UnsupportedEncodingException use) {
			throw new Exception(use);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(CryptoSHA1BASE64.hash("senha"));
	}

}