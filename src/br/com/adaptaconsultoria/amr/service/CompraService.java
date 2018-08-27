package br.com.adaptaconsultoria.amr.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.Compra;
import br.com.adaptaconsultoria.amr.model.MetodoEntrega;
import br.com.adaptaconsultoria.amr.model.Pacote;
import br.com.adaptaconsultoria.amr.model.Produto;
import br.com.adaptaconsultoria.amr.model.RetEnvCompra;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.MetodoEntregaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.proxy.CompraServiceProxy;
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
public class CompraService {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private MetodoEntregaDao metodoEntregaDao = DaoFactory.getInstance().getMetodoEntregaDao();
	private PacoteDao pacoteDao = DaoFactory.getInstance().getPacoteDao();
	private EntregaService entregaService = new EntregaService();

	public List<Produto> listaProdutosDisponiveis(String adUserId, String categoria, String texto, String ordem) throws Exception 
	{
		List<Produto> produtos = new ArrayList<Produto>();

		if (texto == null)
			texto = "";
		 
		texto = "%" + texto.replaceAll(" ", "%") + "%";

		List<Pacote> pacotes = pacoteDao.pesquisa(adUserId, categoria, texto, ordem.equalsIgnoreCase("novidades") ? "ativoDesde" : ordem);
		
		for (int i = 0; i < pacotes.size(); i++) 
		{
			Produto produto = new Produto();
			produto.setId(pacotes.get(i).getId());
			produto.setCodigo(pacotes.get(i).getId());
			produto.setNome(pacotes.get(i).getNome());
			produto.setDescricao(pacotes.get(i).getDescricao());
			produto.setReadOnly(pacotes.get(i).getObrigatorioAdesao().equalsIgnoreCase("Y"));
			produto.setSelecionado(pacotes.get(i).getObrigatorioAdesao().equalsIgnoreCase("Y"));
			produto.setImagemURL(pacotes.get(i).getImageURL());
			produto.setPrecoDe(pacotes.get(i).getPrecoDe());
			produto.setPreco(pacotes.get(i).getPrecoPor());
			produto.setCategoria(pacotes.get(i).getCategoria());
			produto.setPtsBinario(pacotes.get(i).getPtsBinario());
			produto.setPtsCarreira(pacotes.get(i).getPtsCarreira());
			
			produtos.add(produto);
		}
		return produtos;
	}

	public void enviaCompra(String adUserId, String id, boolean autenticaCompra, String senhaFinanceira, String amrMetodoEntregaId) throws Exception {

		if (autenticaCompra) {
			Usuario usuario = usuarioDao.carregaPorId(adUserId);
			if (usuario == null)
				throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

			if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
				throw new Exception("Senha financeira não confere");

			if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhaFinanceira)))
				throw new Exception("Senha financeira não confere");
		}

		Pacote pacote = pacoteDao.carregaPorId(id);
		if (pacote == null)
			throw new Exception("Pacote não encontrado com o ID: " + id);

		Compra compra = new Compra();
		compra.setAdUserId(adUserId);
		compra.setmProductId(pacote.getmProductId());

		MetodoEntrega metodoEntrega = metodoEntregaDao.carregaPorId(amrMetodoEntregaId);
		if (metodoEntrega == null)
			throw new Exception("Método de entrega com o ID: " + amrMetodoEntregaId + " não foi encontrado no sistema.\nContate o suporte técnico.");

		if (metodoEntrega.getMetodoentrega().equalsIgnoreCase("Correios")) {
			BigDecimal valor = entregaService.calculaPrecoDosCorreios(metodoEntrega, adUserId, pacote.getComprimento(), pacote.getAltura(),
					pacote.getLargura(), pacote.getDiametro(), pacote.getPeso().toString());
			metodoEntrega.setValor(valor);
		}

		compra.setDeliveryviarule(metodoEntrega.getDeliveryviarule());
		compra.setmShipperId(metodoEntrega.getM_shipper_id());
		compra.setAcServicoentregaId(metodoEntrega.getAcServicoentregaId());
		compra.setValor(metodoEntrega.getValor());

		String xml = compra.toXML();

		CompraServiceProxy proxy = new CompraServiceProxy();
		RetEnvCompra retEnvCompra = proxy.doPost(xml);
		if (!retEnvCompra.isSucesso()) {
			throw new Exception(retEnvCompra.getDescricao());
		}
	}

}