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
@Table(name = "amr_meuspontos")
public class MeusPontos implements Serializable {

	@Id
	@Column(name = "amr_meuspontos_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "amr_binario_id")
	private String amrBinarioId;
	
	@Column(name = "saldo_anterior_esquerda")
	private String saldoAnteriorEsquerda;
	@Column(name = "saldo_esquerda")
	private String saldoEsquerda;
	@Column(name = "equipe_esquerda")
	private String equipeEsquerda;
	@Column(name = "saldo_anterior_direita")
	private String saldoAnteriorDireita;
	@Column(name = "saldo_direita")
	private String saldoDireita;
	@Column(name = "equipe_direita")
	private String equipeDireita;

	public MeusPontos() {
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

	public String getAmrBinarioId() {
		return amrBinarioId;
	}

	public void setAmrBinarioId(String amrBinarioId) {
		this.amrBinarioId = amrBinarioId;
	}

	public String getSaldoAnteriorEsquerda() {
		return saldoAnteriorEsquerda;
	}

	public void setSaldoAnteriorEsquerda(String saldoAnteriorEsquerda) {
		this.saldoAnteriorEsquerda = saldoAnteriorEsquerda;
	}

	public String getSaldoEsquerda() {
		return saldoEsquerda;
	}

	public void setSaldoEsquerda(String saldoEsquerda) {
		this.saldoEsquerda = saldoEsquerda;
	}

	public String getEquipeEsquerda() {
		return equipeEsquerda;
	}

	public void setEquipeEsquerda(String equipeEsquerda) {
		this.equipeEsquerda = equipeEsquerda;
	}

	public String getSaldoAnteriorDireita() {
		return saldoAnteriorDireita;
	}

	public void setSaldoAnteriorDireita(String saldoAnteriorDireita) {
		this.saldoAnteriorDireita = saldoAnteriorDireita;
	}

	public String getSaldoDireita() {
		return saldoDireita;
	}

	public void setSaldoDireita(String saldoDireita) {
		this.saldoDireita = saldoDireita;
	}

	public String getEquipeDireita() {
		return equipeDireita;
	}

	public void setEquipeDireita(String equipeDireita) {
		this.equipeDireita = equipeDireita;
	}

}