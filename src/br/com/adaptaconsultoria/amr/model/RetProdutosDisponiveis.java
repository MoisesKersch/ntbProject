package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import br.com.adaptaconsultoria.amr.util.io.FileUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class RetProdutosDisponiveis implements Serializable {

	private boolean sucesso = true;
	private String descricao;
	private List<Produto> produtos = new ArrayList<Produto>();

	public RetProdutosDisponiveis() {
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void parse(String xml) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));

		Element retorno = (Element) document.getDocumentElement().getElementsByTagName("retorno").item(0);
		this.sucesso = Boolean.parseBoolean(retorno.getElementsByTagName("sucesso").item(0).getTextContent());
		this.descricao = retorno.getElementsByTagName("descricao").item(0).getTextContent();

		if (this.sucesso) {
			Element eProdutos = (Element) document.getDocumentElement().getElementsByTagName("produtos").item(0);
			for (int i = 0; i < eProdutos.getElementsByTagName("produto").getLength(); i++) {
				Produto produto = new Produto();
				produto.parse((Element) eProdutos.getElementsByTagName("produto").item(i));
				this.produtos.add(produto);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		RetProdutosDisponiveis ret = new RetProdutosDisponiveis();
		ret.parse(FileUtil.toString("/home/adapta/projetos/Adesão Marketing Multinível/src/br/com/adaptaconsultoria/amr/manager/NewFile.xml"));
	}

}