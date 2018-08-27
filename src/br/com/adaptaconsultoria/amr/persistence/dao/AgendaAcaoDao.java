package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.adaptaconsultoria.amr.model.AgendaAcao;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.brminhalista
 * @version 1.0.0
 *
 */ 
@Repository
public interface AgendaAcaoDao extends Dao<AgendaAcao>
{
	public List<AgendaAcao> getAgendaAcao(String id);
}