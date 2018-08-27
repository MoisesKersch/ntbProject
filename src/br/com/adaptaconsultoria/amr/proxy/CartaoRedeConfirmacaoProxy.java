package br.com.adaptaconsultoria.amr.proxy;

import java.text.DecimalFormat;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.RedeConfig;
import br.com.adaptaconsultoria.amr.model.RetRedeCardConfirma;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class CartaoRedeConfirmacaoProxy {

	private static String URI = AMRProperties.getInstance().getPropriedade("rede.card.service.uri");

	public RetRedeCardConfirma doGet(RedeConfig config, PreCompra preCompra) throws Exception {
		HttpClient httpClient = new HttpClient();

		String data = preCompra.getData();
		String transacao = "203";
		String transorig = preCompra.getTransacao();
		String parcelas = preCompra.getParcelas();
		String filiacao = config.getFiliacao();
		String distribuidor = config.getDistribuidor();
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		String total = decimalFormat.format(preCompra.getTotal()).replaceAll(",", ".");
		String numpedido = preCompra.getNumpedido();
		String numautor = preCompra.getNumautor();
		String numcv = preCompra.getNumcv();
		String numsqn = preCompra.getNumsqn();

		String url = URI + "?";
		url += "DATA=" + data;
		url += "&TRANSACAO=" + transacao;
		url += "&TRANSORIG=" + transorig;
		url += "&PARCELAS=" + parcelas;
		url += "&FILIACAO=" + filiacao;
		url += "&DISTRIBUIDOR=" + distribuidor;
		url += "&TOTAL=" + total;
		url += "&NUMPEDIDO=" + numpedido;
		url += "&NUMAUTOR=" + numautor;
		url += "&NUMCV=" + numcv;
		url += "&NUMSQN=" + numsqn;

		GetMethod getMethod = new GetMethod(url);
		getMethod.setDoAuthentication(true);
		httpClient.executeMethod(getMethod);

		RetRedeCardConfirma ret = new RetRedeCardConfirma();
		ret.parse(getMethod.getResponseBodyAsString());

		return ret;
	}

}