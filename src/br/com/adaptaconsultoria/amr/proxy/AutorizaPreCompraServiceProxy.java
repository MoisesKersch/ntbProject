package br.com.adaptaconsultoria.amr.proxy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.adaptaconsultoria.amr.model.RetAutorizaPreCompra;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class AutorizaPreCompraServiceProxy 
{

	private static String URI = AMRProperties.getInstance().getPropriedade("autoriza.precompra.service.uri");
	private static String USER = AMRProperties.getInstance().getPropriedade("openbravo.service.user");
	private static String PASSWORD = AMRProperties.getInstance().getPropriedade("openbravo.service.password");

	public RetAutorizaPreCompra doGet(String amrPreCompraId) throws Exception {
		HttpClient httpClient = new HttpClient();

		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWORD));
		httpClient.getParams().setAuthenticationPreemptive(true);

		GetMethod getMethod = new GetMethod(URI + "?" + "adclientid=" + AMRProperties.getInstance().getPropriedade("adclientid") + "&amrprecompraid="
				+ amrPreCompraId);
		
		getMethod.setDoAuthentication(true);
		
		httpClient.executeMethod(getMethod);

		RetAutorizaPreCompra ret = new RetAutorizaPreCompra();
		
		ret.parse(getMethod.getResponseBodyAsString());

		return ret;
	}

}