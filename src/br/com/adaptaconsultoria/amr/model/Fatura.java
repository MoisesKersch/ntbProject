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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "c_invoice")
public class Fatura implements Serializable, Comparable<Fatura> {

	@Id
	@Column(name = "c_invoice_id")
	private String id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ad_org_id")
	private Organizacao organizacao;
	@Column(name = "em_ac_fiscalno")
	private String numeroDocumento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_bpartner_id", referencedColumnName = "c_bpartner_id")
	private ParceiroNegocios parceiroNegocios;
	@Column(name = "em_ac_fiscalserie")
	private String serieDocumentoFiscal;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateinvoiced")
	private Date dataFatura;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateacct")
	private Date dataContabil;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateordered")
	private Date dataPedido;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "taxdate")
	private Date dataTaxa;
	@Column(name = "docStatus")
	private String statusDocumento;
	@Column(name = "isPrinted")
	private String impresso;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_doctype_id", referencedColumnName = "c_doctype_id")
	private TipoDocumento tipoDocumento;

	public Fatura() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Organizacao getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public ParceiroNegocios getParceiroNegocios() {
		return parceiroNegocios;
	}

	public void setParceiroNegocios(ParceiroNegocios parceiroNegocios) {
		this.parceiroNegocios = parceiroNegocios;
	}

	public String getSerieDocumentoFiscal() {
		return serieDocumentoFiscal;
	}

	public void setSerieDocumentoFiscal(String serieDocumentoFiscal) {
		this.serieDocumentoFiscal = serieDocumentoFiscal;
	}

	public Date getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(Date dataFatura) {
		this.dataFatura = dataFatura;
	}

	public Date getDataContabil() {
		return dataContabil;
	}

	public void setDataContabil(Date dataContabil) {
		this.dataContabil = dataContabil;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataTaxa() {
		return dataTaxa;
	}

	public void setDataTaxa(Date dataTaxa) {
		this.dataTaxa = dataTaxa;
	}

	public String getStatusDocumento() {
		return statusDocumento;
	}

	public void setStatusDocumento(String statusDocumento) {
		this.statusDocumento = statusDocumento;
	}

	public boolean isImpresso() {
		return impresso.equalsIgnoreCase("Y");
	}

	public void setImpresso(boolean impresso) {
		this.impresso = impresso ? "Y" : "N";
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public int compareTo(Fatura fatura) {
		return this.numeroDocumento.compareTo(fatura.getNumeroDocumento());
	}

}