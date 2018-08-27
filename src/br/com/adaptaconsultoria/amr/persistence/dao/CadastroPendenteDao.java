package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.CadastroPendente;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface CadastroPendenteDao extends Dao<CadastroPendente> {

	public List<CadastroPendente> carregaPorUsuario(String adUserId) throws Exception;

	public String paga(String adUserId) throws Exception;

}