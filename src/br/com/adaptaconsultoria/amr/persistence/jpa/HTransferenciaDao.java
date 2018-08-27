package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Transferencia;
import br.com.adaptaconsultoria.amr.persistence.dao.TransferenciaDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HTransferenciaDao extends HDao<Transferencia> implements TransferenciaDao {

	@Override
	public String post(String transferenciaId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_transfere_post('" + transferenciaId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

	@Override
	public BigDecimal taxaTransferencia(String clientId, BigDecimal valor) {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_taxatransferencia('" + clientId + "', " + valor.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString() + ")");
			return (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

}