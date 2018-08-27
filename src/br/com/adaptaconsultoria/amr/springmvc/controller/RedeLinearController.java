package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.model.RedeLinear;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeLinearDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
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
public class RedeLinearController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private RedeLinearDao redeLinearDao = DaoFactory.getInstance().getRedeLinearDao();

	@RequestMapping(value = "/redelinear", method = GET)
	public String inicio(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "redelinear");
		SessionUtil.setSubMenuAtivo(session, "redelinear");
		return "redelinear";
	}

	@RequestMapping(value = "/getredelinear", method = RequestMethod.GET)
	public @ResponseBody List<RedeLinear> getRedeLinear(HttpSession session)
	{
		Usuario usuario = new Usuario();
		
		try {
			usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		List<RedeLinear> redeLinear = redeLinearDao.getRedeLinear(usuario.getParceiroNegocios().getId(), usuario.getAdClientId());
		
		return redeLinear;
	
	}	
}