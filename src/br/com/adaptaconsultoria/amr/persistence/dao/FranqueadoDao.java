package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Franqueado;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface FranqueadoDao extends Dao<Franqueado> {

	public Franqueado carregaPorUsuario(String adUserId) throws Exception;
	public Franqueado carregaPorCodigo(String codigo) throws Exception;

}