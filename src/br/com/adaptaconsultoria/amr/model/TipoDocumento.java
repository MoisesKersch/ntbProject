package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "c_doctype")
public class TipoDocumento implements Serializable {

	@Id
	@Column(name = "c_doctype_id")
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ad_org_id")
	private Organizacao organizacao;
	@Column(name = "name")
	private String nome;

	public TipoDocumento() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Organizacao getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}