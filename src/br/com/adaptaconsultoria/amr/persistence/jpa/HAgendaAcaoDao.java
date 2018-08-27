package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AgendaAcao;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaAcaoDao;

public class HAgendaAcaoDao extends HDao<AgendaAcao> implements AgendaAcaoDao
{

	@Override
	public List<AgendaAcao> getAgendaAcao(String adClientId) 
	{
		String query = "SELECT obj FROM AgendaAcao obj WHERE obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);

		return pesquisa(query, parametros);
	}
}
