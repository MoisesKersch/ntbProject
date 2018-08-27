package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@SuppressWarnings("serial")
@Entity
@Table(name = "amr_ativacoes")
public class Ativacoes implements Serializable {

	@Id
	@Column(name = "amr_ativacoes_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "c_debt_payment_id")
	private String cDebtPaymentId;

	@Column(name = "dateplanned")
	private Date datePlanned;

	@Column(name = "datapagamento")
	private Date dataPagamento;

	@Column(name = "isreceipt")
	private String isReceipt;

	@Column(name = "ispaid")
	private String isPaid;

	@Column(name = "c_bpartner_id")
	private String cBPartnerId;

	@Column(name = "amr_ativacao_id")
	private String amrAtivacaoId;

	@Column(name = "periodo")
	private String periodo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "isexibeboleto")
	private String isExibeBoleto;

	public Ativacoes() {
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

	public String getcDebtPaymentId() {
		return cDebtPaymentId;
	}

	public void setcDebtPaymentId(String cDebtPaymentId) {
		this.cDebtPaymentId = cDebtPaymentId;
	}

	public Date getDatePlanned() {
		return datePlanned;
	}

	public void setDatePlanned(Date datePlanned) {
		this.datePlanned = datePlanned;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getIsReceipt() {
		return isReceipt;
	}

	public void setIsReceipt(String isReceipt) {
		this.isReceipt = isReceipt;
	}

	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}

	public String getcBPartnerId() {
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId) {
		this.cBPartnerId = cBPartnerId;
	}

	public String getAmrAtivacaoId() {
		return amrAtivacaoId;
	}

	public void setAmrAtivacaoId(String amrAtivacaoId) {
		this.amrAtivacaoId = amrAtivacaoId;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	public String getIsExibeBoleto() {
		return isExibeBoleto;
	}

	public void setIsExibeBoleto(String isExibeBoleto) {
		this.isExibeBoleto = isExibeBoleto;
	}

}