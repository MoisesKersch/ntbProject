package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.PreCompraItem;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PreCompraItemDao extends Dao<PreCompraItem> {

	public List<PreCompraItem> listaPorPreCompra(String amrPreCompraId) throws Exception;

}