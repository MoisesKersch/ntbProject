package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.sun.mail.iap.Response;

import br.com.adaptaconsultoria.amr.model.AgendaContato;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.Reuniao;
import br.com.adaptaconsultoria.amr.model.Reunioes;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.FranqueadoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ReuniaoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ReunioesDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.DateUtil;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */

@Controller
@Scope("request")
@SuppressWarnings("serial")
public class MinhasReunioesController implements Serializable
{
	private ReunioesDao reunioesDao = DaoFactory.getInstance().getReunioesDao();
	private ReuniaoDao reuniaoDao = DaoFactory.getInstance().getReuniaoDao();
	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();
	private FranqueadoDao franqueadoDao = DaoFactory.getInstance().getFranqueadoDao();

	@RequestMapping(value = "/minhasreunioes", method = GET)
	public ModelAndView minhasReunioes(HttpSession session)
	{
		SessionUtil.setMenuAtivo(session, "minhasreunioes");
		SessionUtil.setSubMenuAtivo(session, "minhasreunioes");
		
		ModelAndView model = new ModelAndView("minhasreunioes");
		
		return model;
	}
	
	@RequestMapping(value = "/minhasreunioeslista", method = POST)
	public @ResponseBody List<Reunioes> getMinhasReunioes(HttpSession session)
	{
		Usuario usuario;
		
		try
		{
			usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			return reunioesDao.listaReunioes(SessionUtil.getUsuarioId(session));
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/registrarnovareuniao", method = POST)
	public @ResponseBody Reuniao registrarNovaReuniao(Reuniao reuniao, HttpSession session, String data, String hora, String responsavelId, String anfitriaoId)
	{
		try
		{
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			
			
			if (anfitriaoId == "")
			{
		
				reuniao.setResponsavel( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(responsavelId) );
				reuniao.setAnfitriao( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(franqueadoDao.carregaPorUsuario(usuario.getId()).getCodigo()));
			}
			else
			{
				reuniao.setAnfitriao( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(anfitriaoId) );
				reuniao.setResponsavel( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(franqueadoDao.carregaPorUsuario(usuario.getId()).getCodigo()));
			}
			
			String dataHora = data +" "+hora;
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Date parsedDate = dateFormat.parse(dataHora);
			
	
			reuniao.setId(reuniaoDao.getUUID());
			reuniao.setAdClientId(usuario.getAdClientId());
			reuniao.setAdOrgId(usuario.getAdOrgId());
			reuniao.setCreatedBy(usuario.getId());
			reuniao.setUpdatedBy(usuario.getId());
			reuniao.setData(parsedDate);
			reuniaoDao.salva(reuniao);
	
			return reuniao;
		} catch (Exception e)
		{
			return null;
		}
	}
	
	public ParceiroNegocios getParceiroNegocios(String id) throws Exception
	{
		return parceiroNegociosDao.carregaPorId(id);
	}
	
	@RequestMapping(value = "/editarreuniao", method = POST)
	public @ResponseBody Reuniao editarNovaReuniao(Reuniao reuniao, String reuniaoId, String data, String hora,  HttpSession session, String anfitriaoId, String responsavelId)
	{
		try 
		{
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			Reuniao re = reuniaoDao.carregaPorId(reuniaoId);
			
			if (anfitriaoId == "")
			{
		
				re.setResponsavel( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(responsavelId) );
				re.setAnfitriao( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(franqueadoDao.carregaPorUsuario(usuario.getId()).getCodigo()));
			}
			else
			{
				re.setAnfitriao( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(anfitriaoId) );
				re.setResponsavel( parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(franqueadoDao.carregaPorUsuario(usuario.getId()).getCodigo()));
			}
			
			String dataHora = data +" "+hora;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Date parsedDate = dateFormat.parse(dataHora);
			re.setData(parsedDate);
			re.setParticipantes(reuniao.getParticipantes());
			re.setObservacoes(reuniao.getObservacoes());
			
			reuniaoDao.atualiza(re);
			
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/removerreuniao", method = POST)
	public @ResponseBody Reuniao remove(String id)
	{
		try
		{
			Reuniao delete = reuniaoDao.carregaPorId(id);
			Reuniao deleteAux = delete;
			reuniaoDao.exclui(delete);	
			return deleteAux;
		} catch (Exception e)
		{
			return null;
		}
	}
}


