package br.com.adaptaconsultoria.amr.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Criptografia {

	private static SecretKey skey;
	private static KeySpec ks;
	private static PBEParameterSpec ps;
	private static final String algorithm = "PBEWithMD5AndDES";
	private static BASE64Encoder enc = new BASE64Encoder();
	private static BASE64Decoder dec = new BASE64Decoder();

	static {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
			ps = new PBEParameterSpec(new byte[] { 3, 1, 4, 1, 5, 9, 2, 6 }, 20);

			ks = new PBEKeySpec("UdjqHdfak7823ljdf7dsfNJTG/dfshjPoduHdIhHjf86fdjk243Kh345Hdafkl/ads5jH7fjn32hdsfa76bj1167fkja84n8/".toCharArray());

			skey = skf.generateSecret(ks);
		} catch (java.security.NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (java.security.spec.InvalidKeySpecException ex) {
			ex.printStackTrace();
		}
	}

	public static final String encryptText(String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException {

		final Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, skey, ps);
		return enc.encode(cipher.doFinal(text.getBytes()));
	}

	public static final String decryptText(String text) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException,
			NoSuchAlgorithmException, InvalidAlgorithmParameterException {

		final Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, skey, ps);
		String ret = null;
		try {
			ret = new String(cipher.doFinal(dec.decodeBuffer(text)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		String password = "cb@adap014";
		String encoded = Criptografia.encryptText(password);
		System.out.println(encoded);
		System.out.println(Criptografia.decryptText(encoded));
	}

}