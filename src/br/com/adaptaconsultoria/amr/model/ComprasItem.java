package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "amr_compras_item")
public class ComprasItem implements Serializable {

	@Id
	@Column(name = "amr_compras_item_id")
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

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "lancamento")
	private Date lancamento;

	@Column(name = "fatura")
	private String fatura;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "quitacao")
	private Date quitacao;

	@Column(name = "dias")
	private Integer dias;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "item")
	private String item;

	@Column(name = "unitario")
	private BigDecimal unitario;

	@Column(name = "quantidade")
	private BigDecimal quantidade;

	@Column(name = "total")
	private BigDecimal total;

	public ComprasItem() {
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

	public Date getLancamento() {
		return lancamento;
	}

	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}

	public String getFatura() {
		return fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	public Date getQuitacao() {
		return quitacao;
	}

	public void setQuitacao(Date quitacao) {
		this.quitacao = quitacao;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public BigDecimal getUnitario() {
		return unitario;
	}

	public void setUnitario(BigDecimal unitario) {
		this.unitario = unitario;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}