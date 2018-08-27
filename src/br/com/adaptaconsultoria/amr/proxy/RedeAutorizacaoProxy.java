package br.com.adaptaconsultoria.amr.proxy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.adaptaconsultoria.amr.springmvc.form.AutorizacaoCartaoForm;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class RedeAutorizacaoProxy {

	private static String URI = "https://ecommerce.userede.com.br/pos_virtual/form_card.asp";

	public String doGet(AutorizacaoCartaoForm form) throws Exception {
		HttpClient httpClient = new HttpClient();

		String parameto = "TOTAL=" + form.getTotal();
		parameto += "&TRANSACAO=" + form.getTransacao();
		parameto += "&PARCELAS=" + form.getParcelas();
		parameto += "&FILIACAO=" + form.getFiliacao();
		parameto += "&DISTRIBUIDOR=" + form.getDistribuidor();
		parameto += "&BANDEIRA=" + form.getBandeira();
		parameto += "&NUMPEDIDO=" + form.getNumPedido();
		parameto += "&PAX1=" + form.getPax1();
		parameto += "&CODVER=" + form.getCodVer();
		parameto += "&URLBACK=" + form.getUrlBack();
		parameto += "&URLCIMA=" + form.getUrlCima();
		parameto += "&TARGET=" + form.getTarget();

		GetMethod getMethod = new GetMethod(URI + "?" + parameto);

		getMethod.setDoAuthentication(true);
		httpClient.executeMethod(getMethod);

		String texto = getMethod.getResponseBodyAsString();
		return texto;
	}

}