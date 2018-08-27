package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasItemDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HCarrinhoComprasItemDao extends HDao<CarrinhoComprasItem> implements CarrinhoComprasItemDao {

	@Override
	public List<CarrinhoComprasItem> listaPorCarrinhoDeCompras(String amrCarrinhoComprasId) throws Exception {
		String query = "SELECT obj FROM CarrinhoComprasItem obj WHERE obj.carrinho.id = :amrCarrinhoComprasId ORDER BY created DESC";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("amrCarrinhoComprasId", amrCarrinhoComprasId);

		return pesquisa(query, parametros);
	}

}