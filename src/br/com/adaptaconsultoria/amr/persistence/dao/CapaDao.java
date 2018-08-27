package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Capa;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface CapaDao extends Dao<Capa> {

	public Capa carregaPorClient(String adClientId) throws Exception;

}