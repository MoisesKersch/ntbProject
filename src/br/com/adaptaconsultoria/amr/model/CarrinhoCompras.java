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
@Table(name = "amr_cart")
public class CarrinhoCompras implements Serializable {

	@Id
	@Column(name = "amr_cart_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "createdby")
	private String createdby = "100";
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "processed")
	private String processado = "N";
	@Column(name = "vendadireta")
	private String vendaDireta = "N";

	public CarrinhoCompras() {
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

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getProcessado() {
		return processado;
	}

	public void setProcessado(String processado) {
		this.processado = processado;
	}

	public String getVendaDireta() {
		return vendaDireta;
	}

	public void setVendaDireta(String vendaDireta) {
		this.vendaDireta = vendaDireta;
	}

}