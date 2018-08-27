package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.MetodoEntrega;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HMetodoEntregaDao extends HDao<MetodoEntrega> implements MetodoEntregaDao {

	@Override
	public List<MetodoEntrega> listaPorPesoMaximo(String adClientId, String amrPreCompraId, BigDecimal pesoMaximo) throws Exception {
		if (pesoMaximo == null) {
			throw new Exception("Parâmetro \\\"Peso\\\" está NULO");
		}
		String query = "SELECT obj FROM MetodoEntrega obj WHERE obj.adClientId = :adClientId AND obj.amrPreCompraId = :amrPreCompraId AND (obj.peso IS NULL OR obj.peso >= :peso) ORDER BY obj.metodoentrega";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);
		parametros.put("peso", pesoMaximo);
		parametros.put("amrPreCompraId", amrPreCompraId);

		return pesquisa(query, parametros);
	}

	@SuppressWarnings("unchecked")
	public List<MetodoEntrega> porPreCompra(String preCompraId) {
		Query q = getEntityManager().createNativeQuery("SELECT * FROM amr_metodoentrega('" + preCompraId + "', NULL)", MetodoEntrega.class);
		List<MetodoEntrega> resultado = q.getResultList();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public MetodoEntrega porPreCompraMetodoEntrega(String preCompraId, String metodoEntregaId) {
		Query q = getEntityManager().createNativeQuery("SELECT * FROM amr_metodoentrega('" + preCompraId + "', '" + metodoEntregaId + "')", MetodoEntrega.class);
		List<MetodoEntrega> resultado = q.getResultList();
		try {
			return resultado.get(0);
		} catch (Exception e) {
			return null;
		}
	}

}