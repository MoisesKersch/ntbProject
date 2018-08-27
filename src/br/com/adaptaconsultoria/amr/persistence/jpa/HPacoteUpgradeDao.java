package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.PacoteUpgrade;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPacoteUpgradeDao extends HDao<PacoteUpgrade> implements PacoteUpgradeDao {

	@Override
	public List<PacoteUpgrade> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM PacoteUpgrade obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public PacoteUpgrade carregaPorUsuario(String id, String adUserId) throws Exception {
		String query = "SELECT obj FROM PacoteUpgrade obj WHERE obj.adUserId = :adUserId AND obj.id = :id";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("adUserId", adUserId);

		return carrega(query, parametros);
	}
	
	@Override
	public PacoteUpgrade getPacoteUpgradeDetalha(String adUserId, String upgrade) throws Exception 
	{
		String query = "SELECT obj FROM PacoteUpgrade obj WHERE obj.adUserId = :adUserId and obj.upgrade = :upgrade";

		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("adUserId", adUserId);
		parametros.put("upgrade", upgrade);

		return carrega(query, parametros);
	}

}