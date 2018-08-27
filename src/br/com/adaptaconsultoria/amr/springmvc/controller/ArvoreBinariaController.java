package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.model.RedeBinaria;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeBinariaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.service.RedeBinariaService;
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
public class ArvoreBinariaController implements Serializable {

	private RedeBinariaDao redeBinariaDao = DaoFactory.getInstance().getRedeBinariaDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private RedeBinariaService redeBinariaService = new RedeBinariaService();

	@RequestMapping(value = "/arvorebinaria", method = GET)
	public String inicio(HttpSession session) {
		SessionUtil.setSubMenuAtivo(session, "arvorebinaria");
		return "arvorebinaria";
	}

	@RequestMapping(value = "desenhaarvorebinaria", method = RequestMethod.GET)
	public @ResponseBody List<RedeBinaria> desenhaArvoreBinaria(@RequestParam("id") String id, HttpSession session) throws Exception {

		String sessionUser = null;
		if (SessionUtil.getReadOnly(session).equalsIgnoreCase("Y"))
			sessionUser = SessionUtil.getUsuarioFilho(session);
		else
			sessionUser = SessionUtil.getUsuarioId(session);

		if (id == null || id.equalsIgnoreCase(SessionUtil.getUsuarioId(session))) {
			id = sessionUser;
		}

		return redeBinariaService.montaRedeBinariaHTML5(id, sessionUser);
	}

	@RequestMapping(value = "buscaarvorebinaria", method = RequestMethod.GET)
	public @ResponseBody List<RedeBinaria> buscaRedeLinear(@RequestParam("login") String login, HttpSession session) throws Exception {

		List<RedeBinaria> lista = new ArrayList<RedeBinaria>();

		Usuario usuario = usuarioDao.carregaPorCodigoDoParceiroDeNegocios(login);

		if (usuario == null)
			usuario = usuarioDao.carregaPorLogin(login, AMRProperties.getInstance().getPropriedade("adclientid"));

		if (usuario != null) {

			String id = null;
			if (SessionUtil.getReadOnly(session).equalsIgnoreCase("Y"))
				id = SessionUtil.getUsuarioFilho(session);
			else
				id = SessionUtil.getUsuarioId(session);

			boolean permite = redeBinariaDao.descente(id, usuario.getId());

			if (permite)
				lista = redeBinariaService.montaRedeBinariaHTML5(usuario.getId(), SessionUtil.getUsuarioId(session));
		}

		return lista;
	}

}