package br.com.adaptaconsultoria.amr.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ReceitaFederal {

	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;

		try {
			// String arquivo = "http://www.receita.fazenda.gov.br/aplicacoes/atcta/cpf/consultapublica.asp?error=1&cpf=003.111.222-55&nascimento=15/04/2000";
			String arquivo = "http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva/cnpjreva_solicitacao2.asp?cnpj=92310235000106";
			String destino = "/media/wildson/Dados/Projetos/backoffice-DESIGN/res/pagina.html";

			URL url = new URL(arquivo);
			in = url.openStream();
			out = new FileOutputStream(destino);

			byte[] buffer = new byte[4096];
			int bytes_read;

			while ((bytes_read = in.read(buffer)) != -1)
				out.write(buffer, 0, bytes_read);
		}

		catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
			}
		}
	}

}