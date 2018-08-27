package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.CarrinhoCompras;
import br.com.adaptaconsultoria.amr.model.CarrinhoComprasItem;
import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.model.Login;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CarrinhoComprasItemDao;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.service.UsuarioService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

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
public class LoginController implements Serializable {

	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private UsuarioService usuarioService = new UsuarioService();
	private CarrinhoComprasDao carrinhoComprasDao = DaoFactory.getInstance().getCarrinhoComprasDao();
	private CarrinhoComprasItemDao carrinhoComprasItemDao = DaoFactory.getInstance().getCarrinhoComprasItemDao();

	@RequestMapping(value = "/logout", method = GET)
	public String logout(HttpSession session) {
		try {
			SessionUtil.logout(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = GET)
	public ModelAndView login(HttpSession session) {
		Login login = new Login();
		ModelAndView model = new ModelAndView("login", "login", login);
		return model;
	}

	@RequestMapping(value = "/login", method = POST)
	public String login(@Valid final Login login, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		try {

			if (result.hasErrors()) {
				return "login";
			}

			Usuario usuario = usuarioService.login(login.getLogin(), login.getSenha(), AMRProperties.getInstance().getPropriedade("adclientid"));
			
			SessionUtil.setUsuarioId(session, usuario);
			SessionUtil.setAttribute(session, "nit", usuario.getParceiroNegocios().getNit());

			String skin = "skin-blue";
			try {
				skin = usuario.getSkin();
			} catch (Exception e) {
				skin = "skin-blue";
			}
			SessionUtil.setAttribute(session, "skinUser", skin);

			Franqueado franqueado = franqueadoDao.carregaPorUsuario(usuario.getId());
			if (franqueado != null) {
				SessionUtil.setAttribute(session, "avatar", franqueado.getAvatar());
				SessionUtil.setAttribute(session, "autenticacompra", franqueado.getAutenticaCompra());
				SessionUtil.setAttribute(session, "codigobp", franqueado.getCodigo());
			}

			/**
			 * Esvaziando carrinho de compras
			 */

			String[] venda = new String[] { "Y", "N" };
			for (int j = 0; j < venda.length; j++) {
				try {
					CarrinhoCompras cart = carrinhoComprasDao.carregaAbertoPorUsuario(usuario.getId(), venda[j]);
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

			return "redirect:home";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:login";
		}
	}

	@RequestMapping(value = "/patrocinadorlogin", method = GET)
	public String fazerLoginComPatrocinadorDaSessao(HttpSession session, RedirectAttributes redirectAttributes) {
		try {

			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getPatrocinadorId(session));
			SessionUtil.setUsuarioId(session, usuario);
			SessionUtil.clearPatrocinador(session);

			Franqueado franqueado = franqueadoDao.carregaPorUsuario(usuario.getId());
			if (franqueado != null) {
				SessionUtil.setAttribute(session, "avatar", franqueado.getAvatar());
				SessionUtil.setAttribute(session, "autenticacompra", franqueado.getAutenticaCompra());
				SessionUtil.setAttribute(session, "codigobp", franqueado.getCodigo());
			}

			return "redirect:novocadastro";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:login";
		}
	}

	@RequestMapping(value = "/esqueciminhasenha", method = GET)
	public ModelAndView esqueciMinhaSenha(HttpSession session) {
		Login login = new Login();
		login.setSenha("2341234%#$%#@%$@");
		ModelAndView model = new ModelAndView("esqueciminhasenha", "login", login);
		return model;
	}

	@RequestMapping(value = "/esqueciminhasenha", method = POST)
	public String enviaEmailURLRedefinicaoDeSenha(@Valid final Login login, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {
		try {

			if (result.hasErrors()) {
				return "esqueciminhasenha";
			}

			UsuarioService manager = new UsuarioService();
			String mensagem = manager.solicitaNovaDefinicaoDeSenha(login.getLogin());

			MessageUtil.informacao(redirectAttributes, "Sucesso!", mensagem);
			return "redirect:login";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:esqueciminhasenha";
		}
	}

	@RequestMapping(value = "/envianovasenha", method = GET)
	public ModelAndView enviaEmailNovaSenha(String id, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {

			UsuarioService manager = new UsuarioService();
			String mensagem = manager.redefineSenhaEEnviaPorEMail(id);

			ModelAndView model = new ModelAndView("login", "login", new Login());
			model.addObject("messageType", "2");
			model.addObject("messageTitle", "Sucesso!");
			model.addObject("messageDetail", mensagem);
			return model;
		} catch (Exception e) {
			Login login = new Login();
			login.setSenha("2341234%#$%#@%$@");
			ModelAndView model = new ModelAndView("esqueciminhasenha", "login", login);
			model.addObject("messageType", "0");
			model.addObject("messageTitle", "ERRO!");
			model.addObject("messageDetail", ErrorUtil.getLastMessage(e));
			return model;
		}
	}

	public static void main(String[] args) throws Exception {

//		String login = "MASTER MIND";
//		String adUserId = "A7A9FC0EBD1B4AC4B7BEC6EF3A0B2B38";
//		String cBPartnerId = "EC9FB4E569694C65A6CD1D5B070E0F0D";

		UsuarioService service = new UsuarioService();

//		enviaEmailNovaSenha("E359FCB141F545D790BDBD65B751905A");
		// System.out.println(service.solicitaNovaDefinicaoDeSenha(login));
		service.redefineSenhaEEnviaPorEMail("D1D7EBA184554EA09F674B96FA1D2F77");
	}

}