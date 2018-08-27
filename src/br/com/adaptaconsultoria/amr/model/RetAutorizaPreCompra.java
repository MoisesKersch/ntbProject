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
public class RetAutorizaPreCompra implements Serializable {

	private boolean sucesso = true;
	private String descricao;
	private String formaPagamento;
	private boolean pagamentoSucesso;
	private String cDebtPaymentId;

	public RetAutorizaPreCompra() {
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

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public boolean isPagamentoSucesso() {
		return pagamentoSucesso;
	}

	public void setPagamentoSucesso(boolean pagamentoSucesso) {
		this.pagamentoSucesso = pagamentoSucesso;
	}

	public String getcDebtPaymentId() {
		return cDebtPaymentId;
	}

	public void setcDebtPaymentId(String cDebtPaymentId) {
		this.cDebtPaymentId = cDebtPaymentId;
	}

	public void parse(String xml) throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xml)));

		Element retorno = (Element) document.getDocumentElement().getElementsByTagName("retorno").item(0);
		this.sucesso = Boolean.parseBoolean(retorno.getElementsByTagName("sucesso").item(0).getTextContent());
		this.descricao = retorno.getElementsByTagName("descricao").item(0).getTextContent();

		Element pagamento = (Element) document.getDocumentElement().getElementsByTagName("pagamento").item(0);
//		TODO:	if (pagamento != null) { 
//			this.formaPagamento = pagamento.getElementsByTagName("tipo").item(0).getTextContent();
//			this.pagamentoSucesso = Boolean.parseBoolean(pagamento.getElementsByTagName("sucesso").item(0).getTextContent());
//			if (this.formaPagamento.equalsIgnoreCase("BOLETO"))
//				this.cDebtPaymentId = pagamento.getElementsByTagName("cDebtPaymentId").item(0).getTextContent();
//		}
	}

}