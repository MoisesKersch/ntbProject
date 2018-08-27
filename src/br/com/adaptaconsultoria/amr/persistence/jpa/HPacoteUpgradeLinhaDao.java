package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.PacoteUpgradeLinha;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeLinhaDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPacoteUpgradeLinhaDao extends HDao<PacoteUpgradeLinha> implements PacoteUpgradeLinhaDao {

	@Override
	public List<PacoteUpgradeLinha> listaPorPacote(String pacoteUpgradeId) throws Exception {
		String query = "SELECT obj FROM PacoteUpgradeLinha obj WHERE obj.amrPacoteUpgradeId = :pacoteUpgradeId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("pacoteUpgradeId", pacoteUpgradeId);

		return pesquisa(query, parametros);
	}

}