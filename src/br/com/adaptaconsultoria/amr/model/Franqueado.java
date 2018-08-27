package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "amr_franqueado")
public class Franqueado implements Serializable {

	@Id
	@Column(name = "amr_franqueado_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "login")
	private String login;
	@Column(name = "value")
	private String codigo;
	@Column(name = "name")
	private String nome;
	@Column(name = "libera_crescimento")
	private String liberaCrescimento;
	@Column(name = "patrocinador_user_id")
	private String patrocinadorId;
	@Column(name = "url")
	private String url;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "libera_saque")
	private String liberaSaque;
	@Column(name = "pc_binario")
	private String percentualBinario;
	@Column(name = "perfil")
	private String perfil;
	@Column(name = "autentica_compra")
	private String autenticaCompra;
	@Column(name = "icone_desktop")
	private String iconeDesktop;

	public Franqueado() {
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

	public String getLiberaCrescimento() {
		return liberaCrescimento;
	}

	public void setLiberaCrescimento(String liberaCrescimento) {
		this.liberaCrescimento = liberaCrescimento;
	}

	public String getPatrocinadorId() {
		return patrocinadorId;
	}

	public void setPatrocinadorId(String patrocinadorId) {
		this.patrocinadorId = patrocinadorId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLiberaSaque() {
		return liberaSaque;
	}

	public void setLiberaSaque(String liberaSaque) {
		this.liberaSaque = liberaSaque;
	}

	public String getPercentualBinario() {
		return percentualBinario;
	}

	public void setPercentualBinario(String percentualBinario) {
		this.percentualBinario = percentualBinario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getAutenticaCompra() {
		return autenticaCompra;
	}

	public void setAutenticaCompra(String autenticaCompra) {
		this.autenticaCompra = autenticaCompra;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getIconeDesktop() {
		return iconeDesktop;
	}

	public void setIconeDesktop(String iconeDesktop) {
		this.iconeDesktop = iconeDesktop;
	}

}