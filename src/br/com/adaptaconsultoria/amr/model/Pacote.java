package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "amr_pacote")
public class Pacote implements Serializable {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "name")
	private String nome;

	@Column(name = "description")
	private String descricao;

	@Column(name = "obrigatorio_adesao")
	private String obrigatorioAdesao;

	@Column(name = "imageurl")
	private String imageURL;

	@Column(name = "qty_adesao")
	private Integer quantidadeAdesao;

	@Column(name = "ad_user_id")
	private String adUserId;

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

	@Column(name = "m_product_id")
	private String mProductId;

	@Column(name = "precode")
	private String precoDe;

	@Column(name = "precopor")
	private String precoPor;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "descricao_completa")
	private String descricaoCompleta;

	@Column(name = "ordem")
	private Integer ordem;

	@Column(name = "m_pricelist_version_id")
	private String mPriceListVersionId;

	@Column(name = "ativodesde")
	private String ativoDesde;

	@Column(name = "pts_binario")
	private Integer ptsBinario;

	@Column(name = "pts_carreira")
	private String ptsCarreira;

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

	public Pacote() {
		super();
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

	public String getObrigatorioAdesao() {
		return obrigatorioAdesao;
	}

	public void setObrigatorioAdesao(String obrigatorioAdesao) {
		this.obrigatorioAdesao = obrigatorioAdesao;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Integer getQuantidadeAdesao() {
		return quantidadeAdesao;
	}

	public void setQuantidadeAdesao(Integer quantidadeAdesao) {
		this.quantidadeAdesao = quantidadeAdesao;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public BigDecimal getPeso() {
		if (peso == null)
			peso = BigDecimal.ZERO;
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

	public String getmProductId() {
		return mProductId;
	}

	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}

	public String getPrecoDe() {
		return precoDe;
	}

	public void setPrecoDe(String precoDe) {
		this.precoDe = precoDe;
	}

	public String getPrecoPor() {
		return precoPor;
	}

	public void setPrecoPor(String precoPor) {
		this.precoPor = precoPor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getmPriceListVersionId() {
		return mPriceListVersionId;
	}

	public void setmPriceListVersionId(String mPriceListVersionId) {
		this.mPriceListVersionId = mPriceListVersionId;
	}

	public String getAtivoDesde() {
		return ativoDesde;
	}

	public void setAtivoDesde(String ativoDesde) {
		this.ativoDesde = ativoDesde;
	}

	@Override
	public String toString() {
		return "Pacote [id=" + id + ", adClientId=" + adClientId + ", adOrgId=" + adOrgId + ", nome=" + nome
				+ ", descricao=" + descricao + ", obrigatorioAdesao=" + obrigatorioAdesao + ", imageURL=" + imageURL
				+ ", quantidadeAdesao=" + quantidadeAdesao + ", adUserId=" + adUserId + ", peso=" + peso + ", altura="
				+ altura + ", largura=" + largura + ", comprimento=" + comprimento + ", diametro=" + diametro
				+ ", mProductId=" + mProductId + ", precoDe=" + precoDe + ", precoPor=" + precoPor + ", categoria="
				+ categoria + ", descricaoCompleta=" + descricaoCompleta + ", ordem=" + ordem + ", mPriceListVersionId="
				+ mPriceListVersionId + ", ativoDesde=" + ativoDesde + ", ptsBinario=" + ptsBinario + ", ptsCarreira="
				+ ptsCarreira + "]";
	}
}