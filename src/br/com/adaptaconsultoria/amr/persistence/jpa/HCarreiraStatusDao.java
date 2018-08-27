package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.CarreiraStatus;
import br.com.adaptaconsultoria.amr.persistence.dao.CarreiraStatusDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HCarreiraStatusDao extends HDao<CarreiraStatus> implements CarreiraStatusDao {

	@Override
	public List<CarreiraStatus> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM CarreiraStatus obj WHERE obj.adUserId = :adUserId ORDER BY meta DESC";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

}