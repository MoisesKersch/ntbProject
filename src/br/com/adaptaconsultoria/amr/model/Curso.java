package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

@Entity
@Table(name = "amr_curso")
@SuppressWarnings("serial")
public class Curso implements Serializable {

	@Id
	@Column(name = "amr_curso_id")
	private String id;

	@NotNull
	@Column(name = "ad_client_id")
	private String adClientId;

	@NotNull
	@Column(name = "ad_org_id")
	private String adOrgId;

	@NotNull
	@Column(name = "isactive")
	private String isactive = "Y";

	@NotNull
	@Column(name = "created")
	private Date created = new Date();

	@NotNull
	@Column(name = "createdby")
	private String createdby;

	@NotNull
	@Column(name = "updated")
	private Date updated = new Date();

	@NotNull
	@Column(name = "updatedby")
	private String updatedby;

	@NotNull
	@Column(name = "name")
	private String nome;

	@Column(name = "shortname")
	private String nomeCurto;

	@Column(name = "description")
	private String descricao;

	@Column(name = "numsections")
	private Integer numSessao;

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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCurto() {
		return nomeCurto;
	}

	public void setNomeCurto(String nomeCurto) {
		this.nomeCurto = nomeCurto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumSessao() {
		return numSessao;
	}

	public void setNumSessao(Integer numSessao) {
		this.numSessao = numSessao;
	}

}
