package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Aceite;
import br.com.adaptaconsultoria.amr.persistence.dao.AceiteDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

public class HAceiteDao extends HDao<Aceite> implements AceiteDao {

	@Override
	public Aceite carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Aceite obj";
		query += " " + "WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return carrega(query, parametros);
	}

}