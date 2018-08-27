package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Config;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ConfigDao extends Dao<Config> {

	public Config carregaPorAdClientIdAndIsActiveAndSemPlanoId(String adClientId) throws Exception;

}
