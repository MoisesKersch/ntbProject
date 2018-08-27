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
@Table(name = "amr_redeconfig")
public class RedeConfig implements Serializable {

	@Id
	@Column(name = "ad_org_id")
	private String id;
	@Column(name = "filiacao")
	private String filiacao;
	@Column(name = "tipotransacaovista")
	private String tipoTransacaoAVista;
	@Column(name = "tipotransacaoparcelada")
	private String tipoTransacaoParcelada;
	@Column(name = "distribuidor")
	private String distribuidor;
	@Column(name = "codver")
	private String codVer;
	@Column(name = "urlback")
	private String urlBack;
	@Column(name = "urlcima")
	private String urlCima;
	@Column(name = "target")
	private String target;

	public RedeConfig() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFiliacao() {
		return filiacao;
	}

	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
	}

	public String getTipoTransacaoAVista() {
		return tipoTransacaoAVista;
	}

	public void setTipoTransacaoAVista(String tipoTransacaoAVista) {
		this.tipoTransacaoAVista = tipoTransacaoAVista;
	}

	public String getTipoTransacaoParcelada() {
		return tipoTransacaoParcelada;
	}

	public void setTipoTransacaoParcelada(String tipoTransacaoParcelada) {
		this.tipoTransacaoParcelada = tipoTransacaoParcelada;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getCodVer() {
		return codVer;
	}

	public void setCodVer(String codVer) {
		this.codVer = codVer;
	}

	public String getUrlBack() {
		return urlBack;
	}

	public void setUrlBack(String urlBack) {
		this.urlBack = urlBack;
	}

	public String getUrlCima() {
		return urlCima;
	}

	public void setUrlCima(String urlCima) {
		this.urlCima = urlCima;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}