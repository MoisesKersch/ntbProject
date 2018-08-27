package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPreCompraDao extends HDao<PreCompra> implements PreCompraDao {

	@Override
	public PreCompra carregaPorNumero(String numero) throws Exception {
		String query = "SELECT obj FROM PreCompra obj WHERE obj.numero= :numero AND obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numero", numero);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return carrega(query, parametros);
	}

	@Override
	public String proximoNumeroDePedido() throws Exception {
		String query = "SELECT COALESCE(MAX(TO_NUMBER(obj.numero)), 0) + 1 FROM PreCompra obj WHERE obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));
		

		Query q = getEntityManager().createQuery(query);
		for (String chave : parametros.keySet()) {
			q.setParameter(chave, parametros.get(chave));
		}
		try {
			BigDecimal codigo = (BigDecimal) q.getSingleResult();
			return String.valueOf(codigo);
		} catch (NoResultException nre) {
			return "10";
		}
	}

	@Override
	public PreCompra porNumeroDePedido(String cOrderId) throws Exception {
		String query = "SELECT obj FROM PreCompra obj WHERE obj.pedido.id = :cOrderId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cOrderId", cOrderId);

		return carrega(query, parametros);
	}

	@Override
	public String pagar(String id) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_pagamento_cartao('" + id + "')");
		return (String) q.getSingleResult();
	}

}