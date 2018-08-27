package br.com.adaptaconsultoria.amr.springmvc.controller.rest;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.builder.object.User;
import br.com.adaptaconsultoria.amr.model.EnderecoView;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.EnderecoViewDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.service.UsuarioService;
import br.com.adaptaconsultoria.amr.util.cors.CorsAutenticar;

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
public class AutenticarController extends CorsAutenticar implements Serializable {

	private EnderecoViewDao enderecoViewDao = DaoFactory.getInstance().getEnderecoViewDao();
	private UsuarioService usuarioService = new UsuarioService();

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public @ResponseBody User autenticar(String username, String password, HttpSession session, HttpServletResponse response) throws Exception {
		autenticarOptions(response);

		Usuario usuario = usuarioService.login(username, password, AMRProperties.getInstance().getPropriedade("adclientid"));

		User user = new User();
		user.setIdnumber(usuario.getId());
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstname(usuario.getPrimeiroNome());
		user.setLastname(usuario.getSobreNome());
		user.setEmail(usuario.getParceiroNegocios().getEmail());

		try {
			EnderecoView endereco = enderecoViewDao.carregaPorPartner(usuario.getParceiroNegocios().getId());
			user.setCity(endereco.getCidade());
			user.setCountry(endereco.getPais());
		} catch (Exception e) {
			// Nada a fazer
		}

		return user;
	}

}
