package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shipping")
public class Frete {

	private Integer type;
	private BigDecimal cost;
	private Endereco address;

	public Integer getType() {
		return type;
	}

	@XmlElement
	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getCost() {
		return cost;
	}

	@XmlElement
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Endereco getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(Endereco address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Frete [type=" + type + ", cost=" + cost + ", address=" + address + "]";
	}

}