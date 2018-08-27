package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.model.Agenda;
import br.com.adaptaconsultoria.amr.model.AgendaAcao;
import br.com.adaptaconsultoria.amr.model.AgendaContato;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaAcaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaContatoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.AgendaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

@SuppressWarnings("serial")
@Controller
@Scope("request")
public class AgendaContatoController extends Agenda implements Serializable
{
	private AgendaContatoDao agendaContatoDao = DaoFactory.getInstance().getAgendaContatoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private AgendaAcaoDao agendaAcaoDao = DaoFactory.getInstance().getAgendaAcaoDao();
	private AgendaDao agendaDao = DaoFactory.getInstance().getAgendaDao();
	private static Usuario usuario;

	@RequestMapping(value = "/agendacontato", method = GET)
	public ModelAndView iniciar(HttpSession session) throws Exception
	{
		
		usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));

		AgendaAcaoDao agendaAcaoDao = DaoFactory.getInstance().getAgendaAcaoDao();
		
		ModelAndView model = new ModelAndView("agendacontato");
		
		model.addObject("agendaAcao", agendaAcaoDao.getAgendaAcao(usuario.getAdClientId()));
		
		List<AgendaAcao> agenda = agendaAcaoDao.getAgendaAcao(usuario.getAdClientId());
		
		System.out.println(agenda);
		
		
		
		return model;
	}
	
	@RequestMapping(value = "/getlist", method = GET)
	public @ResponseBody List<AgendaContato> getList(HttpSession session) throws Exception
	{
		usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		return agendaContatoDao.listaContato(usuario.getParceiroNegocios().getId());
	}
	
	@RequestMapping(value = "/getactionagenda", method = GET)
	public @ResponseBody List<AgendaAcao> getActionAgenda(HttpSession session) throws Exception
	{
		return agendaAcaoDao.getAgendaAcao(usuario.getAdClientId());
	}
	
	@RequestMapping(value = "/save", method = POST)
	public @ResponseBody AgendaContato salvarNovo(AgendaContato agendaContato, RedirectAttributes redirectAttributes, HttpSession session, String ultimoContato, String proximoContato) throws Exception
	{		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		
		Date ultimoContatoF = null;
		Date proximoContatoF = null;
		
		if ((ultimoContato != null && ultimoContato != "") && (proximoContato != null && proximoContato != ""))
		{
			try {
				ultimoContatoF = (Date) formatter.parse(ultimoContato);
			} catch (Exception e) {
				ultimoContatoF = null;
			}
			
			try {
				proximoContatoF = (Date) formatter.parse(proximoContato);
			} catch (Exception e) {
				proximoContatoF = null;
			}
		}	
		
		agendaContato.setId(agendaContatoDao.getUUID());
		agendaContato.setAdClientId(usuario.getAdClientId());
		agendaContato.setAdOrgId(usuario.getAdOrgId());
		agendaContato.setCreatedby(usuario.getId());
		agendaContato.setUpdatedby(usuario.getId());
		agendaContato.setcBpartneriId(usuario.getParceiroNegocios().getId());
		agendaContato.setProximoContato(proximoContatoF);
		agendaContato.setUltimoContato(ultimoContatoF);
		agendaContato.setClassificacao(new BigDecimal("1"));
		
		try
		{
			agendaContatoDao.salva(agendaContato);
			MessageUtil.sucesso(redirectAttributes, "Sucesso!", "agendaContato salvo com sucesso");
			
			return agendaContato;
		} catch (Exception e)
		{
			return null;
		}
	}

	@RequestMapping(value = "/delete", method = POST)
	public @ResponseBody boolean remove(String id, RedirectAttributes redirectAttributes, HttpSession session) throws Exception
	{
		AgendaContato delete = agendaContatoDao.carregaPorId(id);
		
		try
		{
			agendaContatoDao.exclui(delete);	
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
	
	@RequestMapping(value = "/edit", method = POST)
	public @ResponseBody AgendaContato edit(AgendaContato agendaContato, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, String id, String ultimoContato, String proximoContato) throws Exception
	{
		AgendaContato cont = agendaContatoDao.carregaPorId(agendaContato.getId());
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		
		Date ultimoContatoF = null;
		Date proximoContatoF = null;
		
		if ((ultimoContato != null && ultimoContato != "") && (proximoContato != null && proximoContato != ""))
		{
			ultimoContatoF = (Date) formatter.parse(ultimoContato);
			proximoContatoF = (Date) formatter.parse(proximoContato);
		}	
		
		cont.setNome(agendaContato.getNome());
		cont.setTelefone(agendaContato.getTelefone());
		cont.setCelular(agendaContato.getCelular());
		cont.setEmail(agendaContato.getEmail());
		cont.setCidade(agendaContato.getCidade());
		cont.setEstado(agendaContato.getEstado());
		cont.setDescricao(agendaContato.getDescricao());
		cont.setProximoContato(proximoContatoF);
		cont.setUltimoContato(ultimoContatoF);
		
		try {
			agendaContatoDao.atualiza(cont);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		MessageUtil.sucesso(redirectAttributes, "Sucesso!", "agendaContato Editado com Sucesso");
		
		return cont;
	}
	
	@RequestMapping(value = "/loadcontact", method = GET)
	public @ResponseBody AgendaContato loadContactId(String id) throws Exception
	{	
		return agendaContatoDao.carregaPorId(id);
	}
	
	@RequestMapping(value = "/loadagenda", method = GET)
	public @ResponseBody Agenda loadAgendaId(String id, HttpServletRequest request) throws Exception
	{	
		Agenda agenda = agendaDao.carregaPorId(id);
        request.setAttribute("acaoSelected", agenda.getAmrAgendaAcaoId().getNome());
		return agenda;
	}
	
	@RequestMapping(value = "/getagendasbyid", method = POST)
	public @ResponseBody List<Agenda> getContactById(String id) throws Exception
	{
		AgendaContato agendaContato = agendaContatoDao.carregaPorId(id);
		
		System.out.println(agendaContato.getAgendas());
		return agendaContato.getAgendas();
	}

	@RequestMapping(value = "/agendasave", method = POST)
	public @ResponseBody Agenda setAgenda(@ModelAttribute("agenda") Agenda agenda, BindingResult result, String quando, HttpSession session, 
			RedirectAttributes redirectAttributes, String idContato, String amrAgendaAcaoId) throws Exception
	{
		usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		AgendaContato agendaContato = agendaContatoDao.carregaPorId(idContato);
		
		List<Agenda> getAgenda = agendaContato.getAgendas();
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		Date quandoF = null;
		
		if (quando != null && quando != "")
		{
			try {
				quandoF = (Date) formatter.parse(quando);
			} catch (Exception e) {
				quandoF = null;
			}
		}
		
		agenda.setId(agendaContatoDao.getUUID());
		agenda.setAdClientId(agendaContato.getAdClientId());
		agenda.setAdOrgId(agendaContato.getAdOrgId());
		agenda.setAmrListaId(agendaContato);
		agenda.setcBpartnerId(agendaContato.getcBpartneriId());
		
		if (quandoF != null)
		{
			if (agendaContato.getProximoContato().before(quandoF))
				agendaContato.setProximoContato(quandoF);
		}
		
		agenda.setQuando(quandoF);
		agenda.setCreatedby(usuario.getId());
	    agenda.setUpdatedby(usuario.getId());
	    agenda.setAmrAgendaAcaoId(agendaAcaoDao.carregaPorId(amrAgendaAcaoId));
	    
	    agendaContato.getAgendas().add(agenda);
		
	    try {
			 agendaContatoDao.atualiza(agendaContato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return agenda;
	}
	
	@RequestMapping(value = "/deleteagenda", method = POST)
	public @ResponseBody Agenda deleteAgenda(String id) throws Exception
	{
		Agenda agenda = agendaDao.carregaPorId(id);
		try
		{
			agendaDao.exclui(agendaDao.carregaPorId(id));
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return  agenda;
	}
	
	@RequestMapping(value = "/editagenda", method = POST)
	public @ResponseBody Agenda editAgenda(@ModelAttribute("agenda") Agenda agenda, BindingResult result, String quando, String amrAgendaAcaoId) throws Exception
	{
		Agenda agendaTempt = agendaDao.carregaPorId(agenda.getId());
		
		AgendaAcao agendaAcao = agendaAcaoDao.carregaPorId(amrAgendaAcaoId);
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		
		Date quandoF = null;
		
		if (quando != null && quando != "")
		{
			try {
				quandoF = (Date) formatter.parse(quando);
			} catch (Exception e) {
				quandoF = null;
			}
		}
		
		agendaTempt.setAssunto(agenda.getAssunto());
		agendaTempt.setAnotacao(agenda.getAnotacao());
		agendaTempt.setQuando(quandoF);
		agendaTempt.setOnde(agenda.getOnde());
		agendaTempt.setSituacao(agenda.getSituacao()); 
		agendaTempt.setAmrAgendaAcaoId(agendaAcao);
	
		try {
			agendaDao.atualiza(agendaTempt);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
		return agendaTempt;
	}
}
