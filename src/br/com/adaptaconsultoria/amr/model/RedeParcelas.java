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
@Table(name = "amr_redeparcelas")
public class RedeParcelas implements Serializable {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "numero")
	private String numero;
	@Column(name = "bandeira")
	private String bandeira;
	@Column(name = "nome")
	private String nome;
	@Column(name = "ac_paymentmethod_id")
	private String acPaymentMethodId;
	@Column(name = "c_paymentterm_id")
	private String cPaymentTermId;
	@Column(name = "coef_parcela")
	private BigDecimal coeficienteParcelas;
	@Column(name = "coef_acrescimo")
	private BigDecimal coeficienteAcrescimo;
	@Column(name = "amr_precompra_id")
	private String amrPreCompraId;

	public RedeParcelas() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
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

	public String getcPaymentTermId() {
		return cPaymentTermId;
	}

	public void setcPaymentTermId(String cPaymentTermId) {
		this.cPaymentTermId = cPaymentTermId;
	}

	public BigDecimal getCoeficienteParcelas() {
		return coeficienteParcelas;
	}

	public void setCoeficienteParcelas(BigDecimal coeficienteParcelas) {
		this.coeficienteParcelas = coeficienteParcelas;
	}

	public BigDecimal getCoeficienteAcrescimo() {
		return coeficienteAcrescimo;
	}

	public void setCoeficienteAcrescimo(BigDecimal coeficienteAcrescimo) {
		this.coeficienteAcrescimo = coeficienteAcrescimo;
	}

	public String getAmrPreCompraId() {
		return amrPreCompraId;
	}

	public void setAmrPreCompraId(String amrPreCompraId) {
		this.amrPreCompraId = amrPreCompraId;
	}

}