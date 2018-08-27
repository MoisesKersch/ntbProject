package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "amr_ativacoes_lcto")
public class AtivacoesLCTO implements Serializable {

	@Id
	@Column(name = "amr_ativacoes_lcto_id")
	private String id;

	@Column(name = "ad_client_id")
	private String adClientId;

//	@Column(name = "ad_org_id")
//	private String adOrgId;

	@Column(name = "amr_ativacao_id")
	private String amrAtivacaoId;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "descricao")
	private String descricao;

	public AtivacoesLCTO() {
		super();
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

//	public String getAdOrgId() {
//		return adOrgId;
//	}
//
//	public void setAdOrgId(String adOrgId) {
//		this.adOrgId = adOrgId;
//	}

	public String getAmrAtivacaoId() {
		return amrAtivacaoId;
	}

	public void setAmrAtivacaoId(String amrAtivacaoId) {
		this.amrAtivacaoId = amrAtivacaoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}