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

import br.com.adaptaconsultoria.amr.model.Mural;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.MuralDao;
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
public class MuralController implements Serializable {

	private MuralDao dao = DaoFactory.getInstance().getMuralDao();

	@RequestMapping(value = "/mural", method = GET)
	public ModelAndView pagamentosPendentes(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "mural");
		SessionUtil.setSubMenuAtivo(session, "mural");
		List<Mural> lista = new ArrayList<Mural>();
		try {
			lista = dao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("mural", "lista", lista);
		return model;
	}

}