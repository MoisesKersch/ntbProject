package br.com.adaptaconsultoria.amr.springmvc.form;

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
public class CrescimentoForm {

	private boolean liberaCrescimento = false;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String crescimento;

	public CrescimentoForm() {
		super();
	}

	public String getCrescimento() {
		return crescimento;
	}

	public void setCrescimento(String crescimento) {
		this.crescimento = crescimento;
	}

	public boolean isLiberaCrescimento() {
		return liberaCrescimento;
	}

	public void setLiberaCrescimento(boolean liberaCrescimento) {
		this.liberaCrescimento = liberaCrescimento;
	}

}