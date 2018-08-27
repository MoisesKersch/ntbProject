package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Ativacoes;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AtivacoesDao extends Dao<Ativacoes> {

	public List<Ativacoes> carregaPorCBPartnerId(String cBPartnerId) throws Exception;

}