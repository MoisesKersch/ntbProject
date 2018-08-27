package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "amr_pacotecategoria")
public class PacoteCategoria implements Serializable {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "ad_user_id")
	private String adUserId;

	@Column(name = "ad_client_id")
	private String adClientId;

	public PacoteCategoria() {
		super();
	}

	public String getId() {
		return id;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
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

}