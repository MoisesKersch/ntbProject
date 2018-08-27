package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dom4j.Element;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.adaptaconsultoria.amr.util.FormatUtilities;
import br.com.adaptaconsultoria.amr.util.io.Dom4jUtil;

@SuppressWarnings("serial")
public class Cadastro implements Serializable {

	private String patrocinador;
	@NotNull
	@NotEmpty(message = "*")
	private String nome;
	@NotNull
	@NotEmpty(message = "*")
	private String cpfCnpj;
	@NotNull
	@NotEmpty(message = "*")
	private String dataNascimento;
	@NotNull
	@NotEmpty(message = "*")
	private String genero = "M";
	@Transient
	private String tipoPessoa = "F";
	@NotNull
	@NotEmpty(message = "*")
	private String email;
	@NotNull
	@NotEmpty(message = "*")
	private String usuario;
	@NotNull
	@NotEmpty(message = "*")
	private String senha;
//	@NotNull
//	@NotEmpty(message = "*")
	private String confSenha;
	@NotNull
	@NotEmpty(message = "*")
	private String cep;
	@NotNull
	@NotEmpty(message = "*")
	private String rua;
	@NotNull
	@NotEmpty(message = "*")
	private String numero;
	private String complemento;
	@NotNull
	@NotEmpty(message = "*")
	private String bairro;
	@NotNull
	@NotEmpty(message = "*")
	private String pais = "139";
	@NotNull(message = "")
	@NotEmpty(message = "*")
	private String estado;
	// private String estado = "422";
	@NotNull(message = "")
	@NotEmpty(message = "*")
	private String cidade;
	// private String cidade = "3B0AA478F1B14A559A8D296C81066C0C";
	private String referencia;
	private boolean residencial = true;
	private boolean correspondencia = true;
	private boolean cobranca = true;
	
	@NotNull
	@NotEmpty(message = "*")
	private String tipoTelefone1;
	
//	@NotNull
//	@NotEmpty(message = "*")
	private String operadoraTelefone1;
	
	@NotNull
	@NotEmpty(message = "*")
	private String dddTelefone1;
	
	@NotNull
	@NotEmpty(message = "*")
	private String numeroTelefone1;
	
	private String tipoTelefone2;
	private String operadoraTelefone2;
	private String dddTelefone2;
	private String numeroTelefone2;
	private String tipoTelefone3;
	private String operadoraTelefone3;
	private String dddTelefone3;
	private String numeroTelefone3;
	private List<Produto> produtos;
	private String cBPartnerId;
	private String adUserId;
	private String cBPartnerLocationId;
	private String cLocationId;
	private String crescimento;
	private String senhaFinanceira;
	private boolean liberaCrescimento = false;
	@Transient
	private boolean aceito = false;
	@Transient
	private boolean automatico = false;
	private String nit;

	public Cadastro() {
		super();
	}

	public String getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador != null ? patrocinador.toUpperCase() : patrocinador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario != null ? usuario.toUpperCase() : usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public boolean isResidencial() {
		return residencial;
	}

	public void setResidencial(boolean residencial) {
		this.residencial = residencial;
	}

	public boolean isCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(boolean correspondencia) {
		this.correspondencia = correspondencia;
	}

	public boolean isCobranca() {
		return cobranca;
	}

	public void setCobranca(boolean cobranca) {
		this.cobranca = cobranca;
	}

	public String getTipoTelefone1() {
		return tipoTelefone1;
	}

	public void setTipoTelefone1(String tipoTelefone1) {
		this.tipoTelefone1 = tipoTelefone1;
	}

	public String getOperadoraTelefone1() {
		return operadoraTelefone1;
	}

	public void setOperadoraTelefone1(String operadoraTelefone1) {
		this.operadoraTelefone1 = operadoraTelefone1;
	}

	public String getDddTelefone1() {
		return dddTelefone1;
	}

	public void setDddTelefone1(String dddTelefone1) {
		this.dddTelefone1 = dddTelefone1;
	}

	public String getNumeroTelefone1() {
		return numeroTelefone1;
	}

	public void setNumeroTelefone1(String numeroTelefone1) {
		this.numeroTelefone1 = numeroTelefone1;
	}

	public String getTipoTelefone2() {
		return tipoTelefone2;
	}

	public void setTipoTelefone2(String tipoTelefone2) {
		this.tipoTelefone2 = tipoTelefone2;
	}

	public String getOperadoraTelefone2() {
		return operadoraTelefone2;
	}

	public void setOperadoraTelefone2(String operadoraTelefone2) {
		this.operadoraTelefone2 = operadoraTelefone2;
	}

	public String getDddTelefone2() {
		return dddTelefone2;
	}

	public void setDddTelefone2(String dddTelefone2) {
		this.dddTelefone2 = dddTelefone2;
	}

	public String getNumeroTelefone2() {
		return numeroTelefone2;
	}

	public void setNumeroTelefone2(String numeroTelefone2) {
		this.numeroTelefone2 = numeroTelefone2;
	}

	public String getTipoTelefone3() {
		return tipoTelefone3;
	}

	public void setTipoTelefone3(String tipoTelefone3) {
		this.tipoTelefone3 = tipoTelefone3;
	}

	public String getOperadoraTelefone3() {
		return operadoraTelefone3;
	}

	public void setOperadoraTelefone3(String operadoraTelefone3) {
		this.operadoraTelefone3 = operadoraTelefone3;
	}

	public String getDddTelefone3() {
		return dddTelefone3;
	}

	public void setDddTelefone3(String dddTelefone3) {
		this.dddTelefone3 = dddTelefone3;
	}

	public String getNumeroTelefone3() {
		return numeroTelefone3;
	}

	public void setNumeroTelefone3(String numeroTelefone3) {
		this.numeroTelefone3 = numeroTelefone3;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getcBPartnerId() {
		return cBPartnerId;
	}

	public void setcBPartnerId(String cBPartnerId) {
		this.cBPartnerId = cBPartnerId;
	}

	public String getAdUserId() {
		return adUserId;
	}

	public void setAdUserId(String adUserId) {
		this.adUserId = adUserId;
	}

	public String getcBPartnerLocationId() {
		return cBPartnerLocationId;
	}

	public void setcBPartnerLocationId(String cBPartnerLocationId) {
		this.cBPartnerLocationId = cBPartnerLocationId;
	}

	public String getcLocationId() {
		return cLocationId;
	}

	public void setcLocationId(String cLocationId) {
		this.cLocationId = cLocationId;
	}

	public String getCrescimento() {
		return crescimento;
	}

	public void setCrescimento(String crescimento) {
		this.crescimento = crescimento;
	}

	public String getSenhaFinanceira() {
		return senhaFinanceira;
	}

	public void setSenhaFinanceira(String senhaFinanceira) {
		this.senhaFinanceira = senhaFinanceira;
	}

	public boolean isLiberaCrescimento() {
		return liberaCrescimento;
	}

	public void setLiberaCrescimento(String liberaCrescimento) {
		this.liberaCrescimento = liberaCrescimento != null && liberaCrescimento.equals("Y");
	}

	public void setLiberaCrescimento(boolean liberaCrescimento) {
		this.liberaCrescimento = liberaCrescimento;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public boolean isAceito() {
		return aceito;
	}

	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}

	public boolean isAutomatico() {
		return automatico;
	}

	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getConfSenha() {
		return confSenha;
	}

	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}
	
	public boolean novasSenhasIguais() throws Exception {
		return this.senha.equals(this.confSenha);
	}

	public String toElement() throws Exception {

		String xml = "<parceiro>";

		xml += "<patrocinador>" + this.patrocinador.toUpperCase() + "</patrocinador>";
		xml += "<nome>" + this.nome + "</nome>";
		xml += "<cpfCnpj>" + this.cpfCnpj + "</cpfCnpj>";
		xml += "<dataNascimento>" + this.dataNascimento + "</dataNascimento>";
		xml += "<genero>" + this.genero + "</genero>";
		xml += "<email>" + this.email + "</email>";
		xml += "<usuario>" + this.usuario.toUpperCase() + "</usuario>";
		xml += "<senha>" + FormatUtilities.sha1Base64(this.senha) + "</senha>";
		xml += "<nit>" + this.nit + "</nit>";

		xml += "<telefones>";

		xml += "<telefone>";
		xml += "<tipo>" + "Celular" + "</tipo>";
		xml += "<operadora>" + this.operadoraTelefone1 + "</operadora>";
		xml += "<ddd>" + this.dddTelefone1 + "</ddd>";
		xml += "<numero>" + this.numeroTelefone1 + "</numero>";
		xml += "</telefone>";

		xml += "<telefone>";
		xml += "<tipo>" + "Residencial" + "</tipo>";
		xml += "<operadora>" + this.operadoraTelefone2 + "</operadora>";
		xml += "<ddd>" + this.dddTelefone2 + "</ddd>";
		xml += "<numero>" + this.numeroTelefone2 + "</numero>";
		xml += "</telefone>";

		xml += "<telefone>";
		xml += "<tipo>" + "Outro" + "</tipo>";
		xml += "<operadora>" + this.operadoraTelefone3 + "</operadora>";
		xml += "<ddd>" + this.dddTelefone3 + "</ddd>";
		xml += "<numero>" + this.numeroTelefone3 + "</numero>";
		xml += "</telefone>";

		xml += "</telefones>";

		xml += "<enderecos>";
		xml += "<endereco>";
		xml += "<cep>" + this.cep + "</cep>";
		xml += "<rua>" + this.rua + "</rua>";
		xml += "<numero>" + this.numero + "</numero>";
		xml += "<complemento>" + this.complemento + "</complemento>";
		xml += "<bairro>" + this.bairro + "</bairro>";
		xml += "<pais>" + this.pais + "</pais>";
		xml += "<regiao>" + this.estado + "</regiao>";
		xml += "<cidade>" + this.cidade + "</cidade>";
		xml += "<referencia>" + this.referencia + "</referencia>";
		xml += "<residencial>" + (this.residencial ? "S" : "N") + "</residencial>";
		xml += "<correspondencia>" + (this.correspondencia ? "S" : "N") + "</correspondencia>";
		xml += "<cobranca>" + (this.cobranca ? "S" : "N") + "</cobranca>";

		xml += "</endereco>";
		xml += "</enderecos>";

		xml += "</parceiro>";
		return xml;
	}

	public Element toElement2() throws Exception {

		Element eCadastro = Dom4jUtil.createElement("parceiro");
		eCadastro.add(Dom4jUtil.createElement("patrocinador", this.patrocinador.toUpperCase()));
		eCadastro.add(Dom4jUtil.createElement("nome", this.nome));
		eCadastro.add(Dom4jUtil.createElement("cpfCnpj", this.cpfCnpj));
		eCadastro.add(Dom4jUtil.createElement("dataNascimento", this.dataNascimento));
		eCadastro.add(Dom4jUtil.createElement("genero", this.genero));
		eCadastro.add(Dom4jUtil.createElement("email", this.email));
		eCadastro.add(Dom4jUtil.createElement("nit", this.nit));
		eCadastro.add(Dom4jUtil.createElement("usuario", this.usuario.toUpperCase()));
		eCadastro.add(Dom4jUtil.createElement("senha", FormatUtilities.sha1Base64(this.senha)));

		Element eTelefones = Dom4jUtil.createElement("telefones");
		try {
			Element eTelefone = Dom4jUtil.createElement("telefone");
			eTelefone.add(Dom4jUtil.createElement("tipo", "Celular"));
			eTelefone.add(Dom4jUtil.createElement("operadora", this.operadoraTelefone1));
			eTelefone.add(Dom4jUtil.createElement("ddd", this.dddTelefone1));
			eTelefone.add(Dom4jUtil.createElement("numero", this.numeroTelefone1));
			eTelefones.add(eTelefone);
		} catch (Exception e) {
		}
		try {
			Element eTelefone = Dom4jUtil.createElement("telefone");
			eTelefone.add(Dom4jUtil.createElement("tipo", "Residencial"));
			eTelefone.add(Dom4jUtil.createElement("operadora", this.operadoraTelefone2));
			eTelefone.add(Dom4jUtil.createElement("ddd", this.dddTelefone2));
			eTelefone.add(Dom4jUtil.createElement("numero", this.numeroTelefone2));
			eTelefones.add(eTelefone);
		} catch (Exception e) {
		}
		try {
			Element eTelefone = Dom4jUtil.createElement("telefone");
			eTelefone.add(Dom4jUtil.createElement("tipo", "Outro"));
			eTelefone.add(Dom4jUtil.createElement("operadora", this.operadoraTelefone3));
			eTelefone.add(Dom4jUtil.createElement("ddd", this.dddTelefone3));
			eTelefone.add(Dom4jUtil.createElement("numero", this.numeroTelefone3));
			eTelefones.add(eTelefone);
		} catch (Exception e) {
		}
		eCadastro.add(eTelefones);

		Element eEnderecos = Dom4jUtil.createElement("enderecos");
		Element eEndereco = Dom4jUtil.createElement("endereco");
		eEndereco.add(Dom4jUtil.createElement("cep", this.cep));
		eEndereco.add(Dom4jUtil.createElement("rua", this.rua));
		eEndereco.add(Dom4jUtil.createElement("numero", this.numero));
		eEndereco.add(Dom4jUtil.createElement("complemento", this.complemento));
		eEndereco.add(Dom4jUtil.createElement("bairro", this.bairro));
		eEndereco.add(Dom4jUtil.createElement("pais", this.pais));
		eEndereco.add(Dom4jUtil.createElement("regiao", this.estado));
		eEndereco.add(Dom4jUtil.createElement("cidade", this.cidade));
		eEndereco.add(Dom4jUtil.createElement("referencia", this.referencia));
		eEndereco.add(Dom4jUtil.createElement("residencial", this.residencial ? "S" : "N"));
		eEndereco.add(Dom4jUtil.createElement("correspondencia", this.correspondencia ? "S" : "N"));
		eEndereco.add(Dom4jUtil.createElement("cobranca", this.cobranca ? "S" : "N"));
		eEnderecos.add(eEndereco);
		eCadastro.add(eEnderecos);

		/*
		 * Element eProdutos = Dom4jUtil.createElement("produtos"); for (int i = 0; i < this.produtos.size(); i++) { if (this.produtos.get(i).isSelecionado())
		 * eProdutos.add(produtos.get(i).toElement()); } eCadastro.add(eProdutos);
		 */

		return eCadastro;
	}
}