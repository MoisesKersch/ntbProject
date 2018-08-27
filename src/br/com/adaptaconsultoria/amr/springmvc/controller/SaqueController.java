package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Config;
import br.com.adaptaconsultoria.amr.model.DadosBancarios;
import br.com.adaptaconsultoria.amr.model.Saque;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ConfigDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.service.FinanceiroService;
import br.com.adaptaconsultoria.amr.service.UsuarioService;
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
public class SaqueController implements Serializable {

	private FinanceiroService financeiroService = new FinanceiroService();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private ConfigDao configDao = DaoFactory.getInstance().getConfigDao();
	private UsuarioService usuarioService = new UsuarioService();

	@RequestMapping(value = "/saque", method = GET)
	public ModelAndView trocaSenha(HttpSession session) {

		ModelAndView model = null;
		DadosBancarios dados = null;
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			SessionUtil.setAttribute(session, "nit", usuario.getParceiroNegocios().getNit());
			dados = usuarioService.carregaDadosBancarios(usuario.getParceiroNegocios().getId(), usuario.getId());
			if (dados.isDadosValidos()) {
				SessionUtil.setSubMenuAtivo(session, "saque");
				Saque saque = new Saque();
				model = new ModelAndView("saque", "saque", saque);
				model.addObject("taxaSaque", financeiroService.taxaSaque(usuario.getAdClientId()));
				return model;
			}
			SessionUtil.setSubMenuAtivo(session, "dadosbancarios");
			model = new ModelAndView("dadosbancarios", "dados", dados);
			model.addObject("messageType", "2");
			model.addObject("messageTitle", "Informação!");
			model.addObject("messageDetail", "Primeiro você deve preencher seus dados bancários, para depois poder solicitar saques.");
		} catch (Exception e) {
			if (model == null)
				model = new ModelAndView("dadosbancarios", "dados", dados);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO");
			model.addObject("messageDetail", e.getMessage());
		}
		return model;
	}

	@RequestMapping(value = "/saque", method = POST)
	public String saque(@Valid final Saque saque, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			if (result.hasErrors()) {
				return "saque";
			}

			financeiroService.saca(SessionUtil.getUsuarioId(session), saque);

			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Solicitação de saque efetuada com sucesso.");

			return "redirect:saque";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:saque";
		}
	}

	@RequestMapping(value = "/calculataxasaque", method = POST)
	public @ResponseBody String calculaTaxaSaque(HttpSession session, BigDecimal valor) {
		BigDecimal valorTaxa;
		String quem = "";
		String como = "";
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			Config config = configDao.carregaPorAdClientIdAndIsActiveAndSemPlanoId(usuario.getAdClientId());
			quem = config.getQuemPagaTransferencia();
			como = config.getComoPagaTransferencia();
			valorTaxa = financeiroService.taxaSaque(usuario.getAdClientId(), valor);
		} catch (Exception e) {
			e.printStackTrace();
			valorTaxa = BigDecimal.ZERO;
		}
		return "Você confirma a solicitação de saque no valor de R$ " + valor.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString().replace(".", "*").replace(",", ".").replace("*", ",") + "<br>"
			 + "Com uma taxa de saque de R$ " + valorTaxa.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString().replace(".", "*").replace(",", ".").replace("*", ",") + "<br>"
	 		 + "Paga pelo " + (quem.equalsIgnoreCase("R") ? "Remetente" : "Destinatário") + " com " + (como.equalsIgnoreCase("S") ? "Saldo" : "Boleto");
	}

}
