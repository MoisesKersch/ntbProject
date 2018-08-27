package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AtivoAgenda;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoAgendaDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAtivoAgendaDao extends HDao<AtivoAgenda> implements AtivoAgendaDao {

	@Override
	public List<AtivoAgenda> listaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM AtivoAgenda obj WHERE obj.adUserId = :adUserId ORDER BY obj.periodo.dataProgramada DESC";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

}