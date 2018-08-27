package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Documento;
import br.com.adaptaconsultoria.amr.persistence.dao.DocumentoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HDocumentoDao extends HDao<Documento> implements DocumentoDao {

	@Override
	public List<Documento> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Documento obj WHERE "
				+ "(obj.adUserId = :adUserId OR obj.adUserId IS NULL) AND "
				+ "(obj.adClientId = :adClientId)";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return pesquisa(query, parametros);
	}

}