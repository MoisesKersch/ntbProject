package br.com.adaptaconsultoria.amr.proxy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.tools.ant.filters.StringInputStream;

import br.com.adaptaconsultoria.amr.model.EnvAtualizaBoleto;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class AtualizaBoletoProxy {

	private static String URI = AMRProperties.getInstance().getPropriedade("atualiza.boleto.service.uri");
	private static String USER = AMRProperties.getInstance().getPropriedade("openbravo.service.user");
	private static String PASSWORD = AMRProperties.getInstance().getPropriedade("openbravo.service.password");

	public String doPost(String cDebtPaymentId) throws Exception {
		HttpClient httpClient = new HttpClient();

		// httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, Criptografia.decrypt(PASSWORD)));
		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWORD));
		httpClient.getParams().setAuthenticationPreemptive(true);

		PostMethod postMethod = new PostMethod(URI);
		postMethod.setDoAuthentication(true);

		EnvAtualizaBoleto env = new EnvAtualizaBoleto();
		env.setcDebtPaymentId(cDebtPaymentId);
		String xml = env.toXML();
		InputStreamRequestEntity inp = new InputStreamRequestEntity(new StringInputStream(xml, "UTF-8"));
		postMethod.setRequestEntity(inp);

		httpClient.executeMethod(postMethod);

		return postMethod.getResponseBodyAsString();
	}
}