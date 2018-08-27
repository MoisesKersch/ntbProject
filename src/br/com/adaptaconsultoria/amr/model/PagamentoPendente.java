package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "amr_dp_pendente")
public class PagamentoPendente implements Serializable {

	@Id
	@Column(name = "amr_dp_pendente_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "login_rede")
	private String loginRede;
	@Column(name = "c_debt_payment_id")
	private String cDebtPaymentId;
	@Column(name = "username")
	private String username;
	@Column(name = "name")
	private String name;
	@Column(name = "taxid")
	private String taxid;
	@Column(name = "patrocinador")
	private String patrocinador;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "c_order_id")
	private String cOrderId;
	@Column(name = "amr_precompra_id")
	private String amrPreCompraId;

	public PagamentoPendente() {
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

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getLoginRede() {
		return loginRede;
	}

	public void setLoginRede(String loginRede) {
		this.loginRede = loginRede;
	}

	public String getcDebtPaymentId() {
		return cDebtPaymentId;
	}

	public void setcDebtPaymentId(String cDebtPaymentId) {
		this.cDebtPaymentId = cDebtPaymentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public String getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getcOrderId() {
		return cOrderId;
	}

	public void setcOrderId(String cOrderId) {
		this.cOrderId = cOrderId;
	}

	public String getAmrPreCompraId() {
		return amrPreCompraId;
	}

	public void setAmrPreCompraId(String amrPreCompraId) {
		this.amrPreCompraId = amrPreCompraId;
	}

}