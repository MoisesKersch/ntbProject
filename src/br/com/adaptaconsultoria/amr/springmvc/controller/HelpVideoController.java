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

import br.com.adaptaconsultoria.amr.model.HelpVideo;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.HelpVideoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
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
public class HelpVideoController implements Serializable {

	private HelpVideoDao helpVideoDao = DaoFactory.getInstance().getHelpVideoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "helpvideolista", method = GET)
	public ModelAndView lista(HttpSession session) {
		SessionUtil.setSubMenuAtivo(session, "help");
		List<HelpVideo> lista = new ArrayList<HelpVideo>();
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			lista = helpVideoDao.listaPorClient(usuario.getAdClientId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("helpvideolista", "lista", lista);
		return model;
	}

	@RequestMapping(value = "helpvideover", method = GET)
	public ModelAndView visualiza(String id, HttpSession session) throws Exception {
		HelpVideo video = helpVideoDao.carregaPorId(id);
		ModelAndView model = new ModelAndView("helpvideover", "video", video);
		return model;
	}

}