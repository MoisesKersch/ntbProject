package br.com.adaptaconsultoria.amr.pagseguro.objectxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gatewaySystem")
public class GatewaySystem {

	private String type;
	private Integer authorizationCode;
	private String nsu;
	private String tid;
	private String establishmentCode;
	private String acquirerName;

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public Integer getAuthorizationCode() {
		return authorizationCode;
	}

	@XmlElement
	public void setAuthorizationCode(Integer authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getNsu() {
		return nsu;
	}

	@XmlElement
	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getTid() {
		return tid;
	}

	@XmlElement
	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getEstablishmentCode() {
		return establishmentCode;
	}

	@XmlElement
	public void setEstablishmentCode(String establishmentCode) {
		this.establishmentCode = establishmentCode;
	}

	public String getAcquirerName() {
		return acquirerName;
	}

	@XmlElement
	public void setAcquirerName(String acquirerName) {
		this.acquirerName = acquirerName;
	}

	@Override
	public String toString() {
		return "GatewaySystem [type=" + type + ", authorizationCode=" + authorizationCode + ", nsu=" + nsu + ", tid="
				+ tid + ", establishmentCode=" + establishmentCode + ", acquirerName=" + acquirerName + "]";
	}

}