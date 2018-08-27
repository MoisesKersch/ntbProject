package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.NotificacaoPS;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface NotificacaoPSDao extends Dao<NotificacaoPS> {

	public boolean existeRegistroPosDebtPayment(String debtPaymentId) throws Exception;

}