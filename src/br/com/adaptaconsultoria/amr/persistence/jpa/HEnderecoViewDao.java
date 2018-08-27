package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.EnderecoView;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoViewDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HEnderecoViewDao extends HDao<EnderecoView> implements EnderecoViewDao {

	@Override
	public EnderecoView carregaPorPartner(String bPartnerId) throws Exception {
		String query = "SELECT obj FROM EnderecoView obj WHERE obj.bPartner = :bPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("bPartnerId", bPartnerId);

		return carrega(query, parametros);
	}

}
