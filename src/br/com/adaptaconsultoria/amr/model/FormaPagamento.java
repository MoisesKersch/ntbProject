package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "amr_formapagamento")
public class FormaPagamento implements Serializable {

	@Id
	@Column(name = "amr_formapagamento_id")
	private String id;
	
	@Column(name = "ad_client_id")
	private String adClientId;
	
	@Column(name = "name")
	private String nome;
	
	@Column(name = "ac_paymentmethod_id")
	private String acPaymentMethodId;
	
	@Column(name = "c_paymentterm_id")
	private String acPaymenttermId;
	
	@Column(name = "description")
	private String descricao;
	
	@Column(name = "desconto_loja")
	private BigDecimal descontoLoja;
	
	@Column(name = "desconto_adesao")
	private BigDecimal descontoAdesao;
	
	public FormaPagamento() {
		super();
	}

	public BigDecimal getDescontoAdesao()
	{
		return descontoAdesao;
	}

	public void setDescontoAdesao(BigDecimal descontoAdesao)
	{
		this.descontoAdesao = descontoAdesao;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAcPaymentMethodId() {
		return acPaymentMethodId;
	}

	public void setAcPaymentMethodId(String acPaymentMethodId) {
		this.acPaymentMethodId = acPaymentMethodId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAcPaymenttermId() {
		return acPaymenttermId;
	}

	public void setAcPaymenttermId(String acPaymenttermId) {
		this.acPaymenttermId = acPaymenttermId;
	}
	
	public BigDecimal getDescontoLoja()
	{
		return descontoLoja;
	}

	public void setDescontoLoja(BigDecimal descontoLoja)
	{
		this.descontoLoja = descontoLoja;
	}
}