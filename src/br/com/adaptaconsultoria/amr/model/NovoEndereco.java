package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "amr_atualizaendereco")
public class NovoEndereco implements Serializable {

	@Id
	@Column(name = "amr_atualizaendereco_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "createdby")
	private String createdby = "100";
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "c_bpartner_location_id")
	private String cBPartnerLocationId;
	@Column(name = "c_location_id")
	private String cLocationId;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "cep")
	private String cep;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "rua")
	private String rua;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "numero")
	private String numero;
	@Column(name = "complemento")
	private String complemento;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "bairro")
	private String bairro;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "pais")
	private String pais = "139";
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "estado")
	private String estado;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "cidade")
	private String cidade;
	@Column(name = "referencia")
	private String referencia;
	@Column(name = "processed")
	private String processed;

	public NovoEndereco() {
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

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getcBPartnerId() {
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId) {
		this.cBPartnerId = cBPartnerId;
	}

	public String getcBPartnerLocationId() {
		return cBPartnerLocationId;
	}

	public void setcBPartnerLocationId(String cBPartnerLocationId) {
		this.cBPartnerLocationId = cBPartnerLocationId;
	}

	public String getcLocationId() {
		return cLocationId;
	}

	public void setcLocationId(String cLocationId) {
		this.cLocationId = cLocationId;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

}