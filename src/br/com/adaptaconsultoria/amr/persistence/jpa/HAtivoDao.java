package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Ativo;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAtivoDao extends HDao<Ativo> implements AtivoDao {

	@Override
	public List<Ativo> listaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Ativo obj WHERE obj.adUserId = :adUserId ORDER BY obj.nome";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public String pago(String adUserId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_ativopago('" + adUserId + "', null)");
			String pago = (String) q.getSingleResult();
			return pago;
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}