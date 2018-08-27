package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.PacoteUpgradeLinha;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PacoteUpgradeLinhaDao extends Dao<PacoteUpgradeLinha> {

	public List<PacoteUpgradeLinha> listaPorPacote(String pacoteUpgradeId) throws Exception;

}