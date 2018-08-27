package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Entity
@Table(name = "amr_compras")
@SuppressWarnings("serial")
public class Compras implements Serializable {

	@Id
	@Column(name = "amr_compras_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "c_order_id")
	private String cOrderId;

	@Column(name = "c_bpartner_id")
	private String cBPartnerId;

	@Column(name = "ad_user_id")
	private String adUserId;

	@Column(name = "pedido")
	private String pedido;

	@Column(name = "lancamento")
	private String lancamento;

	@Column(name = "fatura")
	private String fatura;

	@Column(name = "quitacao")
	private String quitacao;

	@Column(name = "dias")
	private Integer dias;

	@Column(name = "itens")
	private String itens;

	@Column(name = "frete")
	private String frete;

//	@Column(name = "codigo")
//	private String codigo;
//	@Column(name = "item")
//	private String item;
//	@Column(name = "unitario")
//	private BigDecimal unitario;
//	@Column(name = "quantidade")
//	private BigDecimal quantidade;

	@Column(name = "total")
	private String total;

	public Compras() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(String adClientId) {
		this.adClientId = adClientId;
	}

	public String getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(String adOrgId) {
		this.adOrgId = adOrgId;
	}

	public String getcOrderId() {
		return cOrderId;
	}

	public void setcOrderId(String cOrderId) {
		this.cOrderId = cOrderId;
	}

	public String getcBPartnerId() {
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId) {
		this.cBPartnerId = cBPartnerId;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getLancamento() {
		return lancamento;
	}

	public void setLancamento(String lancamento) {
		this.lancamento = lancamento;
	}

	public String getFatura() {
		return fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	public String getQuitacao() {
		return quitacao;
	}

	public void setQuitacao(String quitacao) {
		this.quitacao = quitacao;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getItens() {
		return itens;
	}

	public void setItens(String itens) {
		this.itens = itens;
	}

	public String getFrete() {
		return frete;
	}

	public void setFrete(String frete) {
		this.frete = frete;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}