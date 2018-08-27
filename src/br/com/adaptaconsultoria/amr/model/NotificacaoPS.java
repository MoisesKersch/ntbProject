package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "aps_notificacao")
public class NotificacaoPS implements Serializable {

	@Id
	@Column(name = "aps_notificacao_id")
	private String id;

	@NotNull
	@Column(name = "ad_client_id")
	private String adClientId;

	@NotNull
	@Column(name = "ad_org_id")
	private String adOrgId;

	@NotNull
	@Column(name = "isactive")
	private String isAtivo = "Y";

	@NotNull
	@Column(name = "created")
	private Date created = new Date();

	@Column(name = "createdby")
	private String createdBy;

	@NotNull
	@Column(name = "updated")
	private Date updated = new Date();

	@Column(name = "updatedby")
	private String updatedBy;

	@NotNull
	@Column(name = "c_debt_payment_id")
	private String debtPaymentId;

	@NotNull
	@Column(name = "notificacao")
	private String notificacao;

	@NotNull
	@Column(name = "status")
	private Integer status;

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

	public String getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(String isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDebtPaymentId() {
		return debtPaymentId;
	}

	public void setDebtPaymentId(String debtPaymentId) {
		this.debtPaymentId = debtPaymentId;
	}

	public String getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(String notificacao) {
		this.notificacao = notificacao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
