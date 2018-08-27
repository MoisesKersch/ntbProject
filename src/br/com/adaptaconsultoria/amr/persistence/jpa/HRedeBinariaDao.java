package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.RedeBinaria;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeBinariaDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HRedeBinariaDao extends HDao<RedeBinaria> implements RedeBinariaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<RedeBinaria> listaPorID(String ids) throws Exception {

		getEntityManager().clear();

		String query = "SELECT obj FROM RedeBinaria obj";
		Map<String, Object> parametros = new HashMap<String, Object>();
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			if (i == 0)
				query += " WHERE obj.id IN (:id0)";
			else
				query += " OR obj.id IN (:id" + i + ")";

			parametros.put("id" + i, id[i]);
		}

		Query q = getEntityManager().createQuery(query);
		for (String chave : parametros.keySet()) {
			q.setParameter(chave, parametros.get(chave));
		}
		try {
			return q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public String carregaIDPorUsuario(String adUserId) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_montarede('" + adUserId + "', '0')");
		return (String) q.getSingleResult();
	}

	@Override
	public String carregaNoRaiz(String adUserId) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_usuarioraiz('" + adUserId + "')");
		return (String) q.getSingleResult();
	}

	@Override
	public Boolean descente(String adUserRaizId, String adUserId) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_descendente('" + adUserRaizId + "', '" + adUserId + "')");
		String resultado = (String) q.getSingleResult();
		return resultado.equalsIgnoreCase("Y");
	}

	@Override
	public RedeBinaria carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM RedeBinaria obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return carrega(query, parametros);
	}

	@Override
	public RedeBinaria carregaPorCodigoDeParceiroDeNegocios(String codigo) throws Exception {
		String query = "SELECT rb FROM RedeBinaria rb WHERE rb.codigoParceiroNegocios = :codigo AND rb.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return carrega(query, parametros);
	}

}