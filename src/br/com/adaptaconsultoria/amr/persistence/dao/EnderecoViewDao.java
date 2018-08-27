package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.EnderecoView;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface EnderecoViewDao extends Dao<EnderecoView> {

	public EnderecoView carregaPorPartner(String bPartnerId) throws Exception;

}