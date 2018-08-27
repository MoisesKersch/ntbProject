package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HFranqueadoDao extends HDao<Franqueado> implements FranqueadoDao {

	@Override
	public Franqueado carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Franqueado obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		List<Franqueado> lista = pesquisa(query, parametros);
		if (lista != null && !lista.isEmpty())
			return lista.get(0);

		return null;
	}
	
	@Override
	public Franqueado carregaPorCodigo(String codigo) throws Exception {
		String query = "SELECT obj FROM Franqueado obj WHERE obj.codigo = :codigo";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);

		List<Franqueado> lista = pesquisa(query, parametros);
		if (lista != null && !lista.isEmpty())
			return lista.get(0);

		return null;
	}
	
	

}