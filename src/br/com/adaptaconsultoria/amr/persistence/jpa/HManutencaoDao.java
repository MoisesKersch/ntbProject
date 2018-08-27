package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Manutencao;
import br.com.adaptaconsultoria.amr.persistence.dao.ManutencaoDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HManutencaoDao extends HDao<Manutencao> implements ManutencaoDao {

	@Override
	public List<Manutencao> listaAtivas(String adClientId) throws Exception {
		String query = "SELECT obj FROM Manutencao obj WHERE obj.adClientId = :adClientId AND manutencao = 'Y'";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);

		return pesquisa(query, parametros);
	}

	@Override
	public boolean temAtivas(String adClientId) throws Exception {
		String query = "SELECT COUNT(obj.id) FROM Manutencao obj "
				+ " WHERE obj.adClientId = :adClientId "
				+ "   AND manutencao = :manutencao "
				+ "   AND datainicio <= :dataatual "
				+ "   AND datatermino >= :dataatual "
				+ "   AND isactive = 'Y' "
				;

		getEntityManager().clear();
		Query q = getEntityManager().createQuery(query);
		q.setParameter("adClientId", adClientId);
		q.setParameter("manutencao", "Y");
		q.setParameter("dataatual", new Date());

		Long total = 0l;
		try {
			total = (Long) q.getSingleResult();
		} catch (NoResultException nre) {
		}
		return total.intValue() > 0;
	}

}