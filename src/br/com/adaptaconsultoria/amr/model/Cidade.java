package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Cidade implements Serializable {

	private String id;
	private String nome;
	private String resultado;
	private String resultadoTxt;
	private String uf;
	private String cidade;
	private String bairro;
	private String tipoLogradouro;
	private String logradouro;
	private String complemento;

	public Cidade() {
		super();
	}

	public Cidade(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getResultadoTxt() {
		return resultadoTxt;
	}

	public void setResultadoTxt(String resultadoTxt) {
		this.resultadoTxt = resultadoTxt;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void parse(String xml) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));

		this.resultado = document.getDocumentElement().getElementsByTagName("resultado").item(0).getTextContent();
		this.resultadoTxt = document.getDocumentElement().getElementsByTagName("resultado_txt").item(0).getTextContent();
		this.uf = document.getDocumentElement().getElementsByTagName("uf").item(0).getTextContent();
		this.cidade = document.getDocumentElement().getElementsByTagName("cidade").item(0).getTextContent();
		this.bairro = document.getDocumentElement().getElementsByTagName("bairro").item(0).getTextContent();
		this.tipoLogradouro = document.getDocumentElement().getElementsByTagName("tipo_logradouro").item(0).getTextContent();
		this.logradouro = document.getDocumentElement().getElementsByTagName("logradouro").item(0).getTextContent();
	}

}