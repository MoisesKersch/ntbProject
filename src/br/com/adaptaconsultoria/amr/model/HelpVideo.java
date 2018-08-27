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
@Table(name = "amr_helpvideo")
public class HelpVideo implements Serializable {

	@Id
	@Column(name = "amr_helpvideo_id")
	private String id;
	@Column(name = "name")
	private String nome;
	
	@Column(name = "ad_client_id")
	private String adClientId;
	
	@Column(name = "description")
	private String descricao;
	
	@Column(name = "urlembed")
	private String urlEmbed;
	@Column(name = "palavraschave")
	private String palavrasChave;

	public HelpVideo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlEmbed() {
		return urlEmbed;
	}

	public void setUrlEmbed(String urlEmbed) {
		this.urlEmbed = urlEmbed;
	}

	public String getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public String getAdClientId() {
		return adClientId;
	}

	public void setAdClientId(String adClientId) {
		this.adClientId = adClientId;
	}
	
}