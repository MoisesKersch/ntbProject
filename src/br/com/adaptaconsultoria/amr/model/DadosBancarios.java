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
public class DadosBancarios implements Serializable {

	private String cBPartnerId;
	private String adUserId;
	@NotNull(message = "{campo.obrigatorio}")
	private String banco;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String agencia;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String conta;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String contaDV;
	@NotNull
	@NotEmpty(message = "{campo.obrigatorio}")
	private String senhaFinanceira;
	private String operacao;

	public DadosBancarios() {
		super();
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

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getContaDV() {
		return contaDV;
	}

	public void setContaDV(String contaDV) {
		this.contaDV = contaDV;
	}

	public String getSenhaFinanceira() {
		return senhaFinanceira;
	}

	public void setSenhaFinanceira(String senhaFinanceira) {
		this.senhaFinanceira = senhaFinanceira;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public boolean isDadosValidos() throws Exception {
		if (this.banco == null || this.banco.isEmpty())
			return false;
		if (this.agencia == null || this.agencia.isEmpty())
			return false;
		if (this.conta == null || this.conta.isEmpty())
			return false;
		if (this.contaDV == null || this.contaDV.isEmpty())
			return false;

		return true;
	}

}