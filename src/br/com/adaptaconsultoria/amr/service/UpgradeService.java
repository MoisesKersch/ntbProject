package br.com.adaptaconsultoria.amr.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.MetodoEntrega;
import br.com.adaptaconsultoria.amr.model.Pacote;
import br.com.adaptaconsultoria.amr.model.PacoteUpgradeLinha;
import br.com.adaptaconsultoria.amr.model.Produto;
import br.com.adaptaconsultoria.amr.model.RetEnvUpgrade;
import br.com.adaptaconsultoria.amr.model.Upgrade;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeLinhaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.proxy.UpgradeServiceProxy;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class UpgradeService {

	
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	
	private PacoteUpgradeLinhaDao pacoteUpgradeLinhaDao = DaoFactory.getInstance().getPacoteUpgradeLinhaDao();
	
	private MetodoEntregaDao metodoEntregaDao = DaoFactory.getInstance().getMetodoEntregaDao();
	
	private PacoteDao pacoteDao = DaoFactory.getInstance().getPacoteDao();
	
	private EntregaService entregaService = new EntregaService();

	public void enviaUpgrade(Upgrade upgrade, boolean autenticaCompra, String senhaFinanceira, String pacoteUpgradeId, Serializable amrMetodoEntregaId)
			throws Exception {

		if (autenticaCompra) {
			Usuario usuario = usuarioDao.carregaPorId(upgrade.getAdUserId());
			if (usuario == null)
				throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

			if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
				throw new Exception("Senha financeira não confere");

			if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhaFinanceira)))
				throw new Exception("Senha financeira não confere");
		}

		List<PacoteUpgradeLinha> lista = pacoteUpgradeLinhaDao.listaPorPacote(pacoteUpgradeId);
		List<Produto> produtos = new ArrayList<Produto>();
		for (int i = 0; i < lista.size(); i++) {
			Produto produto = new Produto();
			produto.setId(lista.get(i).getmProductId());
			produto.setPreco(lista.get(i).getPricelist().toString());
			produto.setQuantidade(lista.get(i).getQuantidade().intValue());
			produto.setSelecionado(true);
			produtos.add(produto);
		}

		upgrade.setProdutos(produtos);

		MetodoEntrega metodoEntrega = metodoEntregaDao.carregaPorId(amrMetodoEntregaId);
		if (metodoEntrega == null)
			throw new Exception("Método de entrega com o ID: " + amrMetodoEntregaId + " não foi encontrado no sistema.\nContate o suporte técnico.");

		if (metodoEntrega.getMetodoentrega().equalsIgnoreCase("Correios")) {
			Pacote pacote = pacoteDao.carregaPorId(lista.get(0).getmProductId());
			BigDecimal valor = entregaService.calculaPrecoDosCorreios(metodoEntrega, upgrade.getAdUserId(), pacote.getComprimento(), pacote.getAltura(),
					pacote.getLargura(), pacote.getDiametro(), pacote.getPeso().toString());
			metodoEntrega.setValor(valor);
		}

		upgrade.setDeliveryviarule(metodoEntrega.getDeliveryviarule());
		upgrade.setmShipperId(metodoEntrega.getM_shipper_id());
		upgrade.setAcServicoentregaId(metodoEntrega.getAcServicoentregaId());
		upgrade.setValor(metodoEntrega.getValor());

		String xml = upgrade.toXML();

		UpgradeServiceProxy proxy = new UpgradeServiceProxy();
		RetEnvUpgrade retEnvUpgrade = proxy.doPost(xml);
		if (!retEnvUpgrade.isSucesso()) {
			throw new Exception(retEnvUpgrade.getDescricao());
		}
	}

}