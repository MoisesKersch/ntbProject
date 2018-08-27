package br.com.adaptaconsultoria.amr.proxy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.adaptaconsultoria.amr.model.RetProdutosDisponiveis;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class ListaProdutosDisponiveisServiceProxy {

	private static String URI = AMRProperties.getInstance().getPropriedade("lista.produtos.disponiveis.service.uri");
	private static String USER = AMRProperties.getInstance().getPropriedade("openbravo.service.user");
	private static String PASSWORD = AMRProperties.getInstance().getPropriedade("openbravo.service.password");

	public RetProdutosDisponiveis doGet() throws Exception {
		HttpClient httpClient = new HttpClient();

		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWORD));
		httpClient.getParams().setAuthenticationPreemptive(true);

		GetMethod getMethod = new GetMethod(URI + "?" + "adclientid=" + AMRProperties.getInstance().getPropriedade("adclientid"));
		getMethod.setDoAuthentication(true);
		httpClient.executeMethod(getMethod);

		System.out.println(getMethod.getResponseBodyAsString());

		RetProdutosDisponiveis ret = new RetProdutosDisponiveis();
		ret.parse(getMethod.getResponseBodyAsString());

		return ret;
	}

	public static void main(String[] args) throws Exception {
		ListaProdutosDisponiveisServiceProxy proxy = new ListaProdutosDisponiveisServiceProxy();
		proxy.doGet();
	}

}