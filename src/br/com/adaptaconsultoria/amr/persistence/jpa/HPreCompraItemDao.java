package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.PreCompraItem;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraItemDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPreCompraItemDao extends HDao<PreCompraItem> implements PreCompraItemDao {

	@Override
	public List<PreCompraItem> listaPorPreCompra(String amrPreCompraId) throws Exception {
		String query = "SELECT obj FROM PreCompraItem obj WHERE obj.preCompra.id = :amrPreCompraId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("amrPreCompraId", amrPreCompraId);

		return pesquisa(query, parametros);
	}

}