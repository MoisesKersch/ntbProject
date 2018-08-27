package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.AtivacoesLCTO;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AtivacoesLCTODao extends Dao<AtivacoesLCTO> {

	public List<AtivacoesLCTO> carregaPorAmrAtivacaoId(String amrAtivacaoId) throws Exception;

}
