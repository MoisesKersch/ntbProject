package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "amr_saque")
public class Saque implements Serializable {

	@Id
	@Column(name = "amr_saque_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "createdby")
	private String createdby = "100";
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@NumberFormat(style = Style.CURRENCY, pattern = "##,###,###.##")
	@NotNull(message = "{campo.obrigatorio}")
	@Column(name = "valor")
	private BigDecimal valor;
	@Column(name = "status")
	private String status = "PE";
	@Column(name = "observacoes")
	private String observacoes;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Transient
	private String senhaFinanceira;

	public Saque() {
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getSenhaFinanceira() {
		return senhaFinanceira;
	}

	public void setSenhaFinanceira(String senhaFinanceira) {
		this.senhaFinanceira = senhaFinanceira;
	}

}