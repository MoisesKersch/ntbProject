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
@Table(name = "amr_doc")
public class Documento implements Serializable
{

	@Id
	@Column(name = "amr_doc_id")
	private String id;
	@Column(name = "amr_documento_id")
	private String amrDocumentoId;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "ad_user_id")
	private String adUserId;
	@Column(name = "c_bpartner_id")
	private String cBPartnerId;
	@Column(name = "description")
	private String description;
	@Column(name = "url")
	private String url;
	@Column(name = "mimetype")
	private String mimetype;
	@Column(name = "download")
	private String download;
	@Column(name = "categoria")
	private String categoria;

	public Documento()
	{
		super();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getAmrDocumentoId()
	{
		return amrDocumentoId;
	}

	public void setAmrDocumentoId(String amrDocumentoId)
	{
		this.amrDocumentoId = amrDocumentoId;
	}

	public String getAdClientId()
	{
		return adClientId;
	}

	public void setAdClientId(String adClientId)
	{
		this.adClientId = adClientId;
	}

	public String getAdOrgId()
	{
		return adOrgId;
	}

	public void setAdOrgId(String adOrgId)
	{
		this.adOrgId = adOrgId;
	}

	public String getAdUserId()
	{
		return adUserId;
	}

	public void setAdUserId(String adUserId)
	{
		this.adUserId = adUserId;
	}

	public String getcBPartnerId()
	{
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId)
	{
		this.cBPartnerId = cBPartnerId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getMimetype()
	{
		return mimetype;
	}

	public void setMimetype(String mimetype)
	{
		this.mimetype = mimetype;
	}

	public String getDownload()
	{
		return download;
	}

	public void setDownload(String download)
	{
		this.download = download;
	}

	public String getCategoria()
	{
		return categoria;
	}

	public void setCategoria(String categoria)
	{
		this.categoria = categoria;
	}

}