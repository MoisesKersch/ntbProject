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
@Table(name = "amr_transfere")
public class Transferencia implements Serializable {

	@Id
	@Column(name = "amr_transfere_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "createdby")
	private String createdby = "100";
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "debito_user_id")
	private String adUserIdDebito;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "credito_user_id")
	private String adUserIdCredito;
	@NumberFormat(style = Style.CURRENCY, pattern = "##,###,###.##")
	@NotNull(message = "{campo.obrigatorio}")
	private BigDecimal valor;
	private BigDecimal taxa;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Transient
	private String senhaFinanceira;

	public Transferencia() {
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

	public String getAdUserIdDebito() {
		return adUserIdDebito;
	}

	public void setAdUserIdDebito(String adUserIdDebito) {
		this.adUserIdDebito = adUserIdDebito;
	}

	public String getAdUserIdCredito() {
		return adUserIdCredito;
	}

	public void setAdUserIdCredito(String adUserIdCredito) {
		this.adUserIdCredito = adUserIdCredito;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public String getSenhaFinanceira() {
		return senhaFinanceira;
	}

	public void setSenhaFinanceira(String senhaFinanceira) {
		this.senhaFinanceira = senhaFinanceira;
	}

}