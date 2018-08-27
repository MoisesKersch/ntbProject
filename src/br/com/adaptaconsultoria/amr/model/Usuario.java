package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ad_user")
public class Usuario implements Serializable {

	@Id
	@Column(name = "ad_user_id")
	private String id;
	@Column(name = "ad_client_id")
	private String adClientId;
	@Column(name = "ad_org_id")
	private String adOrgId;
	@Column(name = "username")
	private String login;
	@Column(name = "password")
	private String senha;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "c_bpartner_id")
	private ParceiroNegocios parceiroNegocios;
	@Column(name = "em_amr_senhafinanceira")
	private String senhaFinanceira;
	@Column(name = "em_amr_crescimento")
	private String crescimento;
	@Column(name = "firstname")
	private String primeiroNome;
	@Column(name = "lastname")
	private String sobreNome;
	@Column(name = "name")
	private String nome;
	@ManyToOne
	@JoinColumn(name = "em_amr_usuariobase", referencedColumnName = "ad_user_id")
	private Usuario usuarioBase;
	@Column(name = "em_amr_readonly")
	private String readOnly = "N";
	@Column(name = "em_amr_backofficeskin")
	private String skin;
	@Column(name = "em_amr_solicitousenha")
	private String solicitouNovaSenha;
	@Column(name = "em_amr_novasenha")
	private String novaSenha;

	public Usuario() {
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

	public String getAdOrgId() {
		return adOrgId;
	}

	public void setAdOrgId(String adOrgId) {
		this.adOrgId = adOrgId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ParceiroNegocios getParceiroNegocios() {
		return parceiroNegocios;
	}

	public void setParceiroNegocios(ParceiroNegocios parceiroNegocios) {
		this.parceiroNegocios = parceiroNegocios;
	}

	public String getSenhaFinanceira() {
		return senhaFinanceira;
	}

	public void setSenhaFinanceira(String senhaFinanceira) {
		this.senhaFinanceira = senhaFinanceira;
	}

	public String getCrescimento() {
		return crescimento;
	}

	public void setCrescimento(String crescimento) {
		this.crescimento = crescimento;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
		if (nome != null && !nome.isEmpty()) {
			if (!nome.contains(" ")) {
				this.primeiroNome = nome;
			} else {
				int index = nome.indexOf(" ");
				this.primeiroNome = nome.substring(0, index).trim();
				this.sobreNome = nome.substring(index, nome.length()).trim();
			}
		}
	}

	public Usuario getUsuarioBase() {
		return usuarioBase;
	}

	public void setUsuarioBase(Usuario usuarioBase) {
		this.usuarioBase = usuarioBase;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getSolicitouNovaSenha() {
		return solicitouNovaSenha;
	}

	public void setSolicitouNovaSenha(String solicitouNovaSenha) {
		this.solicitouNovaSenha = solicitouNovaSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}