package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.UsuarioRead;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioReadDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

public class HUsuarioReadDao extends HDao<UsuarioRead> implements UsuarioReadDao {

	@Override
	public List<UsuarioRead> listaPorUsuarioBase(String adUserId) throws Exception {
		String query = "SELECT obj FROM UsuarioRead obj WHERE obj.base = :adUserId";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adUserId", adUserId);

		return pesquisa(query, params);
	}

}