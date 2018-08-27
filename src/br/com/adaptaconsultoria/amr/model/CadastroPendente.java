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
@Table(name = "amr_cadastropendente")
public class CadastroPendente implements Serializable {

	@Id
	@Column(name = "amr_cadastropendente_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "direto_id")
	private String direto;
	@Column(name = "login")
	private String login;
	@Column(name = "direto_nome")
	private String diretoNome;
	@Column(name = "direto_cpf")
	private String diretoCpf;
	@Column(name = "perfil")
	private String perfil;
	@Column(name = "cliente_desde")
	private String clienteDesde;
	@Column(name = "cidade")
	private String cidade;

	public CadastroPendente() {
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

	public String getDireto() {
		return direto;
	}

	public void setDireto(String direto) {
		this.direto = direto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDiretoNome() {
		return diretoNome;
	}

	public void setDiretoNome(String diretoNome) {
		this.diretoNome = diretoNome;
	}

	public String getDiretoCpf() {
		return diretoCpf;
	}

	public void setDiretoCpf(String diretoCpf) {
		this.diretoCpf = diretoCpf;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getClienteDesde() {
		return clienteDesde;
	}

	public void setClienteDesde(String clienteDesde) {
		this.clienteDesde = clienteDesde;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}