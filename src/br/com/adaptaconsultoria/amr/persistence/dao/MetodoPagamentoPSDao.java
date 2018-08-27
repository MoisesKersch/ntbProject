package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.MetodoPagamentoPS;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface MetodoPagamentoPSDao extends Dao<MetodoPagamentoPS> {

	public MetodoPagamentoPS carregaPorAdClientIdPaymentMethodType(String adClientId, Integer paymentMethodType) throws Exception;

}