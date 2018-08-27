package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Graduacao;
import br.com.adaptaconsultoria.amr.persistence.dao.GraduacaoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HGraduacaoDao extends HDao<Graduacao> implements GraduacaoDao {

	@Override
	public Graduacao carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Graduacao obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return carrega(query, parametros);
	}

}