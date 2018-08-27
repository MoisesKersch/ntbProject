package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "amr_lista")
public class AgendaContato implements Serializable {

	@Id
	@Column(name = "amr_lista_id")
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
	
	@NotNull(message = "*")
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
	private String cBpartneriId;
	
	@NotNull(message = "*")
	@Column(name = "nome")
	private String nome;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "celular")
	private String celular;

	@Column(name = "whatsapp")
	private String whatsapp;

	@Column(name = "skype")
	private String skype;

	@Column(name = "email")
	private String email;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;
	
	@Column(name = "proximo_contato")
	private Date proximoContato = new Date();
	
	@Column(name = "ultimo_contato")
	private Date ultimoContato = new Date();

	@NotNull
	@Column(name = "classificacao")
	private BigDecimal classificacao;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "amr_lista_status_id")
	private String amrListaStatusId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "amrListaId")
	@LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    @JsonIgnore
	private List<Agenda> agendas = new ArrayList<Agenda>();

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

	public String getcBpartneriId() {
		return cBpartneriId;
	}

	public void setcBpartneriId(String cBpartneriId) {
		this.cBpartneriId = cBpartneriId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(BigDecimal classificacao) {
		this.classificacao = classificacao;
	}

	public String getAmrListaStatusId() {
		return amrListaStatusId;
	}

	public void setAmrListaStatusId(String amrListaStatusId) {
		this.amrListaStatusId = amrListaStatusId;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public Date getProximoContato() {
		return proximoContato;
	}

	public void setProximoContato(Date proximoContato) {
		this.proximoContato = proximoContato;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	public Date getUltimoContato() {
		return ultimoContato;
	}

	public void setUltimoContato(Date ultimoContato) {
		this.ultimoContato = ultimoContato;
	}
}
