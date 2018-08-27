package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "amr_parametro")
public class Parametro implements Serializable {

	@Id
	@Column(name = "amr_parametro_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "isactive")
	private String isactive;

	@Column(name = "created")
	private Date created = new Date();

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "updated")
	private Date updated = new Date();

	@Column(name = "updatedby")
	private String updatedBy;

	@Column(name = "name")
	private String nome;

	@Column(name = "value")
	private String valor;

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

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Parametro [id=" + id + ", nome=" + nome + ", valor=" + valor + "]";
	}

}
