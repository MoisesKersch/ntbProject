package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.AtivacoesLCTO;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivacoesLCTODao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAtivacoesLCTODao extends HDao<AtivacoesLCTO> implements AtivacoesLCTODao {

	@Override
	public List<AtivacoesLCTO> carregaPorAmrAtivacaoId(String amrAtivacaoId) throws Exception {
		String query = "SELECT obj FROM AtivacoesLCTO obj WHERE obj.amrAtivacaoId = :amrAtivacaoId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("amrAtivacaoId", amrAtivacaoId);

		return pesquisa(query, parametros);
	}

}
