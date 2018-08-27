package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.AtivoAgenda;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AtivoAgendaDao extends Dao<AtivoAgenda> {

	public List<AtivoAgenda> listaPorUsuario(String adUserId) throws Exception;

}