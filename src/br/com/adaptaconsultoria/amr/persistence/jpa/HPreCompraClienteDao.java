package br.com.adaptaconsultoria.amr.persistence.jpa;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.PreCompraCliente;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraClienteDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HPreCompraClienteDao extends HDao<PreCompraCliente> implements PreCompraClienteDao {

	@Override
	public String salvaCliente(String amrPreCompraClienteId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_salvacliente('" + amrPreCompraClienteId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}