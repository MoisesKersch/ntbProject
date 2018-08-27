package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adaptaconsultoria.amr.model.Manutencao;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ManutencaoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

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
public class ManutencaoController implements Serializable {

	private ManutencaoDao manutencaoDao = DaoFactory.getInstance().getManutencaoDao();

	@RequestMapping(value = "/manutencao", method = GET)
	public ModelAndView manutencao() {

		ModelAndView model = new ModelAndView("manutencao");

		Manutencao manutencao = null;
		try {
			List<Manutencao> lista = manutencaoDao.listaAtivas(AMRProperties.getInstance().getPropriedade("adclientid"));
			manutencao = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (manutencao != null) {
			model.addObject("titulo", manutencao.getTitulo());
			model.addObject("descricao", manutencao.getDescricao());
		}
		return model;
	}

}