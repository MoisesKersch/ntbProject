package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Graduacao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface GraduacaoDao extends Dao<Graduacao> {

	public Graduacao carregaPorUsuario(String adUserId) throws Exception;

}