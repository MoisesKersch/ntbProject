package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.PreCompraCliente;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PreCompraClienteDao extends Dao<PreCompraCliente> {

	public String salvaCliente(String amrPreCompraClienteId) throws Exception;

}