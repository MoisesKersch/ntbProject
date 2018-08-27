package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.SaldoResumido;
import br.com.adaptaconsultoria.amr.persistence.dao.SaldoResumidoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HSaldoResumidoDao extends HDao<SaldoResumido> implements SaldoResumidoDao {

	@Override
	public List<SaldoResumido> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM SaldoResumido obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

}