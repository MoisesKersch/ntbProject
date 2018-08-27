package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Ativacoes;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivacoesDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAtivacoesDao extends HDao<Ativacoes> implements AtivacoesDao {

	@Override
	public List<Ativacoes> carregaPorCBPartnerId(String cBPartnerId) throws Exception {
		String query = "SELECT obj FROM Ativacoes obj WHERE obj.cBPartnerId = :cBPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cBPartnerId", cBPartnerId);

		return pesquisa(query, parametros);
	}

}
