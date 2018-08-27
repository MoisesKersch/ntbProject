package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Ativacoes;
import br.com.adaptaconsultoria.amr.model.AtivacoesLCTO;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivacoesDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivacoesLCTODao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

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
public class AtivacaoController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private AtivacoesDao ativacoesDao = DaoFactory.getInstance().getAtivacoesDao();
	private AtivacoesLCTODao ativacoesLCTODao = DaoFactory.getInstance().getAtivacoesLCTODao();

	@RequestMapping(value = "/ativacao", method = GET)
	public ModelAndView goFinanceiro(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setMenuAtivo(session, "ativacao");
		List<Ativacoes>  lista = new ArrayList<Ativacoes>();
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			lista = ativacoesDao.carregaPorCBPartnerId(usuario.getParceiroNegocios().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("ativacao", "lista", lista);
		return model;
	}

	@RequestMapping(value = "/buscaitensativacao", method = POST)
	public @ResponseBody List<AtivacoesLCTO> buscaItensCompra(String id) {
		List<AtivacoesLCTO> lista = new ArrayList<AtivacoesLCTO>();
		try {
			lista = ativacoesLCTODao.carregaPorAmrAtivacaoId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
