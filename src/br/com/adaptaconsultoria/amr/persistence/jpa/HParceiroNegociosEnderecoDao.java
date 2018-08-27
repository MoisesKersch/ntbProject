package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.ParceiroNegociosEndereco;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosEnderecoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HParceiroNegociosEnderecoDao extends HDao<ParceiroNegociosEndereco> implements ParceiroNegociosEnderecoDao {

	@Override
	public List<ParceiroNegociosEndereco> listaPorParceiroNegocios(String cBPartnerId) throws Exception {
		String query = "SELECT obj FROM ParceiroNegociosEndereco obj WHERE obj.parceiroNegocios.id = :cBPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cBPartnerId", cBPartnerId);

		return pesquisa(query, parametros);
	}

}