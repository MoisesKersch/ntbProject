package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Endereco;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HEnderecoDao extends HDao<Endereco> implements EnderecoDao {

	@Override
	public List<Endereco> listaPorParceiroNegocios(String cBPartnerId) throws Exception {
		String query = "SELECT e FROM ParceiroNegociosEndereco obj";
		query += " JOIN obj.endereco e WHERE obj.parceiroNegocios.id = :cBPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cBPartnerId", cBPartnerId);

		return pesquisa(query, parametros);
	}

	@Override
	public String carregaCEPOrigem(String adOrgId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_carregaceporigem('" + adOrgId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

	@Override
	public String carregaCEPDestino(String adUserId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_carregacepdestino('" + adUserId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}