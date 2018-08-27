package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ac_endereco")
public class EnderecoView implements Serializable {

	@Id
	@Column(name = "c_bpartner_location_id")
	private String id;

	@Column(name = "c_bpartner_id ")
	private String bPartner;

	@Column(name = "c_location_id")
	private String locationId;

	@Column(name = "ad_client_id")
	private String adClientId;

	@Column(name = "ad_org_id")
	private String orgId;

	@Column(name = "rua")
	private String rua;

	@Column(name = "numero")
	private String numero;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "estado")
	private String estado;

	@Column(name = "uf")
	private String uf;

	@Column(name = "cobranca")
	private String cobranca;

	@Column(name = "fone")
	private String fone;

	@Column(name = "cep")
	private String cep;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "pais")
	private String pais;

	@Column(name = "codigopais")
	private String codigoPais;

	@Column(name = "cod_pais_bacen")
	private String codPaisBacen;

	@Column(name = "cod_municipio_ibge")
	private String codMunicipioIbge;

	@Column(name = "codigo_postal")
	private String codigoPostal;

	@Column(name = "referencia")
	private String referencia;

	public EnderecoView() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getbPartner() {
		return bPartner;
	}

	public void setbPartner(String bPartner) {
		this.bPartner = bPartner;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(String adClientId) {
		this.adClientId = adClientId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCobranca() {
		return cobranca;
	}

	public void setCobranca(String cobranca) {
		this.cobranca = cobranca;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodPaisBacen() {
		return codPaisBacen;
	}

	public void setCodPaisBacen(String codPaisBacen) {
		this.codPaisBacen = codPaisBacen;
	}

	public String getCodMunicipioIbge() {
		return codMunicipioIbge;
	}

	public void setCodMunicipioIbge(String codMunicipioIbge) {
		this.codMunicipioIbge = codMunicipioIbge;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}