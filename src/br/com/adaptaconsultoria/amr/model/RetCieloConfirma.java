package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class RetCieloConfirma implements Serializable {

	private boolean confirmada = false;
	private String codigo;
	private String mensagem;

	public RetCieloConfirma() {
		super();
	}

	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void parse(String retorno) throws Exception {
		String[] ret = retorno.split("&");
		for (int i = 0; i < ret.length; i++) {
			String[] parametro = ret[i].split("=");
			if (parametro[0].equalsIgnoreCase("CODRET"))
				this.codigo = parametro[1];
			if (parametro[0].equalsIgnoreCase("MSGRET"))
				this.mensagem = parametro[1];
		}

		if (this.codigo.equals("0") || this.codigo.equals("1"))
			this.confirmada = true;
	}

}