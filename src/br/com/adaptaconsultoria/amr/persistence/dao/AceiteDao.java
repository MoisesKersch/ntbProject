package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Aceite;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AceiteDao extends Dao<Aceite> {

	public Aceite carregaPorUsuario(String adUserId) throws Exception;

}