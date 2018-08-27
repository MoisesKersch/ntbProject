package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.AtivoStatus;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AtivoStatusDao extends Dao<AtivoStatus> {

	public AtivoStatus carregaPorUsuario(String adUserId) throws Exception;

}