package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import org.dom4j.Document;

import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.io.Dom4jUtil;

@SuppressWarnings("serial")
public class EnvAtualizaBoleto implements Serializable {

	private String cDebtPaymentId;

	public EnvAtualizaBoleto() {
		super();
	}

	public String getcDebtPaymentId() {
		return cDebtPaymentId;
	}

	public void setcDebtPaymentId(String cDebtPaymentId) {
		this.cDebtPaymentId = cDebtPaymentId;
	}

	public String toXML() throws Exception {
		Document document = Dom4jUtil.createDocument("envAtualizaBoleto");
		document.getRootElement().add(Dom4jUtil.createElement("adClientId", AMRProperties.getInstance().getPropriedade("adclientid")));
		document.getRootElement().add(Dom4jUtil.createElement("adOrgId", AMRProperties.getInstance().getPropriedade("adorgid")));
		document.getRootElement().add(Dom4jUtil.createElement("cDebtPaymentId", this.cDebtPaymentId));
		document.getRootElement().add(Dom4jUtil.createElement("dataVencimento", ""));
		return document.asXML();
	}

}