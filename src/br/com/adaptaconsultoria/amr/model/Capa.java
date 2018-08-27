package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "amr_capa")
public class Capa implements Serializable 
{

	@Id
	@Column(name = "amr_capa_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String adOrgId;

	@Column(name = "isactive")
	private String isActive;

	@Column(name = "created")
	private Date created;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "updated")
	private Date updated;

	@Column(name = "updatedby")
	private String updatedBy;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "urlembed")
	private String urlembed;

	@Column(name = "palavraschave")
	private String palavraschave;

	@Column(name = "primeiro_acesso")
	private String primeiroAcesso;

	@Column(name = "capa_de")
	private Date capaDe;

	@Column(name = "capa_ate")
	private Date capaAte;

	@Column(name = "pagina")
	private String pagina;

	public Capa() 
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getCreated() 
	{
		return created;
	}

	public void setCreated(Date created) 
	{
		this.created = created;
	}

	public String getCreatedBy() 
	{
		return createdBy;
	}

	public void setCreatedBy(String createdBy) 
	{
		this.createdBy = createdBy;
	}

	public Date getUpdated() 
	{
		return updated;
	}

	public void setUpdated(Date updated) 
	{
		this.updated = updated;
	}

	public String getUpdatedBy() 
	{
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) 
	{
		this.updatedBy = updatedBy;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getUrlembed() 
	{
		return urlembed;
	}

	public void setUrlembed(String urlembed) 
	{
		this.urlembed = urlembed;
	}

	public String getPalavraschave() 
	{
		return palavraschave;
	}

	public void setPalavraschave(String palavraschave) 
	{
		this.palavraschave = palavraschave;
	}

	public String getPrimeiroAcesso() 
	{
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(String primeiroAcesso) 
	{
		this.primeiroAcesso = primeiroAcesso;
	}

	public Date getCapaDe() 
	{
		return capaDe;
	}

	public void setCapaDe(Date capaDe) 
	{
		this.capaDe = capaDe;
	}

	public Date getCapaAte()
	{
		return capaAte;
	}

	public void setCapaAte(Date capaAte) 
	{
		this.capaAte = capaAte;
	}

	public String getPagina() 
	{
		return pagina;
	}

	public void setPagina(String pagina) 
	{
		this.pagina = pagina;
	}
}