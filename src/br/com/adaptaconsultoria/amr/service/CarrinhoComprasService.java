package br.com.adaptaconsultoria.amr.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.CarrinhoCompras;
import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;
import br.com.adaptaconsultoria.amr.model.Pacote;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class CarrinhoComprasService {

	private CarrinhoComprasDao carrinhoComprasDao = DaoFactory.getInstance().getCarrinhoComprasDao();
	private CarrinhoComprasItemDao carrinhoComprasItemDao = DaoFactory.getInstance().getCarrinhoComprasItemDao();
	private PacoteDao pacoteDao = DaoFactory.getInstance().getPacoteDao();

	public CarrinhoCompras carregaCarrinhoDeCompras(String adUserId, boolean vendaDireta) throws Exception {
		CarrinhoCompras cart = carrinhoComprasDao.carregaAbertoPorUsuario(adUserId, vendaDireta ? "Y" : "N");
		if (cart == null) {
			cart = new CarrinhoCompras();
			cart.setId(carrinhoComprasDao.getUUID());
			cart.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
			cart.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
			cart.setCreatedby(adUserId);
			cart.setUpdatedby(adUserId);
			cart.setAdUserId(adUserId);
			cart.setVendaDireta(vendaDireta ? "Y" : "N");
			cart = carrinhoComprasDao.salva(cart);
		}
		return cart;
	}

	public void adicionaProdutoAoCarinho(String adUserId, String pacoteId, boolean vendaDireta) throws Exception {
		Pacote pacote = pacoteDao.carregaPorId(pacoteId);
		if (pacote == null)
			throw new Exception("Nenhum pacote de produtos com o ID: " + pacoteId + " foi encontrado no sistema");

		CarrinhoCompras cart = carregaCarrinhoDeCompras(adUserId, vendaDireta);

		CarrinhoComprasItem item = new CarrinhoComprasItem();
		item.setId(carrinhoComprasItemDao.getUUID());
		item.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
		item.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
		item.setCreatedby(adUserId);
		item.setUpdatedby(adUserId);
		item.setCarrinho(cart);
		item.setNome(pacote.getNome());
		item.setmProductId(pacote.getmProductId());
		item.setQuantidade(1);
		if (vendaDireta)
			item.setPrecoUnitario(new BigDecimal(pacote.getPrecoDe()));
		else
			item.setPrecoUnitario(new BigDecimal(pacote.getPrecoPor()));
		item.setImagemURL(pacote.getImageURL());
		item.setPeso(pacote.getPeso());
		item.setAltura(pacote.getAltura());
		item.setLargura(pacote.getLargura());
		item.setComprimento(pacote.getComprimento());
		item.setDiametro(pacote.getDiametro());
		item.setPrecoDe(new BigDecimal(pacote.getPrecoDe()));
		item.setPrecoPor(new BigDecimal(pacote.getPrecoPor()));
		item.setmPriceListVersionId(pacote.getmPriceListVersionId());
		carrinhoComprasItemDao.salva(item);
	}

	public void removeProdutoDoCarinho(String amrCarrinhoComprasItemId) throws Exception {
		CarrinhoComprasItem item = carrinhoComprasItemDao.carregaPorId(amrCarrinhoComprasItemId);
		carrinhoComprasItemDao.exclui(item);
	}

	public List<CarrinhoComprasItem> listaItensDoCarrinho(String adUserId, boolean vendaDireta) throws Exception {
		CarrinhoCompras cart = carregaCarrinhoDeCompras(adUserId, vendaDireta);
		return carrinhoComprasItemDao.listaPorCarrinhoDeCompras(cart.getId());
	}

}