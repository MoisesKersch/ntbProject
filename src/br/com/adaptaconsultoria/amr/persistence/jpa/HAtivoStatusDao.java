package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AtivoStatus;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoStatusDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAtivoStatusDao extends HDao<AtivoStatus> implements AtivoStatusDao {

	@Override
	public AtivoStatus carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM AtivoStatus obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return carrega(query, parametros);
	}

}