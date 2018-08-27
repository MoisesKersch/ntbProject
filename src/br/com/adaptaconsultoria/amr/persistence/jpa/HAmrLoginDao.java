package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AmrLogin;
import br.com.adaptaconsultoria.amr.persistence.dao.AmrLoginDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAmrLoginDao extends HDao<AmrLogin> implements AmrLoginDao {

	@Override
	public AmrLogin carregaPorLogin(String login) throws Exception {
		String query = "SELECT obj FROM AmrLogin obj WHERE UPPER(obj.username) = UPPER(:login) AND obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("login", login);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return carrega(query, parametros);
	}

}
