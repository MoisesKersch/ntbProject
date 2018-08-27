package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.adaptaconsultoria.amr.model.AgendaAcao;
import br.com.adaptaconsultoria.amr.model.CarrinhoCompras;
import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;
import br.com.adaptaconsultoria.amr.model.Compras;
import br.com.adaptaconsultoria.amr.model.ComprasItem;
import br.com.adaptaconsultoria.amr.model.FormaPagamento;
import br.com.adaptaconsultoria.amr.model.Pacote;
import br.com.adaptaconsultoria.amr.model.PacoteCategoria;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.PreCompraItem;
import br.com.adaptaconsultoria.amr.model.Produto;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ComprasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ComprasItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FormaPagamentoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteCategoriaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.persistence.dao.VoucherDao;
import br.com.adaptaconsultoria.amr.service.CompraService;
import br.com.adaptaconsultoria.amr.service.EntregaService;
import br.com.adaptaconsultoria.amr.service.FormaPagamentoService;
import br.com.adaptaconsultoria.amr.service.PreCompraService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;
import br.com.adaptaconsultoria.amr.util.lang.CharsetUtil;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Controller
@Scope("request")
@SuppressWarnings("serial")
public class ComprasController implements Serializable {

	private ComprasDao comprasDao = DaoFactory.getInstance().getComprasDao();
	private ComprasItemDao comprasItemDao = DaoFactory.getInstance().getComprasItemDao();
	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
	private CompraService compraService = new CompraService();
	private PreCompraService preCompraService = new PreCompraService();
	private EntregaService entregaService = new EntregaService();
	private FormaPagamentoService formaPagamentoService = new FormaPagamentoService();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private PacoteCategoriaDao pacoteCategoriaDao = DaoFactory.getInstance().getPacoteCategoriaDao();
	private PacoteDao pacoteDao = DaoFactory.getInstance().getPacoteDao();
	private PessoaController pessoaController = new PessoaController();
	private CarrinhoComprasDao carrinhoComprasDao = DaoFactory.getInstance().getCarrinhoComprasDao();
	private CarrinhoComprasItemDao carrinhoComprasItemDao = DaoFactory.getInstance().getCarrinhoComprasItemDao();
	private VoucherDao voucherDao = DaoFactory.getInstance().getVoucherDao();
	private FormaPagamentoDao formaPagamentoDao = DaoFactory.getInstance().getFormaPagamentoDao();

	@RequestMapping(value = "/minhascompras", method = GET)
	public ModelAndView minhasCompras(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "minhascompras");
		SessionUtil.setSubMenuAtivo(session, "minhascompras");
		List<Compras> lista = new ArrayList<Compras>();
		try {
			lista = comprasDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("minhascompras", "lista", lista);
		model.addObject("listaData", (new Gson()).toJson(lista));
		return model;
	}

	@RequestMapping(value = "/buscaitenscompra", method = POST)
	public @ResponseBody List<ComprasItem> buscaItensCompra(String id) {
		List<ComprasItem> lista = new ArrayList<ComprasItem>();
		try {
			lista = comprasItemDao.carregaPorOrder(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean urlIsValid(String url) {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unused")
		HttpHeaders headers = null;
		try {
			headers = restTemplate.headForHeaders(url);
		} catch (RestClientException e) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/compras", method = GET)
	public ModelAndView compras(HttpServletRequest request, HttpSession session, String categoria, String texto,
			String ordem, Boolean venda) {

		SessionUtil.setMenuAtivo(session, "compras");
		SessionUtil.setSubMenuAtivo(session, "compras");

		if (categoria != null)
			try {
				System.out.println("CATEGORIA: " + URLDecoder.decode(categoria, request.getCharacterEncoding()));
				categoria = URLDecoder.decode(categoria, request.getCharacterEncoding());
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		if (categoria == null) {
			request.setAttribute("categ", "");

		} else if (categoria.equals("undefined")) {
			request.setAttribute("categ", "");

		} else {
			request.setAttribute("categ", categoria);
		}

		if (texto != null)
			try {
				System.out.println("TEXTO: " + URLDecoder.decode(texto, request.getCharacterEncoding()));
				texto = URLDecoder.decode(texto, request.getCharacterEncoding());
				texto = CharsetUtil.convertUTF8toISO(texto);
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		ModelAndView model = new ModelAndView("compras");

		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			boolean permitido = true;
			String autenticaCompra = SessionUtil.getAttribute(session, "autenticacompra");
			if (autenticaCompra.equalsIgnoreCase("Y")) {
				if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
					permitido = false;
			}
			model.addObject("permitido", permitido);
		} catch (Exception e1) {
			System.out.println("ERRO AO VERIFICAR PERMISSÃO PARA COMPRA DO USUÁRIO");
			e1.printStackTrace();
		}

		List<Produto> lista = new ArrayList<Produto>();
		try {
			if (ordem == null || ordem.isEmpty())
				ordem = "ordem";
			lista = compraService.listaProdutosDisponiveis(SessionUtil.getUsuarioId(session), categoria, texto, ordem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<PacoteCategoria> categorias = new ArrayList<PacoteCategoria>();
		
		try {
			categorias = pacoteCategoriaDao.listaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		model.addObject("lista", lista);
		model.addObject("categorias", categorias);
		model.addObject("texto", texto);
		model.addObject("ordem", ordem);

		if (venda == null)
			venda = false;
		
		model.addObject("venda", venda);
		return model;
	}

	@RequestMapping(value = "/checkoutcompracarrinho", method = GET)
	public ModelAndView preCompraCarrinho(RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request, Boolean venda, BigDecimal noDiscount) {
		try {

			if (venda == null)
				venda = false;

			String adUserId = SessionUtil.getUsuarioId(session);
			PreCompra preCompra = preCompraService.criaPreCompraDeCarrinho(adUserId, venda);
			
			if (venda) {
				return pessoaController.clientePreCompra(preCompra.getId(), null, redirectAttributes, session, request);
			}
			
			BigDecimal valorTotalSemDesconto = new BigDecimal("0");
			
			for (PreCompraItem x : preCompra.getItens())
			{
				valorTotalSemDesconto = valorTotalSemDesconto.add(x.getTotalPrecoSemDesconto());
			}
			
			ModelAndView model = new ModelAndView("checkoutcompraproduto", "pedido", preCompra);
			
			model.addObject("valorTotalSemDesconto", valorTotalSemDesconto);
			model.addObject("preCompraId", preCompra.getId());
			model.addObject("itens", preCompra.getItens());

			try {
				model.addObject("metodosentrega", entregaService.listaMetodosDeEntrega(preCompra, SessionUtil.getUsuarioId(session)));
			} catch (Exception e) {
				model = compras(request, session, null, null, null, null);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			try {
				model.addObject("formaspagamento", formaPagamentoService.listaFormasDePagamentoPorAdClientId());
				System.out.println(formaPagamentoService.listaFormasDePagamentoPorAdClientId());
			} catch (Exception e) {
				model = compras(request, session, null, null, null, null);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			model.addObject("venda", venda);

			return model;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			ModelAndView model = compras(request, session, null, null, null, null);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO");
			model.addObject("messageDetail", e.getMessage());
			return model;
		}
	}

	@RequestMapping(value = "/checkoutcompraproduto", method = GET)
	public ModelAndView preCompraProduto(String id, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request, Boolean venda) {
		try {

			if (venda == null)
				venda = false;

			String adUserId = SessionUtil.getUsuarioId(session);
			PreCompra preCompra = preCompraService.criaPreCompraDePacote(adUserId, id, venda);

			ModelAndView model = new ModelAndView("checkoutcompraproduto", "pedido", preCompra);
			model.addObject("venda", venda);

			if (venda) {
				return pessoaController.clientePreCompra(preCompra.getId(), null, redirectAttributes, session, request);
			}

			model.addObject("itens", preCompra.getItens());

			try {
				model.addObject("metodosentrega",
						entregaService.listaMetodosDeEntrega(preCompra, SessionUtil.getUsuarioId(session)));
			} catch (Exception e) {
				model = compras(request, session, null, null, null, null);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			try {
				model.addObject("formaspagamento", formaPagamentoService.listaFormasDePagamentoPorAdClientId());
			} catch (Exception e) {
				model = compras(request, session, null, null, null, null);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			return model;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			ModelAndView model = compras(request, session, null, null, null, null);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO");
			model.addObject("messageDetail", e.getMessage());
			return model;
		}
	}

	@RequestMapping(value = "/checkoutcompra", method = GET)
	public ModelAndView checkoutCompraProduto(String precompraid, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {

		try {

			PreCompra preCompra = preCompraDao.carregaPorId(precompraid);

			ModelAndView model = new ModelAndView("checkoutcompraproduto", "pedido", preCompra);
			
			model.addObject("itens", preCompra.getItens());

			try {
				model.addObject("metodosentrega",
						entregaService.listaMetodosDeEntrega(preCompra, SessionUtil.getUsuarioId(session)));
			} catch (Exception e) {
				model = compras(request, session, null, null, null, null);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			try {
				model.addObject("formaspagamento", formaPagamentoService.listaFormasDePagamentoPorAdClientId());
			} catch (Exception e) {
				model = compras(request, session, null, null, null, null);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			return model;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			ModelAndView model = compras(request, session, null, null, null, null);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO");
			model.addObject("messageDetail", e.getMessage());
			return model;
		}
	}
	
	@RequestMapping(value = "/getdiscount", method = POST)
	public @ResponseBody BigDecimal getDesconto(String paymentId, BigDecimal oldValue, HttpSession session, String preCompraId) throws Exception
	{
		return preCompraService.getDesconto(paymentId, oldValue, usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session)), preCompraId);
	}

	@RequestMapping(value = "/validvoucher", method = POST)
	public @ResponseBody boolean validVoucher(String code, HttpSession session) throws Exception {
		Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		return voucherDao.isValid(code, usuario.getAdClientId());
	}

	@RequestMapping(value = "/compraproduto", method = GET)
	public String compraProduto(String id, String senhafinanceira, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request, String amrMetodoEntregaId, @RequestParam("formapagamentoid") String formaPagamentoId, String voucherId) {
		try {

			boolean autenticaCompra = SessionUtil.getAttribute(session, "autenticacompra").equals("Y");

			PreCompra preCompra = preCompraService.atualizaDadosDeEntregaEPagamento(id, SessionUtil.getUsuarioId(session), amrMetodoEntregaId, formaPagamentoId, voucherId);

			if (autenticaCompra) {
				Usuario usuario = usuarioDao.carregaPorId(preCompra.getAdUserId());
				if (usuario == null)
					throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

				if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
					throw new Exception("Senha financeira não confere");

				if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhafinanceira)))
					throw new Exception("Senha financeira não confere");
			}

			if (formaPagamentoId.equalsIgnoreCase("CARTAO")) {
				return "redirect:checkoutcartao?id=" + preCompra.getId();
			}

			String mensagem = preCompraService.compra(preCompra.getId(), "0", "Processando..");
			String cDebtPaymentId = preCompraService.getCDebtPaymentId();
			if (cDebtPaymentId != null && !cDebtPaymentId.isEmpty()) {
				return "redirect:comprasboleto?id=" + cDebtPaymentId + "&preCompraId=" + id;
			}

			MessageUtil.sucesso(redirectAttributes, "Parabéns!", mensagem);
			return "redirect:compraconcluida?id=" + preCompra.getId();

		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:checkoutcompra?precompraid=" + id;
		}
	}

	@RequestMapping(value = "/comprasboleto", method = GET)
	public ModelAndView compraComBoleto(String id, String preCompraId, HttpSession session) {

		/**
		 * Esvaziando carrinho de compras
		 */

		try {
			String[] venda = new String[] { "Y", "N" };
			String usuarioId = SessionUtil.getUsuarioId(session);
			for (int j = 0; j < venda.length; j++) {
				try {
					CarrinhoCompras cart = carrinhoComprasDao.carregaAbertoPorUsuario(usuarioId, venda[j]);
					if (cart != null) {
						List<CarrinhoComprasItem> lista = carrinhoComprasItemDao
								.listaPorCarrinhoDeCompras(cart.getId());
						for (int i = 0; i < lista.size(); i++) {
							carrinhoComprasItemDao.exclui(lista.get(i));
						}
						carrinhoComprasDao.exclui(cart);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}

		PreCompra pedido = null;
		try {
			pedido = preCompraDao.carregaPorId(preCompraId);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		ModelAndView model = new ModelAndView("comprasboleto", "id", id);
		model.addObject("pedido", pedido);
		return model;
	}

	@RequestMapping(value = "/compraconcluida", method = GET)
	public ModelAndView checkOutCartao(String id, HttpSession session) throws Exception {
		PreCompra preCompra = preCompraDao.carregaPorId(id);

		/**
		 * Esvaziando carrinho de compras
		 */

		String[] venda = new String[] { "Y", "N" };
		for (int j = 0; j < venda.length; j++) {
			try {
				CarrinhoCompras cart = carrinhoComprasDao.carregaAbertoPorUsuario(SessionUtil.getUsuarioId(session),
						venda[j]);
				if (cart != null) {
					List<CarrinhoComprasItem> lista = carrinhoComprasItemDao.listaPorCarrinhoDeCompras(cart.getId());
					for (int i = 0; i < lista.size(); i++) {
						carrinhoComprasItemDao.exclui(lista.get(i));
					}
					carrinhoComprasDao.exclui(cart);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ModelAndView model = new ModelAndView("compraconcluida", "pedido", preCompra);
		return model;
	}

	@RequestMapping(value = "/comprasprodutodetalhes", method = GET)
	public ModelAndView detalhaProduto(RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request, String id, Boolean venda) {

		try {
			Pacote pacote = pacoteDao.carregaPorId(id);
			if (pacote == null)
				throw new Exception("Pacote não encontrado com o ID: " + id);

			Produto produto = new Produto();
			
			produto.setId(pacote.getId());
			produto.setCodigo(pacote.getId());
			produto.setNome(pacote.getNome());
			produto.setDescricao(pacote.getDescricao());
			produto.setReadOnly(pacote.getObrigatorioAdesao().equalsIgnoreCase("Y"));
			produto.setSelecionado(pacote.getObrigatorioAdesao().equalsIgnoreCase("Y"));
			produto.setImagemURL(pacote.getImageURL());
			produto.setPrecoDe(pacote.getPrecoDe());
			produto.setPreco(pacote.getPrecoPor());
			produto.setCategoria(pacote.getCategoria());
			produto.setDescricaoCompleta(pacote.getDescricaoCompleta());
			produto.setPtsBinario(pacote.getPtsBinario());
			produto.setPtsCarreira(pacote.getPtsCarreira());

			ModelAndView model = new ModelAndView("comprasprodutodetalhes", "produto", produto);
			model.addObject("venda", venda);
			
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			ModelAndView model = compras(request, session, null, null, null, venda);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO");
			model.addObject("messageDetail", e.getMessage());
			return model;
		}
	}

}