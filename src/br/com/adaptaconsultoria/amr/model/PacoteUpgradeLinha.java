package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "amr_pacote_upgradeline")
public class PacoteUpgradeLinha implements Serializable {

	@Id
	@Column(name = "amr_pacote_upgradeline_id")
	private String id;
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "amr_pacote_upgrade_id")
	// private PacoteUpgrade pacoteUpgrade;
	@Column(name = "amr_pacote_upgrade_id")
	private String amrPacoteUpgradeId;
	@Column(name = "m_product_id")
	private String mProductId;
	@Column(name = "qtde")
	private BigDecimal quantidade;
	@Column(name = "pricelist")
	private BigDecimal pricelist;
	@Column(name = "amr_upgrade_id")
	private String amrUpgradeId;

	public PacoteUpgradeLinha() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmrPacoteUpgradeId() {
		return amrPacoteUpgradeId;
	}

	public void setAmrPacoteUpgradeId(String amrPacoteUpgradeId) {
		this.amrPacoteUpgradeId = amrPacoteUpgradeId;
	}

	public String getmProductId() {
		return mProductId;
	}

	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPricelist() {
		return pricelist;
	}

	public void setPricelist(BigDecimal pricelist) {
		this.pricelist = pricelist;
	}

	public String getAmrUpgradeId() {
		return amrUpgradeId;
	}

	public void setAmrUpgradeId(String amrUpgradeId) {
		this.amrUpgradeId = amrUpgradeId;
	}

}