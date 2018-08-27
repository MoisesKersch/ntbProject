package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Reunioes;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

public interface ReunioesDao extends Dao<Reunioes> 
{
	public List<Reunioes> listaReunioes(String adClientId);
}