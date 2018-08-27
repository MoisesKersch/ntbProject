package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.MeusPontos;
import br.com.adaptaconsultoria.amr.persistence.dao.MeusPontosDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HMeusPontosDao extends HDao<MeusPontos> implements MeusPontosDao {

	@Override
	public List<MeusPontos> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM MeusPontos obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

}