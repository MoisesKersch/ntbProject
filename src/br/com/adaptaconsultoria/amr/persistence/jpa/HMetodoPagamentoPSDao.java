package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.MetodoPagamentoPS;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoPagamentoPSDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HMetodoPagamentoPSDao extends HDao<MetodoPagamentoPS> implements MetodoPagamentoPSDao {

	@Override
	public MetodoPagamentoPS carregaPorAdClientIdPaymentMethodType(String adClientId, Integer paymentMethodType) throws Exception {
		String query = "SELECT obj FROM MetodoPagamentoPS obj WHERE obj.adClientId = :adClientId and obj.paymentMethod = :paymentMethodType";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId"       , adClientId);
		parametros.put("paymentMethodType", paymentMethodType);

		return carrega(query, parametros);
	}

}