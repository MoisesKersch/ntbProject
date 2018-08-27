package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.CarrinhoCompras;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HCarrinhoComprasDao extends HDao<CarrinhoCompras> implements CarrinhoComprasDao {

	@Override
	public CarrinhoCompras carregaAbertoPorUsuario(String adUserId, String vendaDireta) throws Exception {
		String query = "SELECT obj FROM CarrinhoCompras obj WHERE obj.adUserId = :adUserId AND obj.processado = 'N' AND obj.vendaDireta = :vendaDireta";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		if (vendaDireta.equalsIgnoreCase("true"))
			vendaDireta = "Y";
		else if (vendaDireta.equalsIgnoreCase("false"))
			vendaDireta = "N";

		parametros.put("vendaDireta", vendaDireta);

		return carrega(query, parametros);
	}

	@Override
	public String processaCarrinho(String amrCarrinhoComprasId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_cart_post('" + amrCarrinhoComprasId + "')");
			String retorno = (String) q.getSingleResult();
			return retorno;
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}