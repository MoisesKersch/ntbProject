package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adaptaconsultoria.amr.model.Franqueado;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
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
public class FranqueadoController implements Serializable {

	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();

	@RequestMapping(value = "/empreendedor", method = GET)
	public ModelAndView pagamentosPendentes(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "empreendedor");
		SessionUtil.setSubMenuAtivo(session, "empreendedor");

		Franqueado franqueado = null;
		try {
			franqueado = franqueadoDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
			
		} catch (Exception e) {
			e.printStackTrace();
			franqueado = new Franqueado();
		}

		ModelAndView model = new ModelAndView("empreendedor", "franqueado", franqueado);
		return model;
	}

}