package br.com.adaptaconsultoria.amr.util.lang;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class TextoUtil {

	public static String removeAcentos(String texto) throws Exception {
		texto = texto.replaceAll("[ÂÀÁÄÃ]", "A");
		texto = texto.replaceAll("[âãàáä]", "a");
		texto = texto.replaceAll("[ÊÈÉË]", "E");
		texto = texto.replaceAll("[êèéë]", "e");
		texto = texto.replaceAll("ÎÍÌÏ", "I");
		texto = texto.replaceAll("îíìï", "i");
		texto = texto.replaceAll("[ÔÕÒÓÖ]", "O");
		texto = texto.replaceAll("[ôõòóö]", "o");
		texto = texto.replaceAll("[ÛÙÚÜ]", "U");
		texto = texto.replaceAll("[ûúùü]", "u");
		texto = texto.replaceAll("Ç", "C");
		texto = texto.replaceAll("ç", "c");
		texto = texto.replaceAll("[ýÿ]", "y");
		texto = texto.replaceAll("Ý", "Y");
		texto = texto.replaceAll("ñ", "n");
		texto = texto.replaceAll("Ñ", "N");
		texto = texto.replaceAll("['<>\\|/!@#$%*]", "");
		return texto;
	}

	public static String removeNaoNumericos(String texto, char... caracteresIgnorados) {

		if (texto == null) {
			return null;
		}

		StringBuffer strBuff = new StringBuffer();
		char c;

		for (int i = 0; i < texto.length(); i++) {
			c = texto.charAt(i);

			if (Character.isDigit(c))
				strBuff.append(c);
			else {
				for (int j = 0; j < caracteresIgnorados.length; j++) {
					if (caracteresIgnorados[j] == c)
						strBuff.append(c);
				}
			}
		}
		return strBuff.toString();
	}

}