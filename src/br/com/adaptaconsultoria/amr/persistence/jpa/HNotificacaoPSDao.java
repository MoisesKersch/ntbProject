package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.NotificacaoPS;
import br.com.adaptaconsultoria.amr.persistence.dao.NotificacaoPSDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HNotificacaoPSDao extends HDao<NotificacaoPS> implements NotificacaoPSDao {

	@Override
	public boolean existeRegistroPosDebtPayment(String debtPaymentId) throws Exception {
		String query = "SELECT obj FROM NotificacaoPS obj WHERE obj.debtPaymentId = :debtPaymentId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("debtPaymentId", debtPaymentId);

		try {
			NotificacaoPS notificacaoPS = carrega(query, parametros);
			return notificacaoPS != null;
		} catch (Exception e) {
			return false;
		}
	}

}