package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.model.Transferencia;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ConfigDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.service.FinanceiroService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

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
public class FinanceiroController implements Serializable
{

	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private ConfigDao configDao = DaoFactory.getInstance().getConfigDao();
	private FinanceiroService financeiroService = new FinanceiroService();

	@RequestMapping("exibemenusaque")
	public void exibeSaque(HttpServletResponse response, HttpSession session) throws Exception
	{
		Franqueado franqueado = franqueadoDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		if (franqueado != null && franqueado.getLiberaSaque().equalsIgnoreCase("Y"))
			response.getWriter().write("true");
		else
			response.getWriter().write("");
		response.setStatus(200);
	}

	@RequestMapping(value = "/transferencia", method = GET)
	public ModelAndView trocaSenha(HttpSession session)
	{
		SessionUtil.setSubMenuAtivo(session, "transferencia");
		Transferencia transferencia = new Transferencia();
		ModelAndView model = new ModelAndView("transferencia", "transferencia", transferencia);
		return model;
	}

	@RequestMapping(value = "/transferencia", method = POST)
	public String transferencia(@Valid final Transferencia transferencia, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request)
	{
		try
		{
			try
			{
				Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
				transferencia.setTaxa(
						financeiroService.taxaTransferencia(usuario.getAdClientId(), transferencia.getValor()));
			} catch (Exception e)
			{
				transferencia.setTaxa(BigDecimal.ZERO);
			}

			if (result.hasErrors())
			{
				return "transferencia";
			}

			String retorno = financeiroService.transfere(SessionUtil.getUsuarioId(session), transferencia);
			MessageUtil.sucesso(redirectAttributes, "Sucesso!", retorno);

			return "redirect:transferencia";
		} catch (Exception e)
		{
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:transferencia";
		}
	}

	@RequestMapping(value = "/calculataxatransferencia", method = POST)
	public @ResponseBody String calculaTaxaTransferencia(HttpSession session, BigDecimal valor)
	{
		BigDecimal valorTaxa;
		String quem = "";
		String como = "";
		try
		{
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			Config config = configDao.carregaPorAdClientIdAndIsActiveAndSemPlanoId(usuario.getAdClientId());
			quem = config.getQuemPagaTransferencia();
			como = config.getComoPagaTransferencia();
			valorTaxa = financeiroService.taxaTransferencia(usuario.getAdClientId(), valor);
		} catch (Exception e)
		{
			e.printStackTrace();
			valorTaxa = BigDecimal.ZERO;
		}
		return "Você confirma a solicitação de transferência no valor de R$ "
				+ valor.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString().replace(".", "*").replace(",", ".")
						.replace("*", ",")
				+ "<br>" + "Com uma taxa de transferência de R$ "
				+ valorTaxa.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString().replace(".", "*").replace(",", ".")
						.replace("*", ",")
				+ "<br>" + "Paga pelo " + (quem.equalsIgnoreCase("R") ? "Remetente" : "Destinatário") + " com "
				+ (como.equalsIgnoreCase("S") ? "Saldo" : "Boleto");
	}

}