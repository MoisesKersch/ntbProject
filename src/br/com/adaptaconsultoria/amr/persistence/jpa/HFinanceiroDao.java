package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Financeiro;
import br.com.adaptaconsultoria.amr.persistence.dao.FinanceiroDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HFinanceiroDao extends HDao<Financeiro> implements FinanceiroDao {

	@Override
	public List<Financeiro> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Financeiro obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public List<Financeiro> carregaPorUsuarioMesEAno(String adUserId, int mes, int ano, Date dataInicial, Date dataFinal) throws Exception {
		String query = "SELECT obj FROM Financeiro obj WHERE obj.adUserId = :adUserId AND obj.mes = :mes AND obj.ano = :ano ";

		Map<String, Object> parametros = new HashMap<String, Object>();

		if (dataInicial != null) {
			parametros.put("dataInicial", dataInicial);
			query += "AND TRUNC(obj.vencimento) >= :dataInicial ";
		}

		if (dataFinal != null) {
			parametros.put("dataFinal", dataFinal);
			query += "AND TRUNC(obj.vencimento) <= :dataFinal";
		}

		parametros.put("adUserId", adUserId);
		parametros.put("mes", mes);
		parametros.put("ano", ano);

		return pesquisa(query, parametros);
	}

}