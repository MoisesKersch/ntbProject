package br.com.adaptaconsultoria.amr.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class FiltroExtrato implements Serializable {

	@NotNull(message = "{campo.obrigatorio}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicial;
	@NotNull(message = "{campo.obrigatorio}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFinal;

	public FiltroExtrato() {
		super();
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}