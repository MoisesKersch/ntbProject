package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adaptaconsultoria.amr.util.io.FileUtil;

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
public class PDFController implements Serializable {

	@RequestMapping(value = "/pdfvisualiza", method = GET)
	public ModelAndView novo(HttpServletRequest request, String arquivo) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("/");
		File file = new File(arquivo);
		String target = "resources/temp/" + file.getName();
		FileUtil.copy(file.getAbsolutePath(), path + target);
		ModelAndView model = new ModelAndView("visualizapdf");
		model.addObject("arquivo", target);
		return model;
	}

}