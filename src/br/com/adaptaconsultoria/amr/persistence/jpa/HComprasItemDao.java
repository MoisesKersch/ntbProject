package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.ComprasItem;
import br.com.adaptaconsultoria.amr.persistence.dao.ComprasItemDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HComprasItemDao extends HDao<ComprasItem> implements ComprasItemDao {

	@Override
	public List<ComprasItem> carregaPorOrder(String cOrderId) throws Exception {
		String query = "SELECT obj FROM ComprasItem obj WHERE obj.cOrderId = :cOrderId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cOrderId", cOrderId);

		return pesquisa(query, parametros);
	}

}