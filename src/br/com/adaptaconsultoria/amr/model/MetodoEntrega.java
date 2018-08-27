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
@Table(name = "amr_metodoentrega")
public class MetodoEntrega implements Serializable {

	@Id
	@Column(name = "amr_metodoentrega_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "deliveryviarule")
	private String deliveryviarule;
	@Column(name = "m_shipper_id")
	private String m_shipper_id;
	@Column(name = "metodoentrega")
	private String metodoentrega;
	@Column(name = "ac_servicoentrega_id")
	private String acServicoentregaId;
	@Column(name = "amr_precompra_id")
	private String amrPreCompraId;
	@Column(name = "value")
	private String value;
	@Column(name = "servico")
	private String servico;
	@Column(name = "valor")
	private BigDecimal valor;
	@Column(name = "peso")
	private BigDecimal peso;

	public MetodoEntrega() {
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

	public String getDeliveryviarule() {
		return deliveryviarule;
	}

	public void setDeliveryviarule(String deliveryviarule) {
		this.deliveryviarule = deliveryviarule;
	}

	public String getM_shipper_id() {
		return m_shipper_id;
	}

	public void setM_shipper_id(String m_shipper_id) {
		this.m_shipper_id = m_shipper_id;
	}

	public String getMetodoentrega() {
		return metodoentrega;
	}

	public void setMetodoentrega(String metodoentrega) {
		this.metodoentrega = metodoentrega;
	}

	public String getAcServicoentregaId() {
		return acServicoentregaId;
	}

	public void setAcServicoentregaId(String acServicoentregaId) {
		this.acServicoentregaId = acServicoentregaId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getAmrPreCompraId() {
		return amrPreCompraId;
	}

	public void setAmrPreCompraId(String amrPreCompraId) {
		this.amrPreCompraId = amrPreCompraId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetodoEntrega other = (MetodoEntrega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}