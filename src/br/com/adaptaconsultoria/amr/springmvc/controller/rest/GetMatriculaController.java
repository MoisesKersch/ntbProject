package br.com.adaptaconsultoria.amr.springmvc.controller.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.builder.object.Learning;
import br.com.adaptaconsultoria.amr.builder.object.Learnings;
import br.com.adaptaconsultoria.amr.model.Matricula;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.MatriculaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.util.cors.CorsGetMatricula;

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
public class GetMatriculaController extends CorsGetMatricula implements Serializable {

	private MatriculaDao matriculaDao = DaoFactory.getInstance().getMatriculaDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "/getmatricula", method = RequestMethod.POST)
	public @ResponseBody Learnings getMatricula(String idnumber, HttpSession session, HttpServletResponse response) throws Exception {
		getmatriculaOptions(response);

		Usuario usuario;
		try {
			usuario = usuarioDao.carregaPorId(idnumber);
		} catch (Exception e) {
			response.setStatus(403);
			return null;
		}

		List<Matricula> matriculas;
		try {
			matriculas = matriculaDao.porcBPartnerId(usuario.getParceiroNegocios().getId());
		} catch (Exception e) {
			matriculas = new ArrayList<Matricula>();
		}

		Learnings learnings = new Learnings();
		learnings.setUserid(usuario.getId());
		for (Matricula matricula : matriculas) {
			Learning learning = new Learning();
			learning.setCourseid(matricula.getCurso().getId());
			learning.setCoursename(matricula.getCurso().getNome());
			learning.setRoleid(5);
			learnings.getCourses().add(learning);
		}

		return learnings;
	}

}
