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

import br.com.adaptaconsultoria.amr.model.CarreiraStatus;
import br.com.adaptaconsultoria.amr.model.MeusPontos;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.CarreiraStatusDao;
import br.com.adaptaconsultoria.amr.persistence.dao.MeusPontosDao;
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
public class MeusPontosController implements Serializable {

	private MeusPontosDao meusPontosDao = DaoFactory.getInstance().getMeusPontosDao();
	private CarreiraStatusDao carreiraStatusDao = DaoFactory.getInstance().getCarreiraStatusDao();

	@RequestMapping(value = "/meuspontos", method = GET)
	public ModelAndView goMeuspontos(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "meuspontos");
		SessionUtil.setSubMenuAtivo(session, "meuspontos");
		List<MeusPontos> lista = new ArrayList<MeusPontos>();
		try {
			lista = meusPontosDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("meuspontos", "lista", lista);

		try {
			List<CarreiraStatus> status = carreiraStatusDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
			model.addObject("carreira", status);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

}