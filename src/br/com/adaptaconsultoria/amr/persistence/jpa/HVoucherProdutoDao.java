package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.VoucherProduto;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherProdutoDao;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HVoucherProdutoDao extends HDao<VoucherProduto> implements VoucherProdutoDao {

	@Override
	public List<VoucherProduto> listaOfertasPorEmpresa(String adClientId) throws Exception {
		String query = "SELECT obj FROM VoucherProduto obj WHERE obj.adClientId = :adClientId AND obj.ativo = 'Y' ORDER BY obj.valor";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);

		return pesquisa(query, parametros);
	}

	@Override
	public String compraVoucher(String adUserId, String voucherProdutoId) throws Exception {
		try {
			Query q = getEntityManager().createNativeQuery("SELECT amr_compravoucher('" + adUserId + "', '" + voucherProdutoId + "')");
			return (String) q.getSingleResult();
		} catch (Exception e) {
			throw new Exception(ErrorUtil.getLastMessage(e));
		}
	}

}