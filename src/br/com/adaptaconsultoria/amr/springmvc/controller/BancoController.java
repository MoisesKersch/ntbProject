package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Banco;
import br.com.adaptaconsultoria.amr.model.DadosBancarios;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.BancoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
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
public class BancoController implements Serializable {

	private BancoDao bancoDao = DaoFactory.getInstance().getBancoDao();
	private UsuarioService usuarioService = new UsuarioService();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "/dadosbancarios", method = GET)
	public ModelAndView dadosBancarios(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setSubMenuAtivo(session, "dadosbancarios");
		DadosBancarios dados = null;
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			dados = usuarioService.carregaDadosBancarios(usuario.getParceiroNegocios().getId(), usuario.getId());
		} catch (Exception e) {
			dados = new DadosBancarios();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
		}
		return new ModelAndView("dadosbancarios", "dados", dados);
	}

	@RequestMapping(value = "/dadosbancarios", method = POST)
	public String editaDadosBancarios(@Valid final DadosBancarios dados, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {
		try {

			if (result.hasErrors()) {
				return "dadosbancarios";
			}

			usuarioService.salvaDadosBancarios(dados);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Dados bancários salvos com sucesso!");

			return "redirect:dadosbancarios";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:dadosbancarios";
		}
	}

	@RequestMapping(value = "/lista_bancos", method = RequestMethod.GET)
	public @ResponseBody List<Banco> listaBancos() {
		try {
			return bancoDao.lista("id");
		} catch (Exception e) {
			return new ArrayList<Banco>();
		}
	}

}