package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Config;
import br.com.adaptaconsultoria.amr.persistence.dao.ConfigDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HConfigDao extends HDao<Config> implements ConfigDao {

	@Override
	public Config carregaPorAdClientIdAndIsActiveAndSemPlanoId(String adClientId) throws Exception {
		String query = "SELECT obj FROM Config obj WHERE obj.adClientId = :adClientId and isActive = :isActive and amrPlanoId is null";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);
		parametros.put("isActive"  , "Y"       );

		return carrega(query, parametros);
	}

}
