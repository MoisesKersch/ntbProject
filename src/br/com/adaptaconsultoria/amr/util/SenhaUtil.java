package br.com.adaptaconsultoria.amr.util;

import java.util.Random;
import java.util.UUID;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class SenhaUtil {

	public static String criaSenhaCom4Caracteres() throws Exception {
		String senha = UUID.randomUUID().toString().replaceAll("-", "");
		senha = senha.substring(8, 12);
		String novaSenha = "";
		Random random = new Random();
		for (int i = 0; i < senha.length(); i++) {
			if (random.nextBoolean())
				novaSenha += senha.substring(i, i + 1).toUpperCase();
			else
				novaSenha += senha.substring(i, i + 1);
		}
		return novaSenha.toUpperCase();
	}

	public static String criaSenhaCom8Caracteres() throws Exception {
		String senha = UUID.randomUUID().toString().replaceAll("-", "");
		senha = senha.substring(3, 11);
		String novaSenha = "";
		Random random = new Random();
		for (int i = 0; i < senha.length(); i++) {
			if (random.nextBoolean())
				novaSenha += senha.substring(i, i + 1).toUpperCase();
			else
				novaSenha += senha.substring(i, i + 1);
		}
		return novaSenha;
	}

}