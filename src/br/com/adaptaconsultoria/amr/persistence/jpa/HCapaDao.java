package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Capa;
import br.com.adaptaconsultoria.amr.persistence.dao.CapaDao;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HCapaDao extends HDao<Capa> implements CapaDao {

	@Override
	public Capa carregaPorClient(String adClientId) throws Exception {
		String query = "SELECT obj FROM Capa obj WHERE obj.adClientId = :adClientId AND obj.capaDe <= :dataAtual ORDER BY obj.capaDe DESC, obj.name";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);
		parametros.put("dataAtual", new Date());

		return carrega(query, parametros);
	}

}
