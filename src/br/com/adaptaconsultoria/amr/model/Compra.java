package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.dom4j.Document;

import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.io.Dom4jUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Compra implements Serializable {

	private String adUserId = null;
	private String mProductId = null;
	private String deliveryviarule = null;
	private String mShipperId = null;
	private String acServicoentregaId = null;
	private BigDecimal valor = null;

	public Compra() {
		super();
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getmProductId() {
		return mProductId;
	}

	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}

	public String getDeliveryviarule() {
		return deliveryviarule;
	}

	public void setDeliveryviarule(String deliveryviarule) {
		this.deliveryviarule = deliveryviarule;
	}

	public String getmShipperId() {
		return mShipperId;
	}

	public void setmShipperId(String mShipperId) {
		this.mShipperId = mShipperId;
	}

	public String getAcServicoentregaId() {
		return acServicoentregaId;
	}

	public void setAcServicoentregaId(String acServicoentregaId) {
		this.acServicoentregaId = acServicoentregaId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String toXML() throws Exception {
		Document document = Dom4jUtil.createDocument("envCompra");
		document.getRootElement().addAttribute("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));
		document.getRootElement().addAttribute("adOrgId", AMRProperties.getInstance().getPropriedade("adorgid"));

		document.getRootElement().add(Dom4jUtil.createElement("adUserId", this.adUserId));
		document.getRootElement().add(Dom4jUtil.createElement("mProductId", this.mProductId));
		document.getRootElement().add(Dom4jUtil.createElement("deliveryViaRule", this.deliveryviarule));
		document.getRootElement().add(Dom4jUtil.createElement("mShipperId", this.mShipperId));
		document.getRootElement().add(Dom4jUtil.createElement("acServicoEntregaId", this.acServicoentregaId));
		document.getRootElement().add(Dom4jUtil.createElement("valor", this.valor.toString()));

		document.getRootElement().add(Dom4jUtil.createElement("quantidade", "1"));

		return document.asXML();
	}

}