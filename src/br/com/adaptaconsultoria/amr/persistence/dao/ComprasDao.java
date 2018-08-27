package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Compras;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ComprasDao extends Dao<Compras> {

	public List<Compras> carregaPorUsuario(String adUserId) throws Exception;

}