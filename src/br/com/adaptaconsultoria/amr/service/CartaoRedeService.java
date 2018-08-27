package br.com.adaptaconsultoria.amr.service;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.RedeConfig;
import br.com.adaptaconsultoria.amr.model.RetRedeCardConfirma;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.proxy.CartaoRedeConfirmacaoProxy;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class CartaoRedeService {

	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();

	public PreCompra confirmaTransacao(PreCompra preCompra) throws Exception {

		RedeConfig config = DaoFactory.getInstance().getRedeConfigDao().carregaPorId(preCompra.getAdOrgId());
		if (config == null)
			throw new Exception("Nenhuma configuração para autorização de cartões com a rede card encontrada para a organização da compra prévia número: "
					+ preCompra.getNumero());

		CartaoRedeConfirmacaoProxy proxy = new CartaoRedeConfirmacaoProxy();
		RetRedeCardConfirma ret = proxy.doGet(config, preCompra);

		System.out.println(ret.getCodigo());
		System.out.println(ret.getMensagem());

		preCompra.setRetornoCodigo(ret.getCodigo());
		preCompra.setRetornoMensagem(ret.getMensagem());
		preCompra.setAutorizada(ret.isConfirmada() ? "Y" : "N");
		preCompra = preCompraDao.atualiza(preCompra);

		return preCompra;
	}

	public static void main(String[] args) throws Exception {
		CartaoRedeService service = new CartaoRedeService();
		PreCompra preCompra = DaoFactory.getInstance().getPreCompraDao().carregaPorId("B7B5A639BA464E74850A3D2A9AF33F93");
		service.confirmaTransacao(preCompra);
	}

}