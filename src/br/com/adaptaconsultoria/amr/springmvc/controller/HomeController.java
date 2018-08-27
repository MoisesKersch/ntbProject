package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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

import br.com.adaptaconsultoria.amr.model.Aceite;
import br.com.adaptaconsultoria.amr.model.AgendaContato;
import br.com.adaptaconsultoria.amr.model.AtivoStatus;
import br.com.adaptaconsultoria.amr.model.Banner;
import br.com.adaptaconsultoria.amr.model.Capa;
import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.model.Graduacao;
import br.com.adaptaconsultoria.amr.model.Mural;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AceiteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoStatusDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CapaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.GraduacaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MuralDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.springmvc.form.CrescimentoForm;
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
public class HomeController implements Serializable
{

	private AceiteDao aceiteDao = DaoFactory.getInstance().getAceiteDao();
	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private MuralDao muralDao = DaoFactory.getInstance().getMuralDao();
	private CapaDao capaDao = DaoFactory.getInstance().getCapaDao();
	// private AtivoDao ativoDao = DaoFactory.getInstance().getAtivoDao();
	private GraduacaoDao graduacaoDao = DaoFactory.getInstance().getGraduacaoDao();
	private AtivoStatusDao ativoStatusDao = DaoFactory.getInstance().getAtivoStatusDao();
	

	@RequestMapping(value = "/home", method = GET)
	public ModelAndView pagamentosPendentes(HttpSession session)
	{
		SessionUtil.setMenuAtivo(session, "home");
		SessionUtil.setSubMenuAtivo(session, "home");

		ModelAndView model = new ModelAndView("home");

		String adUserId = null;
		try
		{
			adUserId = SessionUtil.getUsuarioId(session);
			Aceite aceite = aceiteDao.carregaPorUsuario(adUserId);
			if (aceite == null)
				return new ModelAndView("termosuso");
			SessionUtil.setAceite(session, true);
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}

		Usuario usuario;

		CrescimentoForm form = new CrescimentoForm();
		Franqueado franqueado = null;
		try
		{
			franqueado = franqueadoDao.carregaPorUsuario(adUserId);
			if (franqueado != null)
			{
				if (franqueado.getLiberaCrescimento().equalsIgnoreCase("Y"))
				{
					usuario = usuarioDao.carregaPorId(adUserId);
					form.setCrescimento(usuario.getCrescimento());
					form.setLiberaCrescimento(true);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		model.addObject("crescimento", form == null ? "E" : form);
		model.addObject("liberaCrescimento", form.isLiberaCrescimento());

		List<Mural> noticias = new ArrayList<Mural>();
		
		BigDecimal qualificacaoRede = new BigDecimal("0");
		BigDecimal bonusIncentivoRede = new BigDecimal("0");
		BigDecimal indicePagamentosDiretos = new BigDecimal("0");
		BigDecimal contasAtivaRede = new BigDecimal("0");
		
		try
		{
			noticias = muralDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	
		model.addObject("noticias", noticias);
		model.addObject("mostrarNoticias", (noticias.size() > 0 ? "Y" : "N"));

		try
		{
			// model.addObject("percentual",
			// franqueado.getPercentualBinario().replaceAll("[.]", ",") + "%");
			model.addObject("perfil", franqueado.getPerfil());
			model.addObject("iconedesktop", franqueado.getIconeDesktop());
		} catch (Exception e)
		{
			// model.addObject("percentual", "0%");
			model.addObject("perfil", "");
			model.addObject("iconedesktop", "");
		}

		try
		{
			AtivoStatus status = ativoStatusDao.carregaPorUsuario(adUserId);
			// model.addObject("ativopago", ativoDao.pago(adUserId));
			model.addObject("ativostatus", status);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			Graduacao graduacao = graduacaoDao.carregaPorUsuario(adUserId);
			model.addObject("graduacaoNome", graduacao.getGraduacao());
			model.addObject("graduacaoIcone", graduacao.getIcone());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			List<Banner> banners = DaoFactory.getInstance().getBannerDao()
					.carregaPorEntidade(AMRProperties.getInstance().getPropriedade("adclientid"));
			model.addObject("banners", banners);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			usuario = usuarioDao.carregaPorId(adUserId);
			Capa capa = capaDao.carregaPorClient(usuario.getAdClientId());
			if (!capa.getUrlembed().contains("</iframe>"))
			{
				capa.setUrlembed(capa.getUrlembed() + "</iframe>");
			}
			model.addObject("urlembed", capa.getUrlembed());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return model;
	}
	
	@RequestMapping(value = "/getqualificacaorede", method = POST)
	public @ResponseBody String getQualificacaoRede(HttpSession session)
	{
		
		try
		{
			return muralDao.getQualificacaoRede(usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session)).getParceiroNegocios().getId()).setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()+"%";
		} catch (Exception e)
		{
			return null;
		}
	}
	
	
	@RequestMapping(value = "/getbonusincentivorede", method = POST)
	public @ResponseBody String getBonusIncentivoRede(HttpSession session)
	{
		
		try
		{
			return muralDao.getBonusIncentivoRede(usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session)).getParceiroNegocios().getId()).setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()+"%";
		} catch (Exception e)
		{
			return null;
		}
	}
	
	
	@RequestMapping(value = "/getindicepagamentosdiretos", method = POST)
	public @ResponseBody String getIndicePagamentosDiretos(HttpSession session)
	{
		
		try
		{
			return muralDao.getIndicePagamentosDiretos(usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session)).getParceiroNegocios().getId()).setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()+"%";
		} catch (Exception e)
		{
			return null;
		}
	}
	
	@RequestMapping(value = "/getcontasativarede", method = POST)
	public @ResponseBody String getContasAtivaRede(HttpSession session)
	{
		
		try
		{
			return muralDao.getContasAtivaRede(usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session)).getParceiroNegocios().getId()).setScale(1, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString()+"%";
		} catch (Exception e)
		{
			return null;
		}
	}

	@RequestMapping(value = "/cookiesdesabilitados", method = GET)
	public String cookieNaoEncontrado(HttpSession session)
	{
		return "cookiesdesabilitados";
	}

	@RequestMapping(value = "/termosuso", method = GET)
	public String termosDeUso(HttpSession session)
	{
		return "termosuso";
	}

	@RequestMapping(value = "/aceite", method = GET)
	public String aceitoTermosDeUso(HttpSession session, RedirectAttributes redirectAttributes)
	{

		try
		{
			String a = SessionUtil.getAceite(session);
			System.out.println("\n\n Aceite: " + a + "\n");
			if (a != null && a.equalsIgnoreCase("Y"))
				return "redirect:home";

			Aceite aceite = new Aceite();
			aceite.setId(aceiteDao.getUUID());
			aceite.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
			aceite.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
			aceite.setAdUserId(SessionUtil.getUsuarioId(session));
			aceite.setAceito("Y");
			aceite = aceiteDao.salva(aceite);

			SessionUtil.setAceite(session, true);
			return "redirect:compras";
		} catch (Exception e)
		{
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:home";
		}

	}

	@RequestMapping(value = "/crescimento", method = POST)
	public String defineCrescimento(@Valid final CrescimentoForm form, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request)
	{

		try
		{

			if (result.hasErrors())
			{
				return "homecrescimento";
			}

			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			if (usuario == null)
				throw new Exception("Nenhum usuário encontrado.");

			usuario.setCrescimento(form.getCrescimento());
			usuarioDao.atualiza(usuario);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Crescimento modificado com sucesso.");

			return "redirect:home";
		} catch (Exception e)
		{
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:home";
		}
	}

}