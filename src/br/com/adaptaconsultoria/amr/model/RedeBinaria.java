package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "amr_redebinaria")
public class RedeBinaria implements Serializable {

	@Id
	@Column(name = "amr_redebinaria_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "c_bpartner_id")
	private String cBpartnerId;
	@Column(name = "name")
	private String name;
	@Column(name = "username")
	private String username;
	@Column(name = "esquerda_id")
	private String esquerda;
	@Column(name = "direita_id")
	private String direita;
	@Column(name = "tooltip")
	private String tooltip;
	@Column(name = "perfil")
	private String perfil;
	@Column(name = "icone")
	private String icone;
	@Transient
	private String pai;
	@Column(name = "codigo_parceiro")
	private String codigoParceiroNegocios;
	@Transient
	private boolean ultimoNivel = false;

	public RedeBinaria() {
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

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getcBpartnerId() {
		return cBpartnerId;
	}

	public void setcBpartnerId(String cBpartnerId) {
		this.cBpartnerId = cBpartnerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(String esquerda) {
		this.esquerda = esquerda;
	}

	public String getDireita() {
		return direita;
	}

	public void setDireita(String direita) {
		this.direita = direita;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getCodigoParceiroNegocios() {
		return codigoParceiroNegocios;
	}

	public void setCodigoParceiroNegocios(String codigoParceiroNegocios) {
		this.codigoParceiroNegocios = codigoParceiroNegocios;
	}

	public boolean isUltimoNivel() {
		return ultimoNivel;
	}

	public void setUltimoNivel(boolean ultimoNivel) {
		this.ultimoNivel = ultimoNivel;
	}

}