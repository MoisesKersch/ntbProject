package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.FormaPagamento;
import br.com.adaptaconsultoria.amr.persistence.dao.FormaPagamentoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HFormaPagamentoDao extends HDao<FormaPagamento> implements FormaPagamentoDao {

	@Override
	public List<FormaPagamento> porAdClientId(String adClientId) {
		String query = "SELECT obj FROM FormaPagamento obj WHERE obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);

		return pesquisa(query, parametros);
	}

}