package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AgendaContato;
import br.com.adaptaconsultoria.amr.model.Reunioes;
import br.com.adaptaconsultoria.amr.persistence.dao.ReunioesDao;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

public class HReunioesDao extends HDao<Reunioes> implements ReunioesDao 
{
	@Override
	public List<Reunioes> listaReunioes(String adUserId) 
	{
		String query = "SELECT obj FROM Reunioes obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}
}