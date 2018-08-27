package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Arquivo;
import br.com.adaptaconsultoria.amr.persistence.dao.ArquivoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HArquivoDao extends HDao<Arquivo> implements ArquivoDao {

	public static final String TABLE_C_BPARTNER = "291";

	@Override
	public List<Arquivo> listaPorParceiroNegocios(String cBPartnerId) throws Exception {
		String query = "SELECT obj FROM Arquivo obj";
		query += " " + "WHERE obj.adTableId = :adTableId";
		query += " " + "AND obj.adRecordId = :cBPartnerId";
		query += " " + "ORDER BY seqNo";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adTableId", TABLE_C_BPARTNER);
		parametros.put("cBPartnerId", cBPartnerId);

		return pesquisa(query, parametros);
	}

	@Override
	public int getProximoNumeroDaSequencia(String cBPartnerId) throws Exception {
		String query = "SELECT COALESCE(MAX(obj.seqNo), 0) + 10 FROM Arquivo obj";
		query += " " + "WHERE obj.adTableId = :adTableId";
		query += " " + "AND obj.adRecordId = :cBPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adTableId", TABLE_C_BPARTNER);
		parametros.put("cBPartnerId", cBPartnerId);

		Query q = getEntityManager().createQuery(query);
		for (String chave : parametros.keySet()) {
			q.setParameter(chave, parametros.get(chave));
		}
		try {
			return (Integer) q.getSingleResult();
		} catch (NoResultException nre) {
			return 10;
		}
	}

	@Override
	public String getDataType(String extensao) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT ac_datatype(:extensao)");
		q.setParameter("extensao", extensao);
		return (String) q.getSingleResult();
	}

}