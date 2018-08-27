package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Voucher;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HVoucherDao extends HDao<Voucher> implements VoucherDao {

	@Override
	public List<Voucher> listaDisponiveisPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Voucher obj WHERE obj.adUserId = :adUserId AND obj.disponivel = 'Y'";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}
	
	@Override
	public boolean isValid(String codigo,  String adClientId) 
	{
		String query = "SELECT obj FROM Voucher obj WHERE obj.codigo = :codigo AND obj.adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		parametros.put("adClientId", adClientId);

		try {
			return carrega(query, parametros) != null;
		} catch (Exception e) {
			return false;
		}
	}
}