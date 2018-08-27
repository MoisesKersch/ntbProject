package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Cadastro;
import br.com.adaptaconsultoria.amr.model.SaldoResumido;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.SaldoResumidoDao;
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
public class MenuController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private SaldoResumidoDao saldoResumidoDao = DaoFactory.getInstance().getSaldoResumidoDao();
	private UsuarioService usuarioService = new UsuarioService();

	@RequestMapping(value = "/meusdados", method = GET)
	public ModelAndView goMeusDados(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setMenuAtivo(session, "meusdados");
		SessionUtil.setSubMenuAtivo(session, "meucadastro");

		ModelAndView model = new ModelAndView("meusdados");
		Cadastro cadastro = null;
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			cadastro = usuarioService.criaCadastroDeParceiroDeNegocios(usuario.getParceiroNegocios().getId(), true, usuario.getId());
			model.addObject("cadastro", cadastro);
			model.addObject("meuID", usuario.getParceiroNegocios().getCodigo());
		} catch (Exception e) {
			cadastro = new Cadastro();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
		}
		return model;
	}
	
	@RequestMapping(value = "/financeiro", method = GET)
	public ModelAndView goFinanceiro(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setMenuAtivo(session, "financeiro");
		SessionUtil.setSubMenuAtivo(session, "saldoresumido");
		List<SaldoResumido> lista = new ArrayList<SaldoResumido>();
		try {
			lista = saldoResumidoDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("financeiro", "lista", lista);
		
		return model;
	}
	
	@RequestMapping(value = "/saldoresumido", method = GET)
	public ModelAndView saldoResumido(HttpSession session, RedirectAttributes redirectAttributes) 
	{
		List<SaldoResumido> lista = new ArrayList<SaldoResumido>();
		SessionUtil.setSubMenuAtivo(session, "saldoresumido");
		try {
			lista = saldoResumidoDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView model = new ModelAndView("saldoresumido", "lista", lista);
		
		return model;
	}


	@RequestMapping(value = "/salaconferencia", method = GET)
	public String goSalaconferencia(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "salaconferencia");
		SessionUtil.setSubMenuAtivo(session, "salaconferencia");
		return "salaconferencia";
	}

	@RequestMapping(value = "/minhasfaturas", method = GET)
	public String goMinhasfaturas(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "minhasfaturas");
		SessionUtil.setSubMenuAtivo(session, "minhasfaturas");
		return "minhasfaturas";
	}

	@RequestMapping(value = "/suporte", method = GET)
	public String goSuporte(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "suporte");
		SessionUtil.setSubMenuAtivo(session, "suporte");
		return "suporte";
	}

	@RequestMapping(value = "/redebinaria", method = GET)
	public String goRedeBinaria(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "redebinaria");
		SessionUtil.setSubMenuAtivo(session, "arvorebinaria");
		return "redebinaria";
	}
	
	

}