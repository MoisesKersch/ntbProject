package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "amr_reuniao")
public class Reuniao implements Serializable 
{
	
	@Id
	@Column(name = "amr_reuniao_id")
	private String id;
	
	@NotNull
	@Column(name = "ad_client_id")
	private String adClientId;
	
	@NotNull
	@Column(name = "ad_org_id")
	private String adOrgId;
	
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
	
	@Column(name = "updatedby")
	private String updatedBy;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "responsavel_id")
	@NotNull
	private ParceiroNegocios responsavel;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "anfitriao_id")
	@NotNull
	private ParceiroNegocios anfitriao;
	
	@NotNull
	@Column(name = "data")
	private Date data;
	
	@Column(name = "participantes")
	private BigDecimal participantes;
	
	@Column(name = "observacoes")
	private String observacoes;

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

	public String getIsactive()
	{
		return isactive;
	}

	public void setIsactive(String isactive)
	{
		this.isactive = isactive;
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

	public ParceiroNegocios getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ParceiroNegocios responsavel) {
		this.responsavel = responsavel;
	}

	public ParceiroNegocios getAnfitriao() {
		return anfitriao;
	}

	public void setAnfitriao(ParceiroNegocios anfitriao) {
		this.anfitriao = anfitriao;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm" , timezone = "America/Sao_Paulo", locale = "pt-BR")
	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public BigDecimal getParticipantes()
	{
		return participantes;
	}

	public void setParticipantes(BigDecimal participantes)
	{
		this.participantes = participantes;
	}

	public String getObservacoes()
	{
		return observacoes;
	}

	public void setObservacoes(String observacoes)
	{
		this.observacoes = observacoes;
	}
}