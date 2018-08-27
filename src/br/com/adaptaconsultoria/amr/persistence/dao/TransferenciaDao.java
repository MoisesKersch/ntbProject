package br.com.adaptaconsultoria.amr.persistence.dao;

import java.math.BigDecimal;

import br.com.adaptaconsultoria.amr.model.Transferencia;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface TransferenciaDao extends Dao<Transferencia> {

	public String post(String transferenciaId) throws Exception;
	public BigDecimal taxaTransferencia(String clientId, BigDecimal valor);

}