package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
@SuppressWarnings("serial")
@Table(name = "ad_orginfo")
public class OrgInfo implements Serializable {

	@Id
	@Column(name = "ad_org_id")
	private String id;
	
	@NotNull
	@Column(name = "ad_client_id")
	private String adClient;
	
	@NotNull
	@Column(name = "isactive")
	private String isactive = "Y";
	
	@NotNull
	@Column(name = "created")
	private Date created = new Date();
	
	@NotNull
	@Column(name = "createdby")
	private String createdBy;
	
	@NotNull
	@Column(name = "updated")
	private Date updated = new Date();
	
	@NotNull
	@Column(name = "updatedby")
	private String updatedBy;
	
	@NotNull
	@Column(name = "c_location_id")
	private String cLocationId;
	
	@NotNull
	@Column(name = "duns")
	private String duns;
	
	@NotNull
	@Column(name = "taxid")
	private String taxId;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_bpartner_id")
	private ParceiroNegocios parceiroNegocio;
	
	@NotNull
	@Column(name = "istaxundeductable")
	private String isTaxUndeductable = "N";
	
	@NotNull
	@Column(name = "c_tax_id")
	private String cTaxId;
	
	@Column(name = "logo")
	private String logo;
	
	@Column(name = "reference_order")
	private String referenceOrder;
	
	@Column(name = "your_company_document_image")
	private String yourCompanyDocumentImage;
	
	@Column(name = "em_af_regime")
	private String emAfRegime;
	
	@NotNull
	@Column(name = "em_af_validacfop")
	private String emAfValidacfop = "Y";
	
	@Column(name = "em_af_aliquotasimples")
	private BigDecimal emAfAliquotasimples = BigDecimal.ZERO;
	
	@NotNull
	@Column(name = "em_ac_baixasequencial")
	private String emAcBaixasequencial = "N";
	
	@Column(name = "em_ac_settlementorgnotparent")
	private String emAcSettlementorgnotparent = "N";
	
	@Column(name = "em_ac_glitem_rcorgnotparent_id")
	private String emAcGlitemRcorgnotparentId;
	
	@Column(name = "em_ac_cashbookorgnotparent_id")
	private String emAcCashbookorgnotparentId;
	
	@Column(name = "em_ac_liberacaoestorno")
	private String em_ac_liberacaoestorno = "Y";
	
	@Column(name = "em_age_vendegarantia")
	private String emAgeVendegarantia;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdClient() {
		return adClient;
	}

	public void setAdClient(String adClient) {
		this.adClient = adClient;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getcLocationId() {
		return cLocationId;
	}

	public void setcLocationId(String cLocationId) {
		this.cLocationId = cLocationId;
	}

	public String getDuns() {
		return duns;
	}

	public void setDuns(String duns) {
		this.duns = duns;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public ParceiroNegocios getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocios parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public String getIsTaxUndeductable() {
		return isTaxUndeductable;
	}

	public void setIsTaxUndeductable(String isTaxUndeductable) {
		this.isTaxUndeductable = isTaxUndeductable;
	}

	public String getcTaxId() {
		return cTaxId;
	}

	public void setcTaxId(String cTaxId) {
		this.cTaxId = cTaxId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getReferenceOrder() {
		return referenceOrder;
	}

	public void setReferenceOrder(String referenceOrder) {
		this.referenceOrder = referenceOrder;
	}

	public String getYourCompanyDocumentImage() {
		return yourCompanyDocumentImage;
	}

	public void setYourCompanyDocumentImage(String yourCompanyDocumentImage) {
		this.yourCompanyDocumentImage = yourCompanyDocumentImage;
	}

	public String getEmAfRegime() {
		return emAfRegime;
	}

	public void setEmAfRegime(String emAfRegime) {
		this.emAfRegime = emAfRegime;
	}

	public String getEmAfValidacfop() {
		return emAfValidacfop;
	}

	public void setEmAfValidacfop(String emAfValidacfop) {
		this.emAfValidacfop = emAfValidacfop;
	}

	public BigDecimal getEmAfAliquotasimples() {
		return emAfAliquotasimples;
	}

	public void setEmAfAliquotasimples(BigDecimal emAfAliquotasimples) {
		this.emAfAliquotasimples = emAfAliquotasimples;
	}

	public String getEmAcBaixasequencial() {
		return emAcBaixasequencial;
	}

	public void setEmAcBaixasequencial(String emAcBaixasequencial) {
		this.emAcBaixasequencial = emAcBaixasequencial;
	}

	public String getEmAcSettlementorgnotparent() {
		return emAcSettlementorgnotparent;
	}

	public void setEmAcSettlementorgnotparent(String emAcSettlementorgnotparent) {
		this.emAcSettlementorgnotparent = emAcSettlementorgnotparent;
	}

	public String getEmAcGlitemRcorgnotparentId() {
		return emAcGlitemRcorgnotparentId;
	}

	public void setEmAcGlitemRcorgnotparentId(String emAcGlitemRcorgnotparentId) {
		this.emAcGlitemRcorgnotparentId = emAcGlitemRcorgnotparentId;
	}

	public String getEmAcCashbookorgnotparentId() {
		return emAcCashbookorgnotparentId;
	}

	public void setEmAcCashbookorgnotparentId(String emAcCashbookorgnotparentId) {
		this.emAcCashbookorgnotparentId = emAcCashbookorgnotparentId;
	}

	public String getEm_ac_liberacaoestorno() {
		return em_ac_liberacaoestorno;
	}

	public void setEm_ac_liberacaoestorno(String em_ac_liberacaoestorno) {
		this.em_ac_liberacaoestorno = em_ac_liberacaoestorno;
	}

	public String getEmAgeVendegarantia() {
		return emAgeVendegarantia;
	}

	public void setEmAgeVendegarantia(String emAgeVendegarantia) {
		this.emAgeVendegarantia = emAgeVendegarantia;
	}
	

}