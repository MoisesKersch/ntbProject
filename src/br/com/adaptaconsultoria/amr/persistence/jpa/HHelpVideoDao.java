package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.HelpVideo;
import br.com.adaptaconsultoria.amr.persistence.dao.HelpVideoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HHelpVideoDao extends HDao<HelpVideo> implements HelpVideoDao {

	@Override
	public List<HelpVideo> listaPorPalavraChave(String... palavra) throws Exception {
		String query = "SELECT obj FROM HelpVideo obj WHERE adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));
		for (int i = 0; i < palavra.length; i++) {
			query += " " + "AND UPPER(obj.palavraChave) LIKE UPPER(:palavra" + i + ")";
			parametros.put("palavra" + i, "%" + palavra[i].replaceAll(" ", "%") + "%");
		}

		return pesquisa(query, parametros);
	}

	@Override
	public List<HelpVideo> listaPorClient(String adClientId) throws Exception {
		String query = "SELECT obj FROM HelpVideo obj WHERE obj.adClientId = :adClientId order by obj.nome";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);

		return pesquisa(query, parametros);
	}

}