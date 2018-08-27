package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Saque;
import br.com.adaptaconsultoria.amr.persistence.dao.SaqueDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HSaqueDao extends HDao<Saque> implements SaqueDao {

	@Override
	public BigDecimal taxaSaque(String clientId) {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_taxasaque('" + clientId + "')");
			return (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public BigDecimal taxaSaque(String clientId, BigDecimal valor) {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_taxasaque('" + clientId + "', " + valor.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString() + ")");
			return (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

}