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
@Table(name = "aps_paymentmethod")
public class MetodoPagamentoPS implements Serializable {

	@Id
	@Column(name = "aps_paymentmethod_id")
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

	@NotNull
	@Column(name = "createdby")
	private String createdBy;

	@NotNull
	@Column(name = "updated")
	private Date updated = new Date();

	@NotNull
	@Column(name = "updatedby")
	private String updatedBy;

	@NotNull
	@Column(name = "paymentmethod")
	private Integer paymentMethod;

	@NotNull
	@Column(name = "ac_paymentmethod_id")
	private String acPaymentMethodId;

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

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAcPaymentMethodId() {
		return acPaymentMethodId;
	}

	public void setAcPaymentMethodId(String acPaymentMethodId) {
		this.acPaymentMethodId = acPaymentMethodId;
	}

}
