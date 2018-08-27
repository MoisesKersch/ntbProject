package br.com.adaptaconsultoria.amr.builder.object;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class SuporteForm implements Serializable {

	@NotEmpty(message = "*")
	private String assunto;

	@NotEmpty(message = "*")
	private String descricao;

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
