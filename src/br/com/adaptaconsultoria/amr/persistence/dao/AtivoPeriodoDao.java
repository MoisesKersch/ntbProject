package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.AtivoPeriodo;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AtivoPeriodoDao extends Dao<AtivoPeriodo> {

	public AtivoPeriodo getProximoPeriodoParaInserir(String usuarioId) throws Exception;

}