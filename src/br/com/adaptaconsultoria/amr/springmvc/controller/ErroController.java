package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class ErroController implements Serializable {

	@RequestMapping(value = "/erro", method = GET)
	public ModelAndView novo(HttpSession session, String titulo, String mensagem) {
		ModelAndView model = new ModelAndView("erro");
		model.addObject("titulo", titulo);
		model.addObject("mensagem", mensagem);
		return model;
	}

}