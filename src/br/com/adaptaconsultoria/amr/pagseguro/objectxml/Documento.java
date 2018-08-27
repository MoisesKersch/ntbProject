package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "document")
public class Documento {

	private String type;
	private String value;

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Documento [type=" + type + ", value=" + value + "]";
	}

}