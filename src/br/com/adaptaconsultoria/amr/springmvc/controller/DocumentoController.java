package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.adaptaconsultoria.amr.model.Documento;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.DocumentoDao;
import br.com.adaptaconsultoria.amr.service.DocumentoService;
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
public class DocumentoController implements Serializable {

	private DocumentoDao documentoDao = DaoFactory.getInstance().getDocumentoDao();
	private DocumentoService documentoService = new DocumentoService();

	@RequestMapping(value = "/documentos", method = GET)
	public ModelAndView pagamentosPendentes(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "documentos");
		SessionUtil.setSubMenuAtivo(session, "documentos");
		List<Documento> lista = new ArrayList<Documento>();
		try {
			lista = documentoDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("documentos", "lista", lista);
		return model;
	}

	@RequestMapping(value = "/downloaddocumento/{id}", method = RequestMethod.GET)
	public void downloadDocumento(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

		try {

			Documento documento = documentoService.downloadDocumento(id);

			File arquivo = new File(documento.getUrl());
			response.setContentType(documento.getMimetype());
			response.setContentLength(new Long(arquivo.length()).intValue());
			response.setHeader("Content-Disposition", "attachment; filename=" + arquivo.getName());
			FileCopyUtils.copy(new FileInputStream(arquivo), response.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}