package br.com.adaptaconsultoria.amr.test;

import javax.swing.JOptionPane;

import br.com.adaptaconsultoria.amr.builder.EMailBuilder;
import br.com.adaptaconsultoria.amr.model.EMail;
import br.com.adaptaconsultoria.amr.model.ServidorEMail;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class EnviaEmailTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {

			ServidorEMail servidor = EMailBuilder.criaServidorPorProperties();

			EMail email = new EMail();
			email.setServidor(servidor);
			email.setAssunto("Teste");
			email.setMensagem("Teste de mensagem backoffice");
			email.addDestinatario("caralindao@gmail.com");
			email.envia();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + (e.getCause() != null ? "\n" + e.getCause().getMessage() : ""));
		}
	}

}