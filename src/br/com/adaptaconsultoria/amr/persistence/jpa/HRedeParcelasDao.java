package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.RedeParcelas;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeParcelasDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HRedeParcelasDao extends HDao<RedeParcelas> implements RedeParcelasDao {

	@Override
	public List<RedeParcelas> listaPorBandeira(String bandeira, String amrPreCompraId) throws Exception {
		String query = "SELECT obj FROM RedeParcelas obj WHERE obj.bandeira = :bandeira AND obj.amrPreCompraId = :amrPreCompraId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("bandeira", bandeira);
		parametros.put("amrPreCompraId", amrPreCompraId);

		return pesquisa(query, parametros);
	}

}