package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phone")
public class Telefone {

	private String areaCode;
	private String number;

	public String getAreaCode() {
		return areaCode;
	}

	@XmlElement
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	@XmlElement
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Telefone [areaCode=" + areaCode + ", number=" + number + "]";
	}

}