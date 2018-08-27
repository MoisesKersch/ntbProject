package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@SuppressWarnings("serial")
@Entity
public class ExtratoNovo implements Serializable 
{
	@Id
	@Column(name = "amr_extratofinanceiro_id")
	private String id;
	
	@Column(name = "ano")
	private BigDecimal ano;
	
	@Column(name = "mes")
	private BigDecimal mes;
	
	@Column(name = "datalcto")
	private Date dataLcto;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "debito_credito")
	private String debitoCredito;
	
	@Column(name = "descricao")
	private String descricao;
	
	public String getValorPrint(){
		NumberFormat brFormatted = NumberFormat.getCurrencyInstance();
		return brFormatted.format(this.valor);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAno() {
		return ano;
	}

	public void setAno(BigDecimal ano) {
		this.ano = ano;
	}

	public BigDecimal getMes() {
		return mes;
	}

	public void setMes(BigDecimal mes) {
		this.mes = mes;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public Date getDataLcto() {
		return dataLcto;
	}

	public void setDataLcto(Date dataLcto) {
		this.dataLcto = dataLcto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDebitoCredito() {
		return debitoCredito;
	}

	public void setDebitoCredito(String debitoCredito) {
		this.debitoCredito = debitoCredito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}