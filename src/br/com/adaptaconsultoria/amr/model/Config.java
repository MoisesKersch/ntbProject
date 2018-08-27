package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Entity
@SuppressWarnings("serial")
@Table(name = "amr_config")
public class Config implements Serializable {

	@Id
	@Column(name = "amr_config_id")
	private String id;

	@NotEmpty
	@Column(name = "ad_client_id")
	private String adClientId;

	@NotEmpty
	@Column(name = "ad_org_id")
	private String adOrgId;

	@NotEmpty
	@Column(name = "isactive")
	private String isActive = "Y";

	@Column(name = "amr_plano_id")
	private String amrPlanoId;

	@NotEmpty
	@Column(name = "quempaga_transferencia")
	private String quemPagaTransferencia = "R"; // R - Remetente, D - Destinatário

	@NotEmpty
	@Column(name = "comopaga_transferencia")
	private String comoPagaTransferencia = "S"; // S - Saldo, B - Boleto

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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAmrPlanoId() {
		return amrPlanoId;
	}

	public void setAmrPlanoId(String amrPlanoId) {
		this.amrPlanoId = amrPlanoId;
	}

	public String getQuemPagaTransferencia() {
		return quemPagaTransferencia;
	}

	public void setQuemPagaTransferencia(String quemPagaTransferencia) {
		this.quemPagaTransferencia = quemPagaTransferencia;
	}

	public String getComoPagaTransferencia() {
		return comoPagaTransferencia;
	}

	public void setComoPagaTransferencia(String comoPagaTransferencia) {
		this.comoPagaTransferencia = comoPagaTransferencia;
	}

}