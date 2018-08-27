package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Matricula;
import br.com.adaptaconsultoria.amr.persistence.dao.MatriculaDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HMatriculaDao extends HDao<Matricula> implements MatriculaDao {

	@Override
	public List<Matricula> porcBPartnerId(String cBPartnerId) throws Exception {
		String query = "SELECT obj FROM Matricula obj WHERE obj.cBPartnerId = :cBPartnerId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cBPartnerId", cBPartnerId);

		return pesquisa(query, parametros);
	}

}