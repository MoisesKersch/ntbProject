package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.PreCompra;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PreCompraDao extends Dao<PreCompra> {

	public PreCompra carregaPorNumero(String numero) throws Exception;

	public String proximoNumeroDePedido() throws Exception;

	public PreCompra porNumeroDePedido(String cOrderId) throws Exception;

	public String pagar(String id) throws Exception;

}