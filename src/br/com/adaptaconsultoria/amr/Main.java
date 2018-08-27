package br.com.adaptaconsultoria.amr;

import br.com.adaptaconsultoria.amr.util.FormatUtilities;

/**
 * 
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		System.out.println(FormatUtilities.sha1Base64("fatima2016"));
		
		/*
		String uri = "/BackOffice/lista_paises;jsessionid=3E6B4C1378D89B1DDBA8689BDCC3A232";
		System.out.println(uri);
		int x1 = uri.indexOf(";jsessionid");

		int x2 = uri.length();
		if (uri.contains("?"))
			x2 = uri.indexOf("?");

		uri = uri.replace(uri.substring(x1, x2), "");
		System.out.println(uri);
		*/
		
	}

}