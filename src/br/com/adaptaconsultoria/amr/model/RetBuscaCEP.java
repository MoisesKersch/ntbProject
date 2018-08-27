package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
public class RetBuscaCEP implements Serializable {

	private boolean sucesso = true;
	private String descricao;
	private String cidade = "";
	private String uf = "";
	private String bairro = "";
	private String logradouro = "";
	private String complemento = "";

	public RetBuscaCEP() {
		super();
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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

		Element retorno = (Element) document.getDocumentElement().getElementsByTagName("retorno").item(0);
		this.sucesso = Boolean.parseBoolean(retorno.getElementsByTagName("sucesso").item(0).getTextContent());
		this.descricao = retorno.getElementsByTagName("descricao").item(0).getTextContent();

		this.sucesso = Boolean.parseBoolean(retorno.getElementsByTagName("sucesso").item(0).getTextContent());

		Element cep = (Element) document.getDocumentElement().getElementsByTagName("cep").item(0);
		if (this.sucesso) {
			this.cidade = cep.getElementsByTagName("cidade").item(0).getTextContent();
			this.uf = cep.getElementsByTagName("uf").item(0).getTextContent();
			this.bairro = cep.getElementsByTagName("bairro").item(0).getTextContent();
			this.logradouro = cep.getElementsByTagName("logradouro").item(0).getTextContent();
			this.complemento = cep.getElementsByTagName("complemento").item(0).getTextContent();
		}
	}

}