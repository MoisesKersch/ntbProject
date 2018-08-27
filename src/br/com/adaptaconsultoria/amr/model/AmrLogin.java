package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

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
@Entity
@SuppressWarnings("serial")
@Table(name = "amr_login")
public class AmrLogin implements Serializable {

	@Id
	@Column(name = "amr_login_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "name")
	private String nome;

	@Column(name = "ad_user_id")
	private String adUserId;

	@Column(name = "c_bpartner_id")
	private String cbPartnerId;

	@Column(name = "bpartner_parent_id")
	private String cbPartnerParentId;

	@Column(name = "franqueado")
	private String franqueado;

	@Column(name = "username")
	private String username;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getCbPartnerId() {
		return cbPartnerId;
	}

	public void setCbPartnerId(String cbPartnerId) {
		this.cbPartnerId = cbPartnerId;
	}

	public String getCbPartnerParentId() {
		return cbPartnerParentId;
	}

	public void setCbPartnerParentId(String cbPartnerParentId) {
		this.cbPartnerParentId = cbPartnerParentId;
	}

	public String getFranqueado() {
		return franqueado;
	}

	public void setFranqueado(String franqueado) {
		this.franqueado = franqueado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}