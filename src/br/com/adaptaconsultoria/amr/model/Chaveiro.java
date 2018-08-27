package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.adaptaconsultoria.amr.util.FormatUtilities;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Chaveiro implements Serializable {

	private String senhaAtual;
	private String senhaAtualFinanceira;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String senhaNova;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String senhaNova2;
	private boolean temSenha;

	public Chaveiro() {
		super();
	}

	public String getSenhaAtualFinanceira() {
		return senhaAtualFinanceira;
	}

	public void setSenhaAtualFinanceira(String senhaAtualFinanceira) {
		this.senhaAtualFinanceira = senhaAtualFinanceira;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaNova2() {
		return senhaNova2;
	}

	public void setSenhaNova2(String senhaNova2) {
		this.senhaNova2 = senhaNova2;
	}

	public boolean senhaAtualIgual(String senha) throws Exception {
		return senha.equals(FormatUtilities.sha1Base64(this.senhaAtual));
	}

	public boolean novasSenhasIguais() throws Exception {
		return this.senhaNova.equals(this.senhaNova2);
	}

	public boolean isTemSenha() {
		return temSenha;
	}

	public void setTemSenha(boolean temSenha) {
		this.temSenha = temSenha;
	}

}