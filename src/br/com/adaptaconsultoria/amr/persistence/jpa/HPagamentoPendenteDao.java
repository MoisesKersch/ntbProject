package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.PagamentoPendente;
import br.com.adaptaconsultoria.amr.persistence.dao.PagamentoPendenteDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

public class HPagamentoPendenteDao extends HDao<PagamentoPendente> implements PagamentoPendenteDao {

	@Override
	public List<PagamentoPendente> carregaPorUsuario(String adUserId) throws Exception {
		System.out.println(adUserId);
		String query = "SELECT obj FROM PagamentoPendente obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public String paga(String adUserId, String cDebtPaymentId, String voucher) throws Exception 
	{
		try {
			String sql = "";
			if (voucher == null) {
				sql = "SELECT amr_pagamento_saldo('" + adUserId + "', '" + cDebtPaymentId + "', null)";
			} else {
				sql = "SELECT amr_pagamento_saldo('" + adUserId + "', '" + cDebtPaymentId + "', '" + voucher + "')";
			}
			Query q = getEntityManager().createNativeQuery(sql);
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

	@Override
	public String pagamentoTeste(String adClientId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_bpgto_post0('" + adClientId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}