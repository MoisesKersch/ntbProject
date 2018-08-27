package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.RedeParcelas;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface RedeParcelasDao extends Dao<RedeParcelas> {

	public List<RedeParcelas> listaPorBandeira(String bandeira, String amrPreCompraId) throws Exception;

}