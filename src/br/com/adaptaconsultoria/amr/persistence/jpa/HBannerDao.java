package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Banner;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.BannerDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HBannerDao extends HDao<Banner> implements BannerDao {

	@Override
	public List<Banner> carregaPorEntidade(String entidadeId) throws Exception {
		String query = "SELECT obj FROM Banner obj";
		query += " " + "WHERE obj.adClientId = :entidadeId";
		query += " " + "AND (obj.dataInicial IS NULL OR TRUNC(obj.dataInicial) <= TRUNC(now()))";
		query += " " + "AND (obj.dataFinal IS NULL OR TRUNC(obj.dataFinal) >= TRUNC(now()))";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("entidadeId", entidadeId);

		return pesquisa(query, parametros);
	}

	public static void main(String[] args) throws Exception {
		
		
		System.out.println(DaoFactory.getInstance().getBannerDao().carregaPorEntidade("FC0C96D044D54907B5263BADCB11C72F"));
	}

}