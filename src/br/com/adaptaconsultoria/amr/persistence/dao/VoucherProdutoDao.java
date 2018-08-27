package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.VoucherProduto;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface VoucherProdutoDao extends Dao<VoucherProduto> {

	public List<VoucherProduto> listaOfertasPorEmpresa(String adClientId) throws Exception;

	public String compraVoucher(String usuarioId, String voucherProdutoId) throws Exception;

}