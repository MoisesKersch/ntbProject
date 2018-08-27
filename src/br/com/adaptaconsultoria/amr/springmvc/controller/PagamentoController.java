package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.PagamentoPendente;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.PagamentoPendenteDao;
import br.com.adaptaconsultoria.amr.service.PagamentoService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
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
public class PagamentoController implements Serializable {

	private PagamentoPendenteDao pagamentoPendenteDao = DaoFactory.getInstance().getPagamentoPendenteDao();
	private PagamentoService pagamentoService = new PagamentoService();

	@RequestMapping(value = "/gopagamentospendentes", method = GET)
	public ModelAndView goPagamentosPendentes(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setMenuAtivo(session, "financeiro");
		SessionUtil.setSubMenuAtivo(session, "pagamentospendentes");

		ModelAndView model = new ModelAndView("financeiro", "go", "pagamentospendentes");
		return model;
	}

	@RequestMapping(value = "/pagamentospendentes", method = GET)
	public ModelAndView pagamentosPendentes(HttpSession session) {
		SessionUtil.setSubMenuAtivo(session, "pagamentospendentes");
		List<PagamentoPendente> lista = new ArrayList<PagamentoPendente>();
		try {
			lista = pagamentoPendenteDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView("pagamentospendentes", "lista", lista);
		
		return model;
	}

	@RequestMapping(value = "/pagamentospendentes", method = POST)
	public String paga(@RequestParam("id") String id, @RequestParam("senha") String senha, @RequestParam("voucher") String voucher,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		String retorno = "";
		try {

			if (id == null || id.isEmpty())
				return "redirect:pagamentospendentes";

			String adUserId = SessionUtil.getUsuarioId(session);
			String[] selecao = id.replaceFirst("#", "").split("#");
			if (selecao != null && selecao.length > 0) {

				for (int i = 0; i < selecao.length; i++) {
					retorno += "<p>" + (i + 1) + ". " + pagamentoService.pagaTitulo(adUserId, selecao[i], senha, voucher) + "</p>";
				}
				MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Solicitação de pagamentos efetuada com sucesso.<br><br>" + retorno);
			}

			return "redirect:pagamentospendentes";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", retorno + "<br>" + e.getMessage());
			return "redirect:pagamentospendentes";
		}
	}

	@RequestMapping(value = "/downloadboleto/{id}", method = RequestMethod.GET)
	public void downloadBoleto(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

		try {

			File arquivo = pagamentoService.criaBoleto(id);

			response.setContentType("application/pdf");
			response.setContentLength(new Long(arquivo.length()).intValue());
			response.setHeader("Content-Disposition", "attachment; filename=" + arquivo.getName());
			FileCopyUtils.copy(new FileInputStream(arquivo), response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/paga_com_cartao/{id}", method = RequestMethod.GET)
	public String pagamentoComCartao(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		return "redirect:checkoutcartao?id=" + id;
	}

}