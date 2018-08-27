package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.com.adaptaconsultoria.amr.model.AgendaContato;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.brminhalista
 * @version 1.0.0
 *
 */ 
@Transactional
public interface AgendaContatoDao extends Dao<AgendaContato>
{
	public void addContato(AgendaContato p);
	public List<AgendaContato> listaContato(String cBpartneriId);
}