package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.adaptaconsultoria.amr.model.ExtratoNovo;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ExtratoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
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
public class SaldoController implements Serializable 
{
	private ExtratoDao extratoDao = DaoFactory.getInstance().getExtratoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();

	@RequestMapping(value = "/extratoperiodo", method = GET)
	public String extratoPorPeriodo(HttpSession session) throws ParseException 
	{
		SessionUtil.setSubMenuAtivo(session, "extratoperiodo");
		return "extratoperiodo";
	}
	
	@RequestMapping(value = "/getsaldoinicial", method = GET)
	public @ResponseBody BigDecimal getSaldoInicial(HttpSession session, String dataInicial) throws Exception
	{
		Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		return extratoDao.getCreditoInicial(usuario.getParceiroNegocios().getId(), dataInicial);
		
	}
	
	@RequestMapping(value = "/getextrato", method = GET)
	public @ResponseBody List<ExtratoNovo> extratoGeral(HttpSession session, String dataInicial, String dataFinal) throws Exception
	{	
		Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
		return extratoDao.getExtrato(usuario.getParceiroNegocios().getId(), dataInicial, dataFinal);
		
		
	}
}




