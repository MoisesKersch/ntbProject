package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Pacote;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPacoteDao extends HDao<Pacote> implements PacoteDao {

	@Override
	public List<Pacote> listaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Pacote obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public List<Pacote> pesquisa(String adUserId, String categoria, String texto, String ordem) throws Exception {

		String query = "SELECT obj FROM Pacote obj WHERE obj.adUserId = :adUserId";
		query += " AND obj.adClientId = :adClientId";
		query += " AND (";
		query += " UPPER(obj.nome) LIKE UPPER(:texto)";
		query += " OR UPPER(obj.descricao) LIKE UPPER(:texto)";
		query += " OR UPPER(obj.categoria) LIKE UPPER(:texto)";
		query += " )";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));
		parametros.put("texto", texto);
		if (categoria != null && !categoria.isEmpty()) {
			parametros.put("categoria", categoria);
			query += " " + "AND obj.categoria = :categoria";
		}

		if (ordem == null)
			ordem = "nome";
		
		query += " " + "ORDER BY obj." + ordem;
		return pesquisa(query, parametros);
	}
	
	@Override
	public Pacote getProductDiscount(String adClientId, String mProductId) throws Exception {

		String query = "SELECT obj FROM Pacote obj WHERE obj.adClientId = :adClientId and obj.mProductId = :mProductId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("adClientId", adClientId);
		parametros.put("mProductId", mProductId);
		
		return carrega(query, parametros);
	}
	

	public static void main(String[] args) throws Exception {
		System.out.println(DaoFactory.getInstance().getPacoteDao().pesquisa("00FA0D5A459F4906A58462E5DB5F220E", null, "ativ", "nome"));
	}

}