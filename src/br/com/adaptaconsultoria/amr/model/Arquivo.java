package br.com.adaptaconsultoria.amr.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.adaptaconsultoria.amr.properties.AMRProperties;

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
@Table(name = "c_file")
public class Arquivo implements Serializable {

	@Id
	@Column(name = "c_file_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "updatedby")
	private String updatedby = "100";
	@Column(name = "createdby")
	private String createdby = "100";
	@Column(name = "ad_record_id")
	private String adRecordId;
	@Column(name = "ad_table_id")
	private String adTableId;
	@Column(name = "seqNo")
	private Integer seqNo;
	@Column(name = "c_datatype_id")
	private String cDataTypeId;
	@Column(name = "name")
	private String name;

	public Arquivo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdRecordId() {
		return adRecordId;
	}

	public void setAdRecordId(String adRecordId) {
		this.adRecordId = adRecordId;
	}

	public String getAdTableId() {
		return adTableId;
	}

	public void setAdTableId(String adTableId) {
		this.adTableId = adTableId;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getcDataTypeId() {
		return cDataTypeId;
	}

	public void setcDataTypeId(String cDataTypeId) {
		this.cDataTypeId = cDataTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public File path() throws Exception {
		String diretorio = AMRProperties.getInstance().getPropriedade("diretorio.openbravo.anexo");
		String arquivo = this.adTableId + "-" + this.adRecordId;
		File file = new File(diretorio + arquivo + "/" + this.name);
		return file;
	}

}