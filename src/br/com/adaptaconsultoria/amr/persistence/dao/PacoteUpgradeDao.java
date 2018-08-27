package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.PacoteUpgrade;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PacoteUpgradeDao extends Dao<PacoteUpgrade> {

	public List<PacoteUpgrade> carregaPorUsuario(String adUserId) throws Exception;

	public PacoteUpgrade carregaPorUsuario(String id, String adUserId) throws Exception;
	
	public PacoteUpgrade getPacoteUpgradeDetalha(String adUserId, String upgrade) throws Exception;

}