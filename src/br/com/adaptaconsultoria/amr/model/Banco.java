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
@Table(name = "amr_banco")
public class Banco implements Serializable {

	@Id
	@Column(name = "amr_banco_id")
	private String id;
	@Column(name = "name")
	private String nome;
	@Column(name = "solicitaoperacao")
	private String solicitaOperacao;

	public Banco() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSolicitaOperacao() {
		return solicitaOperacao;
	}

	public void setSolicitaOperacao(String solicitaOperacao) {
		this.solicitaOperacao = solicitaOperacao;
	}

}