package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.PacoteUpgrade;
import br.com.adaptaconsultoria.amr.model.PreCompra;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.PacoteUpgradeDao;
import br.com.adaptaconsultoria.amr.persistence.dao.PreCompraDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.service.EntregaService;
import br.com.adaptaconsultoria.amr.service.FormaPagamentoService;
import br.com.adaptaconsultoria.amr.service.PreCompraService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.FormatUtilities;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("request")
public class UpgradeController implements Serializable {

	private PacoteUpgradeDao pacoteUpgradeDao = DaoFactory.getInstance().getPacoteUpgradeDao();
	private PreCompraDao preCompraDao = DaoFactory.getInstance().getPreCompraDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private EntregaService entregaService = new EntregaService();
	private FormaPagamentoService formaPagamentoService = new FormaPagamentoService();
	private PreCompraService preCompraService = new PreCompraService();

	@RequestMapping(value = "/upgrade", method = GET)
	public ModelAndView upgrade(HttpSession session) 
	{
		SessionUtil.setMenuAtivo(session, "upgrade");
		SessionUtil.setSubMenuAtivo(session, "upgrade");
		
		List<PacoteUpgrade> lista = new ArrayList<PacoteUpgrade>();
		try {
			lista = pacoteUpgradeDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
		}
		ModelAndView model = new ModelAndView("upgrade", "lista", lista);
		System.out.print(lista);
		return model;
	}

	@RequestMapping(value = "/precompraupgrade", method = GET)
	public ModelAndView upgrade(String pacoteUpgradeId, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {

			String adUserId = SessionUtil.getUsuarioId(session);
			PreCompra preCompra = preCompraService.criaPreCompraDePacoteUpgrade(adUserId, pacoteUpgradeId, false);

			ModelAndView model = new ModelAndView("precompraupgrade", "pedido", preCompra);
			model.addObject("itens", preCompra.getItens());

			try {
				model.addObject("metodosentrega", entregaService.listaMetodosDeEntrega(preCompra, SessionUtil.getUsuarioId(session)));
			} catch (Exception e) {
				model = upgrade(session);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			try {
				model.addObject("formaspagamento", formaPagamentoService.listaFormasDePagamentoPorAdClientId());
			} catch (Exception e) {
				model = upgrade(session);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			return model;
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return upgrade(session);
		}
	}
	
	@RequestMapping(value = "/upgradedetalhe", method = GET)
	public ModelAndView upgrade(HttpSession session, HttpServletRequest request, String upgrade) throws Exception 
	{
		ModelAndView model = new ModelAndView("upgradedetalhe");
		PacoteUpgrade pacote = new PacoteUpgrade();
		
		String getId = SessionUtil.getUsuarioId(session);
		
		
		try {
			pacote = pacoteUpgradeDao.getPacoteUpgradeDetalha(getId, upgrade);
		} catch (Exception e) {
		}
		System.out.println(pacote);
		
		model.addObject("pacote", pacote);
		
		return model;
	}

	@RequestMapping(value = "/checkoutprecompraupgrade", method = GET)
	public ModelAndView checkoutPreCompraUpgrade(String id, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {

			PreCompra preCompra = preCompraDao.carregaPorId(id);

			ModelAndView model = new ModelAndView("precompraupgrade", "pedido", preCompra);
			model.addObject("itens", preCompra.getItens());

			try {
				model.addObject("metodosentrega", entregaService.listaMetodosDeEntrega(preCompra, SessionUtil.getUsuarioId(session)));
			} catch (Exception e) {
				model = upgrade(session);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			try {
				model.addObject("formaspagamento", formaPagamentoService.listaFormasDePagamentoPorAdClientId());
			} catch (Exception e) {
				model = upgrade(session);
				model.addObject("messageType", "0");
				model.addObject("messageTitle", "ERRO");
				model.addObject("messageDetail", e.getMessage());
			}

			return model;
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return upgrade(session);
		}
	}

	@RequestMapping(value = "/compraupgrade", method = GET)
	public String upgrade(String id, String senhaFinanceira, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request,
			String amrMetodoEntregaId, @RequestParam("formapagamentoid") String formaPagamentoId) {

		try {

			boolean autenticaCompra = SessionUtil.getAttribute(session, "autenticacompra").equals("Y");

			PreCompra preCompra = preCompraService.atualizaDadosDeEntregaEPagamento(id, SessionUtil.getUsuarioId(session), amrMetodoEntregaId, formaPagamentoId, null);

			if (autenticaCompra) {
				Usuario usuario = usuarioDao.carregaPorId(preCompra.getAdUserId());
				if (usuario == null)
					throw new Exception("Nenhum registro de usuario com o id de sessão encontrado no banco de dados");

				if (usuario.getSenhaFinanceira() == null || usuario.getSenhaFinanceira().isEmpty())
					throw new Exception("Senha financeira não confere");

				if (!usuario.getSenhaFinanceira().equals(FormatUtilities.sha1Base64(senhaFinanceira)))
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
			return "redirect:checkoutprecompraupgrade?id=" + id;
		}
	}

}