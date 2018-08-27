package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.builder.EMailBuilder;
import br.com.adaptaconsultoria.amr.builder.object.SuporteForm;
import br.com.adaptaconsultoria.amr.model.EMail;
import br.com.adaptaconsultoria.amr.model.ServidorEMail;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Controller
@Scope("request")
@SuppressWarnings("serial")
public class ContatoFormController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "/contatoform", method = GET)
	public ModelAndView contatoForm(HttpSession session) {
		SuporteForm suporteForm = new SuporteForm();

		SessionUtil.setMenuAtivo(session, "contatoform");
		SessionUtil.setSubMenuAtivo(session, "contatoform");

		ModelAndView model = new ModelAndView("contatoform", "suporteForm", suporteForm);
		return model;
	}

	@RequestMapping(value = "/contatoform", method = RequestMethod.POST)
	public String contatoFormPost(@Valid SuporteForm suporteForm, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
		Usuario usuario;
		try {
			usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			usuario = null;
		}

		boolean erro = true; String mensagem = "";
		if (!result.hasErrors() && usuario != null) {
			try {
				mensagem = enviarEmail(usuario.getParceiroNegocios().getEmail(), suporteForm);
				erro = false;
			} catch (Exception e) {
				MessageUtil.erro(redirectAttributes, "ERRO!", e);
				return "redirect:contatoform";
			}
		}
		if (erro)
			MessageUtil.erro(redirectAttributes, "ERRO!", "Campos obrigatório não preenchidos!");
		else
			MessageUtil.sucesso(redirectAttributes, "Sucesso!", mensagem);
		return "redirect:contatoform";
	}

	private String enviarEmail(String de, SuporteForm suporteForm) throws Exception {
		ServidorEMail servidor = EMailBuilder.criaServidorPorProperties();

//		servidor.setEmail(de);

		EMail email = new EMail();
		email.addDestinatario(AMRProperties.getInstance().getPropriedade("emails.suporte").split(";"));
		email.setServidor(servidor);
		email.setAssunto("SUPORTE: \"" + de + "\" - " + suporteForm.getAssunto());
		email.setMensagem(suporteForm.getDescricao());
		email.setHtml(true);
		email.envia();


		return "Seu e-mail de suporte foi enviado!";
	}

}