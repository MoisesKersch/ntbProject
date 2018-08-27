package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "amr_carreirastatus")
public class CarreiraStatus implements Serializable {

	@Id
	@Column(name = "amr_carreirastatus_id")
	private String id;
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "saldoem")
	private Date saldoEm = new Date();
	@Column(name = "nome")
	private String nome;
	@Column(name = "icone")
	private String icone;
	@Column(name = "meta")
	private BigDecimal meta;
	@Column(name = "pontuacao")
	private BigDecimal pontuacao;
	@Column(name = "realizado")
	private BigDecimal realizado;

	public CarreiraStatus() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getcBPartnerId() {
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId) {
		this.cBPartnerId = cBPartnerId;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public Date getSaldoEm() {
		return saldoEm;
	}

	public void setSaldoEm(Date saldoEm) {
		this.saldoEm = saldoEm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public BigDecimal getMeta() {
		return meta;
	}

	public void setMeta(BigDecimal meta) {
		this.meta = meta;
	}

	public BigDecimal getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(BigDecimal pontuacao) {
		this.pontuacao = pontuacao;
	}

	public BigDecimal getRealizado() {
		return realizado;
	}

	public void setRealizado(BigDecimal realizado) {
		this.realizado = realizado;
	}

	public String getSaldoEmFormatado() {
		if (saldoEm == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(this.saldoEm);
	}

}