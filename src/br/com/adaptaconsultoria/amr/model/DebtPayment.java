package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "c_debt_payment")
public class DebtPayment implements Serializable {

	@Id
	@Column(name = "c_debt_payment_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "c_order_id")
	private String cOrderId;

	@Column(name = "c_bpartner_id")
	private String cBPartnerId;

	@Column(name = "description")
	private String descricao;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "em_ac_nrvezes")
	private Integer nVezes;

	@Column(name = "em_ac_nrautorizacao")
	private String nAutorizacao;

	@Column(name = "em_ac_paymentmethod_id")
	private String paymentMethodId;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getnVezes() {
		return nVezes;
	}

	public void setnVezes(Integer nVezes) {
		this.nVezes = nVezes;
	}

	public String getnAutorizacao() {
		return nAutorizacao;
	}

	public void setnAutorizacao(String nAutorizacao) {
		this.nAutorizacao = nAutorizacao;
	}

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

}
