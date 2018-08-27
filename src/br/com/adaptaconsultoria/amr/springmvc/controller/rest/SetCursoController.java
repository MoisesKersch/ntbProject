package br.com.adaptaconsultoria.amr.springmvc.controller.rest;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.builder.object.Course;
import br.com.adaptaconsultoria.amr.model.Curso;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.CursoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.util.cors.CorsSetCurso;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Controller
@Scope("request")
@SuppressWarnings("serial")
public class SetCursoController extends CorsSetCurso implements Serializable {

	private CursoDao cursoDao = DaoFactory.getInstance().getCursoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "/setcurso", method = RequestMethod.POST)
	public @ResponseBody Course setCurso(String idnumber, Course course, HttpSession session, HttpServletResponse response) throws Exception {
		setcursoOptions(response);

		Usuario usuario;
		try {
			usuario = usuarioDao.carregaPorId(idnumber);
		} catch (Exception e) {
			response.setStatus(403);
			return null;
		}

		Curso curso;
		try {
			curso = cursoDao.carregaPorId(course.getCourseid());
		} catch (Exception e) {
			curso = null;
		}

		boolean novo = false;
		if (curso == null) {
			novo = true;
			curso = new Curso();
			curso.setId(course.getCourseid());
			curso.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
			curso.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
			curso.setCreatedby(usuario.getId());
		}

		curso.setUpdatedby(usuario.getId());
		curso.setNome(course.getFullname());
		curso.setNomeCurto(course.getShortname());
		curso.setDescricao(course.getSummary());
		curso.setNumSessao(course.getNumsections());

		if (novo) {
			curso = cursoDao.salva(curso);
		} else {
			curso = cursoDao.atualiza(curso);
		}

		return course;
	}

}
