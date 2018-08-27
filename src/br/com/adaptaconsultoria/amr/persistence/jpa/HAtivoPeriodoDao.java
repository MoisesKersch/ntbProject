package br.com.adaptaconsultoria.amr.persistence.jpa;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.AtivoPeriodo;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoPeriodoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HAtivoPeriodoDao extends HDao<AtivoPeriodo> implements AtivoPeriodoDao {

	@Override
	public AtivoPeriodo getProximoPeriodoParaInserir(String usuarioId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery(
					"SELECT amr_proximoativoperiodo('" + AMRProperties.getInstance().getPropriedade("adclientid") + "', '" + usuarioId + "')");
			String id = (String) q.getSingleResult();
			AtivoPeriodo periodo = carregaPorId(id);
			return periodo;
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}