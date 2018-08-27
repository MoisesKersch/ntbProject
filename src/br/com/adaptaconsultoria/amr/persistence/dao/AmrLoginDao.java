package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.AmrLogin;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AmrLoginDao extends Dao<AmrLogin> {

	public AmrLogin carregaPorLogin(String login) throws Exception;

}