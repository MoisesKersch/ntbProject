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
public class RetEnvAdesao implements Serializable {

	private boolean sucesso = true;
	private String descricao;

	public RetEnvAdesao() {
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

	public void parse(String xml) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));

		Element retorno = (Element) document.getDocumentElement().getElementsByTagName("retorno").item(0);
		this.sucesso = Boolean.parseBoolean(retorno.getElementsByTagName("sucesso").item(0).getTextContent());
		this.descricao = retorno.getElementsByTagName("descricao").item(0).getTextContent();
	}

}