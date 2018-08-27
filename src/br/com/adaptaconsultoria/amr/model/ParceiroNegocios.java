package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SuppressWarnings("serial")
@Table(name = "c_bpartner")
public class ParceiroNegocios implements Serializable {

	@Id
	@Column(name = "c_bpartner_id")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "ad_client_id")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "ad_org_id")
	private Organizacao organizacao;
	
	@Column(name = "value")
	private String codigo;
	@Column(name = "name")
	private String nome;
	@Column(name = "taxid")
	private String cnpj;
	@Column(name = "em_ac_data_nascimento")
	private Date dataNascimento;
	@Column(name = "em_af_email")
	private String email;
	@Column(name = "em_ac_celular_tipotelefone")
	private String celularTipoTelefone;
	@Column(name = "em_ac_celular_ddd")
	private String celularDDD;
	@Column(name = "em_ac_celular")
	private String celularNumero;
	@Column(name = "em_ac_celularoperadora_id")
	private String celularOperadora;
	@Column(name = "em_ac_fone_tipotelefone")
	private String outroTipoTelefone;
	@Column(name = "em_ac_fone_ddd")
	private String outroDDD;
	@Column(name = "em_ac_fone")
	private String outroTelefone;
	@Column(name = "em_ac_foneoperadora_id")
	private String outroOperadora;
	@Column(name = "em_amr_banco_id")
	private String bancoID;
	@Column(name = "em_amr_agencia")
	private String agencia;
	@Column(name = "em_amr_conta")
	private String conta;
	@Column(name = "em_amr_conta_dv")
	private String contaDV;
	@Column(name = "em_ac_genero")
	private String genero;
	@Column(name = "bpartner_parent_id")
	private String parent;
	@Column(name = "em_amr_crescimento")
	private String crescimento;
	@Column(name = "em_amr_perfil_id")
	private String perfil;
	@Column(name = "em_amr_bancooperacao")
	private String bancoOperacao;
	@Column(name = "em_amr_nit")
	private String nit;
	@Column(name = "em_amr_isactive")
	private String amrIsactive;

	public ParceiroNegocios() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Organizacao getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelularDDD() {
		return celularDDD;
	}

	public void setCelularDDD(String celularDDD) {
		this.celularDDD = celularDDD;
	}

	public String getCelularNumero() {
		return celularNumero;
	}

	public void setCelularNumero(String celularNumero) {
		this.celularNumero = celularNumero;
	}

	public String getCelularTipoTelefone() {
		return celularTipoTelefone;
	}

	public void setCelularTipoTelefone(String celularTipoTelefone) {
		this.celularTipoTelefone = celularTipoTelefone;
	}

	public String getOutroTipoTelefone() {
		return outroTipoTelefone;
	}

	public void setOutroTipoTelefone(String outroTipoTelefone) {
		this.outroTipoTelefone = outroTipoTelefone;
	}

	public String getOutroDDD() {
		return outroDDD;
	}

	public void setOutroDDD(String outroDDD) {
		this.outroDDD = outroDDD;
	}

	public String getOutroTelefone() {
		return outroTelefone;
	}

	public void setOutroTelefone(String outroTelefone) {
		this.outroTelefone = outroTelefone;
	}

	public String getBancoID() {
		return bancoID;
	}

	public void setBancoID(String bancoID) {
		this.bancoID = bancoID;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getContaDV() {
		return contaDV;
	}

	public void setContaDV(String contaDV) {
		this.contaDV = contaDV;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCelularOperadora() {
		return celularOperadora;
	}

	public void setCelularOperadora(String celularOperadora) {
		this.celularOperadora = celularOperadora;
	}

	public String getOutroOperadora() {
		return outroOperadora;
	}

	public void setOutroOperadora(String outroOperadora) {
		this.outroOperadora = outroOperadora;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getCrescimento() {
		return crescimento;
	}

	public void setCrescimento(String crescimento) {
		this.crescimento = crescimento;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getBancoOperacao() {
		return bancoOperacao;
	}

	public void setBancoOperacao(String bancoOperacao) {
		this.bancoOperacao = bancoOperacao;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getAmrIsactive() {
		return amrIsactive;
	}

	public void setAmrIsactive(String amrIsactive) {
		this.amrIsactive = amrIsactive;
	}

}