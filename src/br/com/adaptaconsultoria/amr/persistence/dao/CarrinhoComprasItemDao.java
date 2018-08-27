package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface CarrinhoComprasItemDao extends Dao<CarrinhoComprasItem> {

	public List<CarrinhoComprasItem> listaPorCarrinhoDeCompras(String amrCarrinhoComprasId) throws Exception;

}