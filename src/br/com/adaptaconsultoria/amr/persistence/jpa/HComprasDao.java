package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Compras;
import br.com.adaptaconsultoria.amr.persistence.dao.ComprasDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HComprasDao extends HDao<Compras> implements ComprasDao {

	@Override
	public List<Compras> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Compras obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

}