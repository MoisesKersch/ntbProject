package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AgendaContato;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaContatoDao;


public class HAgendaContatoDao extends HDao<AgendaContato> implements AgendaContatoDao 
{

	@Override
	public void addContato(AgendaContato p) 
	{
		try 
		{
			p.setId(getUUID());
			salva(p);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

	@Override
	public List<AgendaContato> listaContato(String cBpartneriId) 
	{
		String query = "SELECT obj FROM AgendaContato obj WHERE obj.cBpartneriId = :cBpartneriId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cBpartneriId", cBpartneriId);

		return pesquisa(query, parametros);
	}
}
