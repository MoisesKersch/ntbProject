package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.w3c.dom.Element;

import br.com.adaptaconsultoria.amr.util.io.Dom4jUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Produto implements Serializable {

	private String id;
	private String codigo;
	private String nome;
	private boolean readOnly = false;
	private String imagemURL;
	@NumberFormat(style = Style.CURRENCY, pattern = "##,###,###.##")
	private String preco;
	private boolean selecionado = false;
	private int quantidade = 1;
	private String descricao;
	@NumberFormat(style = Style.CURRENCY, pattern = "##,###,###.##")
	private String precoDe;
	private String categoria;
	private String descricaoCompleta;
	private Integer ptsBinario;
	private String ptsCarreira;
	
	public Produto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getImagemURL() {
		return imagemURL;
	}

	public void setImagemURL(String imagemURL) {
		this.imagemURL = imagemURL;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getPrecoDe() {
		return precoDe;
	}

	public void setPrecoDe(String precoDe) {
		this.precoDe = precoDe;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setSelecionado(boolean selecionado) {
		if (this.readOnly)
			this.selecionado = true;
		else
			this.selecionado = selecionado;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public void parse(Element element) throws Exception {
		this.id = element.getAttribute("id");
		this.codigo = element.getAttribute("codigo");
		this.nome = element.getTextContent();
	}

	public org.dom4j.Element toElement() throws Exception {
		org.dom4j.Element element = Dom4jUtil.createElement("produto");
		element.add(Dom4jUtil.createElement("id", this.id));
		element.add(Dom4jUtil.createElement("quantidade", this.selecionado ? String.valueOf(quantidade) : "0"));
		return element;
	}

	public Integer getPtsBinario() {
		return ptsBinario;
	}

	public void setPtsBinario(Integer ptsBinario) {
		this.ptsBinario = ptsBinario;
	}

	public String getPtsCarreira() {
		return ptsCarreira;
	}

	public void setPtsCarreira(String ptsCarreira) {
		this.ptsCarreira = ptsCarreira;
	}

}