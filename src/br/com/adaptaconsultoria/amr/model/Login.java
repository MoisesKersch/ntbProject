package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class Login implements Serializable {

	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String login;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String senha;

	public Login() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login != null ? login.toUpperCase() : login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}