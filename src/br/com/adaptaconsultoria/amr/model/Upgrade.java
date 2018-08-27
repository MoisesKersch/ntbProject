package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;

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
public class Upgrade implements Serializable {

	private String adUserId;
	private List<Produto> produtos;
	private String deliveryviarule = null;
	private String mShipperId = null;
	private String acServicoentregaId = null;
	private BigDecimal valor = null;

	public Upgrade() {
		super();
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
		Document document = Dom4jUtil.createDocument("adesao");
		document.getRootElement().addAttribute("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));
		document.getRootElement().addAttribute("adOrgId", AMRProperties.getInstance().getPropriedade("adorgid"));
		document.getRootElement().addAttribute("adUserId", this.adUserId);

		boolean vazio = true;
		Element eProdutos = Dom4jUtil.createElement("produtos");
		Set<String> enviados = new HashSet<String>();
		for (int i = 0; i < this.produtos.size(); i++) {
			if (this.produtos.get(i).isSelecionado()) {
				if (enviados.contains(produtos.get(i).getId()))
					continue;
				enviados.add(produtos.get(i).getId());
				eProdutos.add(produtos.get(i).toElement());
				vazio = false;
			}
		}
		if (vazio)
			throw new Exception("Nenhum produto selecionado");

		document.getRootElement().add(Dom4jUtil.createElement("deliveryViaRule", this.deliveryviarule));
		document.getRootElement().add(Dom4jUtil.createElement("mShipperId", this.mShipperId));
		document.getRootElement().add(Dom4jUtil.createElement("acServicoEntregaId", this.acServicoentregaId));
		document.getRootElement().add(Dom4jUtil.createElement("valor", this.valor.toString()));
		document.getRootElement().add(eProdutos);

		return document.asXML();
	}

}