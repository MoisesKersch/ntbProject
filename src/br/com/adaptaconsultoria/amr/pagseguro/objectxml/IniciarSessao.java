package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="session")
public class IniciarSessao {

	private String id;

	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IniciarSessao [id=" + id + "]";
	}

}