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
@Table(name = "amr_mural_v")
public class Mural implements Serializable {

	@Id
	@Column(name = "amr_mural_v_id")
	private String id;
	@Column(name = "amr_mural_id")
	private String amrMuralId;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "note")
	private String note;

	public Mural() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmrMuralId() {
		return amrMuralId;
	}

	public void setAmrMuralId(String amrMuralId) {
		this.amrMuralId = amrMuralId;
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

	public String getcBPartnerId() {
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId) {
		this.cBPartnerId = cBPartnerId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}