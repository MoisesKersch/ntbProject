package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.CadastroPendente;
import br.com.adaptaconsultoria.amr.persistence.dao.CadastroPendenteDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HCadastroPendenteDao extends HDao<CadastroPendente> implements CadastroPendenteDao {

	@Override
	public List<CadastroPendente> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM CadastroPendente obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public String paga(String adUserId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_pagamento_cadastro('" + adUserId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}