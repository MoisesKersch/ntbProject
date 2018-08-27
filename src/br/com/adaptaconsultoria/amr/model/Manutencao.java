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
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "amr_manutencao")
public class Manutencao implements Serializable {

	@Id
	@Column(name = "amr_manutencao_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "title")
	private String titulo;
	@Column(name = "description")
	private String descricao;
	@Column(name = "manutencao")
	private String manutencao;
	@Column(name = "isactive")
	private String isactive;
	@Column(name = "datatermino")
	private Date datatermino;
	@Column(name = "datainicio")
	private Date datainicio;

	public Manutencao() {
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getManutencao() {
		return manutencao;
	}

	public void setManutencao(String manutencao) {
		this.manutencao = manutencao;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public Date getDatatermino() {
		return datatermino;
	}

	public void setDatatermino(Date datatermino) {
		this.datatermino = datatermino;
	}

	public Date getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

}