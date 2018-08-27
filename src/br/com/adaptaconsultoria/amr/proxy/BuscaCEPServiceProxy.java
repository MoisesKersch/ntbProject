package br.com.adaptaconsultoria.amr.proxy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.adaptaconsultoria.amr.model.RetBuscaCEP;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class BuscaCEPServiceProxy {

	private static String URI = AMRProperties.getInstance().getPropriedade("busca.cep.service.uri");
	private static String USER = AMRProperties.getInstance().getPropriedade("openbravo.service.user");
	private static String PASSWORD = AMRProperties.getInstance().getPropriedade("openbravo.service.password");

	public RetBuscaCEP doGet(String cep) throws Exception {
		HttpClient httpClient = new HttpClient();

		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWORD));
		httpClient.getParams().setAuthenticationPreemptive(true);

		GetMethod getMethod = new GetMethod(URI + "?" + "adclientid=" + AMRProperties.getInstance().getPropriedade("adclientid") + "&adorgid="
				+ AMRProperties.getInstance().getPropriedade("adorgid") + "&cep=" + cep);
		getMethod.setDoAuthentication(true);
		httpClient.executeMethod(getMethod);

		System.out.println(URI + "?" + "adclientid=" + AMRProperties.getInstance().getPropriedade("adclientid") + "&adorgid="
				+ AMRProperties.getInstance().getPropriedade("adorgid") + "&cep=" + cep);

		System.out.println(getMethod.getResponseBodyAsString());

		RetBuscaCEP ret = new RetBuscaCEP();
		ret.parse(getMethod.getResponseBodyAsString());

		return ret;
	}

	public static void main(String[] args) throws Exception {
		BuscaCEPServiceProxy proxy = new BuscaCEPServiceProxy();
		proxy.doGet("89812120");
	}

}