package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="paymentMethod")
public class MetodoPagamento {

	private Integer type;
	private Integer code;

	public Integer getType() {
		return type;
	}

	@XmlElement
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCode() {
		return code;
	}

	@XmlElement
	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MetodoPagamento [type=" + type + ", code=" + code + "]";
	}

}