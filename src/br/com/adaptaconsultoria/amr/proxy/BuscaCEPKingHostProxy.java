package br.com.adaptaconsultoria.amr.proxy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.adaptaconsultoria.amr.model.Cidade;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class BuscaCEPKingHostProxy {

	private static String URI = "http://webservice.kinghost.net/web_cep.php";

	public Cidade doGet(String cep) throws Exception {
		HttpClient httpClient = new HttpClient();

		httpClient.getParams().setAuthenticationPreemptive(false);

		GetMethod getMethod = new GetMethod(URI + "?" + "auth=1e3e4a661394080bb02abe134a8fcd24&formato=xml&cep=" + cep);
		getMethod.setDoAuthentication(true);
		httpClient.executeMethod(getMethod);

		Cidade cep0 = new Cidade();
		cep0.parse(getMethod.getResponseBodyAsString());
		return cep0;
	}

}