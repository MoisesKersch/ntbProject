package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 *
 * @author Moises Kerschner..
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

@Entity
@SuppressWarnings("serial")
@Table(name = "amr_agenda")
public class Agenda implements Serializable 
{
	@Id
	@Column(name = "amr_agenda_id")
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
	private String createdby;
	
	@NotNull
	@Column(name = "updated")
	private Date updated = new Date();
	
	@NotNull
	@Column(name = "updatedby")
	private String updatedby;
	
	@NotNull
	@Column(name = "c_bpartner_id")
	private String cBpartnerId;

	@NotNull
	@Column(name = "assunto")
	private String assunto;

	@Column(name = "anotacao")
	private String anotacao;
	
	@Column(name = "quando")
	private Date quando;
	
	@Column(name = "onde")
	private String onde;
	
	@NotNull
	@Column(name = "situacao")
	private String situacao = "A";
	
	@Column(name = "agenda_pai_id")
	private String agendaPaiId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "amr_lista_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonBackReference
	private AgendaContato amrListaId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "amr_agenda_acao_id")
	private AgendaAcao amrAgendaAcaoId;

	public AgendaAcao getAmrAgendaAcaoId() {
		return amrAgendaAcaoId;
	}

	public void setAmrAgendaAcaoId(AgendaAcao amrAgendaAcaoId) {
		this.amrAgendaAcaoId = amrAgendaAcaoId;
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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getcBpartnerId() {
		return cBpartnerId;
	}

	public void setcBpartnerId(String cBpartnerId) {
		this.cBpartnerId = cBpartnerId;
	}

	public AgendaContato getAmrListaId() {
		return amrListaId;
	}

	public void setAmrListaId(AgendaContato amrListaId) {
		this.amrListaId = amrListaId;
	}
	
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public Date getQuando() {
		return quando;
	}

	public void setQuando(Date quando) {
		this.quando = quando;
	}

	public String getOnde() {
		return onde;
	}

	public void setOnde(String onde) {
		this.onde = onde;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getAgendaPaiId() {
		return agendaPaiId;
	}

	public void setAgendaPaiId(String agendaPaiId) {
		this.agendaPaiId = agendaPaiId;
	}
}
