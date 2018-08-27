package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.ComprasItem;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ComprasItemDao extends Dao<ComprasItem> {

	public List<ComprasItem> carregaPorOrder(String cOrderId) throws Exception;

}