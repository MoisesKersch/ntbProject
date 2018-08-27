package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Chaveiro;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.service.UsuarioService;
import br.com.adaptaconsultoria.amr.springmvc.propertyeditor.UsuarioPropertyEditor;
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
public class SenhaFinanceiraController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private UsuarioService usuarioService = new UsuarioService();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Usuario.class, new UsuarioPropertyEditor());
	}

	@RequestMapping(value = "/senhafinanceira", method = GET)
	public ModelAndView senhafinanceira(HttpSession session) throws Exception {
		SessionUtil.setSubMenuAtivo(session, "senhafinanceira");

		boolean temSenha = false;
		Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		if (usuario.getSenhaFinanceira() != null && !usuario.getSenhaFinanceira().isEmpty()) {
			temSenha = true;
		}

		Chaveiro chaveiro = new Chaveiro();
		chaveiro.setTemSenha(temSenha);
		ModelAndView model = new ModelAndView("senhafinanceira", "chaveiro", chaveiro);
		return model;
	}

	@RequestMapping(value = "/gosenhafinanceira", method = GET)
	public ModelAndView goSenhaFinanceira(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setMenuAtivo(session, "meusdados");
		SessionUtil.setSubMenuAtivo(session, "senhafinanceira");

		ModelAndView model = new ModelAndView("meusdados", "go", "senhafinanceira");
		return model;
	}

	@RequestMapping(value = "/senhafinanceira", method = POST)
	public String senhafinanceira(@Valid final Chaveiro chaveiro, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {

		try {

			if (result.hasErrors()) {
				return "senhafinanceira";
			}

			if (!chaveiro.novasSenhasIguais())
				throw new Exception("Novas senhas não conferem!");

			usuarioService.alteraSenhaFinanceira(SessionUtil.getUsuarioId(session), chaveiro.getSenhaAtualFinanceira(), chaveiro.getSenhaNova());

			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Sua senha financeira foi alterada com sucesso");

			return "redirect:senhafinanceira";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:senhafinanceira";
		}
	}

	@RequestMapping(value = "/envianovasenhafinanceira", method = GET)
	public String enviaEmailNovaSenhaFinanceira(RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {

			UsuarioService manager = new UsuarioService();
			String mensagem = manager.redefineSenhaFinanceiraEEnviaPorEMail(SessionUtil.getUsuarioId(session));

			MessageUtil.sucesso(redirectAttributes, "SUCESSO!", mensagem);
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
		}
		return "redirect:gosenhafinanceira";
	}

}