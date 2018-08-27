package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "amr_precomprapn")
public class PreCompraCliente implements Serializable {

	@Id
	@Column(name = "amr_precomprapn_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "createdby")
	private String createdby = "100";
	@Column(name = "amr_precompra_id")
	private String preCompraId;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "cpfcnpj")
	private String cpfCnpj;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "name")
	private String nome;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "datanascimento")
	private String dataNascimento;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "genero")
	private String genero = "M";
	@Column(name = "tipopessoa")
	private String tipoPessoa = "F";
	@Column(name = "email")
	private String email;
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
	private String estado = "422";
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	@Column(name = "cidade")
	private String cidade = "3B0AA478F1B14A559A8D296C81066C0C";
	@Column(name = "referencia")
	private String referencia;
	@NotNull
	@NotEmpty(message = "*")
	@Column(name = "dddtelefone")
	private String dddTelefone;
	@NotNull
	@NotEmpty(message = "*")
	@Column(name = "numerotelefone")
	private String numeroTelefone;
	@Column(name = "operadoratelefone")
	private String operadoraTelefone;
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "c_bpartner_location_id")
	private String cBPartnerLocationId;
	@Column(name = "c_location_id")
	private String cLocationId;
	@Transient
	private boolean readOnly;
	@Transient
	private boolean vendaDireta;

	public PreCompraCliente() {
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

	public String getPreCompraId() {
		return preCompraId;
	}

	public void setPreCompraId(String preCompraId) {
		this.preCompraId = preCompraId;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getOperadoraTelefone() {
		return operadoraTelefone;
	}

	public void setOperadoraTelefone(String operadoraTelefone) {
		this.operadoraTelefone = operadoraTelefone;
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

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isVendaDireta() {
		return vendaDireta;
	}

	public void setVendaDireta(boolean vendaDireta) {
		this.vendaDireta = vendaDireta;
	}

}