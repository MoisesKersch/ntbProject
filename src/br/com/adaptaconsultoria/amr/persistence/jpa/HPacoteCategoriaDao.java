package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.PacoteCategoria;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteCategoriaDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPacoteCategoriaDao extends HDao<PacoteCategoria> implements PacoteCategoriaDao {

	@Override
	public List<PacoteCategoria> listaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM PacoteCategoria obj WHERE obj.adUserId = :adUserId and obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return pesquisa(query, parametros);
	}

}