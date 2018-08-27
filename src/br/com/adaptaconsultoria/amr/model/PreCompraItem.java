package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "amr_precompraitem")
public class PreCompraItem implements Serializable {

	@Id
	@Column(name = "amr_precompraitem_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "createdby")
	private String createdby = "100";
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "amr_precompra_id")
	private PreCompra preCompra;
	@Column(name = "name")
	private String nome;
	@Column(name = "description")
	private String descricao;
	@Column(name = "imagemurl")
	private String imagemURL;
	@Column(name = "m_product_id")
	private String mProductId = null;
	@Column(name = "quantity")
	private Integer quantidade;
	@Column(name = "precounitario")
	private BigDecimal precoUnitario;
	@Column(name = "total")
	private BigDecimal total;
	@Column(name = "peso")
	private BigDecimal peso;
	@Column(name = "altura")
	private BigDecimal altura = BigDecimal.ZERO;
	@Column(name = "largura")
	private BigDecimal largura = BigDecimal.ZERO;
	@Column(name = "comprimento")
	private BigDecimal comprimento = BigDecimal.ZERO;
	@Column(name = "diametro")
	private BigDecimal diametro = BigDecimal.ZERO;
	@Column(name = "precode")
	private BigDecimal precoDe = BigDecimal.ZERO;
	@Column(name = "precopor")
	private BigDecimal precoPor = BigDecimal.ZERO;
	@Column(name = "m_pricelist_version_id")
	private String mPriceListVersionId = null;

	public PreCompraItem() {
		super();
	}
	
	public BigDecimal getTotalPrecoSemDesconto()
	{
		return (this.precoDe.multiply(BigDecimal.valueOf(this.quantidade)));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(String adClientId) {
		this.adClientId = adClientId;
	}

	public String getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(String adOrgId) {
		this.adOrgId = adOrgId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemURL() {
		return imagemURL;
	}

	public void setImagemURL(String imagemURL) {
		this.imagemURL = imagemURL;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public PreCompra getPreCompra() {
		return preCompra;
	}

	public void setPreCompra(PreCompra preCompra) {
		this.preCompra = preCompra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getmProductId() {
		return mProductId;
	}

	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) 
	{
		this.precoUnitario = precoUnitario;
		setTotal(this.precoUnitario.multiply(new BigDecimal(this.quantidade)));
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) 
	{
		this.total = total;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
	}

	public BigDecimal getDiametro() {
		return diametro;
	}

	public void setDiametro(BigDecimal diametro) {
		this.diametro = diametro;
	}

	public BigDecimal getPrecoDe() {
		return precoDe;
	}

	public void setPrecoDe(BigDecimal precoDe) {
		this.precoDe = precoDe;
	}

	public BigDecimal getPrecoPor() {
		return precoPor;
	}

	public void setPrecoPor(BigDecimal precoPor) {
		this.precoPor = precoPor;
	}

	public String getmPriceListVersionId() {
		return mPriceListVersionId;
	}

	public void setmPriceListVersionId(String mPriceListVersionId) {
		this.mPriceListVersionId = mPriceListVersionId;
	}

}