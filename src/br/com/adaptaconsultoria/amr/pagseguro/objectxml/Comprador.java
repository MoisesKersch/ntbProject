package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sender")
public class Comprador {

	private String name;
	private String email;
	private Telefone phone;
	private List<Documento> documents;

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public Telefone getPhone() {
		return phone;
	}

	@XmlElement
	public void setPhone(Telefone phone) {
		this.phone = phone;
	}

	public List<Documento> getDocuments() {
		return documents;
	}

	@XmlElementWrapper
	@XmlElement(name = "document")
	public void setDocuments(List<Documento> documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "Comprador [name=" + name + ", email=" + email + ", phone=" + phone + ", documents=" + documents + "]";
	}

}