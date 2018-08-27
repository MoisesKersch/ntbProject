package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.PacoteCategoria;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PacoteCategoriaDao extends Dao<PacoteCategoria> {

	public List<PacoteCategoria> listaPorUsuario(String adUserId) throws Exception;

}