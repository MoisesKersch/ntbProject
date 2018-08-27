package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.CarrinhoCompras;
import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasItemDao;
import br.com.adaptaconsultoria.amr.service.CarrinhoComprasService;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

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
public class CarrinhoComprasController implements Serializable {

	private CarrinhoComprasService carrinhoComprasService = new CarrinhoComprasService();
	private CarrinhoComprasItemDao carrinhoComprasItemDao = DaoFactory.getInstance().getCarrinhoComprasItemDao();

	@RequestMapping(value = "/cartlista", method = GET)
	public ModelAndView novo(HttpSession session, RedirectAttributes redirectAttributes, Boolean venda) {

		if (venda == null)
			venda = false;

		CarrinhoCompras cart = new CarrinhoCompras();
		List<CarrinhoComprasItem> itens = new ArrayList<CarrinhoComprasItem>();
		try {
			String adUserId = SessionUtil.getUsuarioId(session);
			cart = carrinhoComprasService.carregaCarrinhoDeCompras(adUserId, venda);
			itens = carrinhoComprasItemDao.listaPorCarrinhoDeCompras(cart.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("comprascartitens", "cart", cart);
		
		BigDecimal valorTotalSemDesconto = new BigDecimal("0");
		
		for (CarrinhoComprasItem x: itens)
		{
			valorTotalSemDesconto = valorTotalSemDesconto.add(x.getTotalPrecoSemDesconto());
		}
		
		model.addObject("lista", itens);
		model.addObject("valorTotalSemDesconto", valorTotalSemDesconto);
		model.addObject("venda", venda);

		return model;
	}

	@RequestMapping(value = "/cartaddquantidade", method = POST)
	public String adicionaQuantidadeAoItem(String id, Model model, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			CarrinhoComprasItem item = carrinhoComprasItemDao.carregaPorId(id);
			if (item == null)
				throw new Exception("Nenhum item com esse ID: " + id + " foi encontrado no banco de dados");

			item.setQuantidade(item.getQuantidade() + 1);
			
			carrinhoComprasItemDao.atualiza(item);

			model.addAttribute("messageType", "3");
			model.addAttribute("messageTitle", "Parabéns!");
			model.addAttribute("messageDetail", "Item adicionado ao carrinho de compras");

			return "message";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "ERRO");
			model.addAttribute("messageDetail", e.getMessage());
			return "message";
		}
	}

	@RequestMapping(value = "/cartremovequantidade", method = POST)
	public String removeQuantidadeAoItem(String id, Model model, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			CarrinhoComprasItem item = carrinhoComprasItemDao.carregaPorId(id);
			if (item == null)
				throw new Exception("Nenhum item com esse ID: " + id + " foi encontrado no banco de dados");

			item.setQuantidade(item.getQuantidade() - 1);
			if (item.getQuantidade().intValue() <= 0)
				carrinhoComprasItemDao.exclui(item);
			else
				carrinhoComprasItemDao.atualiza(item);

			model.addAttribute("messageType", "3");
			model.addAttribute("messageTitle", "Parabéns!");
			model.addAttribute("messageDetail", "Item removido ao carrinho de compras");

			return "message";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "ERRO");
			model.addAttribute("messageDetail", e.getMessage());
			return "message";
		}
	}

	@RequestMapping(value = "/cartremoveitem", method = POST)
	public String removeItem(String id, Model model, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			CarrinhoComprasItem item = carrinhoComprasItemDao.carregaPorId(id);
			if (item == null)
				throw new Exception("Nenhum item com esse ID: " + id + " foi encontrado no banco de dados");

			carrinhoComprasItemDao.exclui(item);

			model.addAttribute("messageType", "3");
			model.addAttribute("messageTitle", "Parabéns!");
			model.addAttribute("messageDetail", "Item removido do carrinho de compras");

			return "message";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "ERRO");
			model.addAttribute("messageDetail", e.getMessage());
			return "message";
		}
	}

	@RequestMapping(value = "/cartadd", method = POST)
	public String adicionaProdutoAoCarrinhoDeCompras(String id, Boolean venda, Model model, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			carrinhoComprasService.adicionaProdutoAoCarinho(SessionUtil.getUsuarioId(session), id, venda);

			model.addAttribute("messageType", "3");
			model.addAttribute("messageTitle", "Parabéns!");
			model.addAttribute("messageDetail", "Item adicionado ao carrinho de compras");

			return "message";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "ERRO");
			model.addAttribute("messageDetail", e.getMessage());
			return "message";
		}
	}

	@RequestMapping(value = "/cartremove", method = POST)
	public String removeProdutoDoCarrinhoDeCompras(String id, Model model, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {

		try {

			carrinhoComprasService.removeProdutoDoCarinho(id);

			model.addAttribute("messageType", "3");
			model.addAttribute("messageTitle", "Parabéns!");
			model.addAttribute("messageDetail", "Item removido do carrinho de compras");

			return "message";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "ERRO");
			model.addAttribute("messageDetail", e.getMessage());
			return "message";
		}
	}

}