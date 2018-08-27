package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Pacote;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PacoteDao extends Dao<Pacote> {

	public List<Pacote> listaPorUsuario(String adUserId) throws Exception;

	public List<Pacote> pesquisa(String adUserId, String categoria, String texto, String ordem) throws Exception;
	
	public Pacote getProductDiscount (String adClientId, String mProductId) throws Exception;

}