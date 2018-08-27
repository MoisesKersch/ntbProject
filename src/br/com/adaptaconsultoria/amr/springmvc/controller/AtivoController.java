package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Ativo;
import br.com.adaptaconsultoria.amr.model.AtivoAgenda;
import br.com.adaptaconsultoria.amr.model.AtivoPeriodo;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoAgendaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AtivoPeriodoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
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
public class AtivoController implements Serializable {

	private AtivoPeriodoDao ativoPeriodoDao = DaoFactory.getInstance().getAtivoPeriodoDao();
	private AtivoAgendaDao ativoAgendaDao = DaoFactory.getInstance().getAtivoAgendaDao();
	private AtivoDao ativoDao = DaoFactory.getInstance().getAtivoDao();

	@RequestMapping(value = "/ativos", method = GET)
	public ModelAndView novo(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "ativos");

		String adUserId = null;
		try {
			adUserId = SessionUtil.getUsuarioId(session);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		AtivoPeriodo periodo = null;
		try {
			periodo = ativoPeriodoDao.getProximoPeriodoParaInserir(adUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AtivoAgenda agenda = new AtivoAgenda();
		agenda.setPeriodo(periodo);

		List<AtivoAgenda> lista = null;
		try {
			lista = ativoAgendaDao.listaPorUsuario(adUserId);
		} catch (Exception e) {
			lista = new ArrayList<AtivoAgenda>();
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("ativos", "agenda", agenda);
		model.addObject("lista", lista);
		return model;
	}

	@RequestMapping(value = "/ativonovo", method = POST)
	public String salvaNovoAgendamentoDeAtivo(@Valid final AtivoAgenda agenda, BindingResult result, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletRequest request) {

		try {

			if (result.hasErrors()) {
				return "ativos";
			}

			String adUserId = SessionUtil.getUsuarioId(session);
			agenda.setId(ativoAgendaDao.getUUID());
			agenda.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
			agenda.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
			agenda.setCreatedby(adUserId);
			agenda.setUpdatedby(adUserId);
			agenda.setAdUserId(adUserId);
			agenda.setPeriodo(ativoPeriodoDao.getProximoPeriodoParaInserir(adUserId));
			ativoAgendaDao.salva(agenda);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Agendamento de compra de ativo criado com sucesso");

			return "redirect:ativos";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:ativos";
		}
	}

	@RequestMapping(value = "/listaativos", method = RequestMethod.GET)
	public @ResponseBody List<Ativo> listaAtivos(HttpSession session) {
		try {
			List<Ativo> lista = ativoDao.listaPorUsuario(SessionUtil.getUsuarioId(session));
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Ativo>();
	}

	@RequestMapping(value = "/ativoatualiza", method = POST)
	public String atualizaAgendamentoDeAtivo(String id, String mProductId, Model model, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {

		try {
			AtivoAgenda agenda = ativoAgendaDao.carregaPorId(id);
			if (agenda == null)
				throw new Exception("Nenhum registro com o ID: '" + id + "' foi encontrado no sistema");

			agenda.setmProductId(mProductId);
			ativoAgendaDao.atualiza(agenda);

			model.addAttribute("messageType", "3");
			model.addAttribute("messageTitle", "Parabéns!");
			model.addAttribute("messageDetail", "Agendamento de compra de ativo atualizado com sucesso");

			return "message";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "ERRO");
			model.addAttribute("messageDetail", e.getMessage());
			return "message";
		}
	}

}