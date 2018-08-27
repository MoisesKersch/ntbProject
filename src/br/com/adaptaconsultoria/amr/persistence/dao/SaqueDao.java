package br.com.adaptaconsultoria.amr.persistence.dao;

import java.math.BigDecimal;

import br.com.adaptaconsultoria.amr.model.Saque;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface SaqueDao extends Dao<Saque> {
	public BigDecimal taxaSaque(String clientId);
	public BigDecimal taxaSaque(String clientId, BigDecimal valor);
}