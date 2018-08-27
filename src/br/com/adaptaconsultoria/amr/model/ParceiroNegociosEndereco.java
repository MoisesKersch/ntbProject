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
@Table(name = "c_bpartner_location")
public class ParceiroNegociosEndereco implements Serializable {

	@Id
	@Column(name = "c_bpartner_location_id")
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_bpartner_id")
	private ParceiroNegocios parceiroNegocios;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_location_id")
	private Endereco endereco;
	@Column(name = "em_ac_tipotelefone")
	private String tipoTelefone;
	@Column(name = "em_ac_ddd")
	private Integer ddd;
	@Column(name = "phone")
	private String telefone;
	@Column(name = "em_ac_referencia")
	private String referencia;

	public ParceiroNegociosEndereco() {
		super();
	}

	public String getId() {
		return id;
	}

	public ParceiroNegocios getParceiroNegocios() {
		return parceiroNegocios;
	}

	public void setParceiroNegocios(ParceiroNegocios parceiroNegocios) {
		this.parceiroNegocios = parceiroNegocios;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}