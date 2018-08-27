package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.adaptaconsultoria.amr.builder.object.EnderecoWS;
import br.com.adaptaconsultoria.amr.loader.CidadeLoader;
import br.com.adaptaconsultoria.amr.model.Aceite;
import br.com.adaptaconsultoria.amr.model.Cadastro;
import br.com.adaptaconsultoria.amr.model.Cidade;
import br.com.adaptaconsultoria.amr.model.Estado;
import br.com.adaptaconsultoria.amr.model.OrgInfo;
import br.com.adaptaconsultoria.amr.model.Pais;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.RetBuscaCEP;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AceiteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.OrgInfoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.proxy.BuscaCEPServiceProxy;
import br.com.adaptaconsultoria.amr.service.AdesaoService;
import br.com.adaptaconsultoria.amr.springmvc.util.MessageUtil;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;
import br.com.adaptaconsultoria.amr.util.HttpRequest;
import br.com.adaptaconsultoria.amr.util.error.CNP;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("session")
public class AdesaoController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private AceiteDao aceiteDao = DaoFactory.getInstance().getAceiteDao();
	private OrgInfoDao orgInfoDao = DaoFactory.getInstance().getOrgInfoDao();
	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();

	private Cadastro cadastro;

	@RequestMapping(value = "/adesao", method = GET)
	public ModelAndView novo(String id) {

		if (cadastro == null) {
			cadastro = new Cadastro();
		}

		if (id != null && !id.isEmpty()) {
			cadastro.setPatrocinador(id);
		}
		else cadastro.setPatrocinador(null);

		ModelAndView model = new ModelAndView("adesao", "cadastro", cadastro);
		return model;
	}

	@RequestMapping(value = "/novaadesao", method = GET)
	public ModelAndView novaAdesaoPatrocinada(String id) {

		cadastro = new Cadastro();

		Usuario user;
		ModelAndView model = null;
		try {
			user = usuarioDao.carregaPorCodigoDoParceiroDeNegocios(id);
			if (user != null) {
				cadastro.setPatrocinador(user.getParceiroNegocios().getCodigo());
				model = new ModelAndView("novaadesao", "cadastro", cadastro);
			}
		} catch (Exception e) {
			model = new ModelAndView("adesao", "cadastro", cadastro);
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/novaadesao_novo", method = POST)
	public String novaAdesaoPatrocinada(@Valid final Cadastro cadastro, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {

		try {

			cadastro.setOperadoraTelefone1("CLARO");

			if (result.hasErrors()) {
				return "novaadesao";
			}

			AdesaoService manager = new AdesaoService();
			manager.enviaAdesao(cadastro);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Adesão efetuada com sucesso");

			return "redirect:login";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			this.cadastro = cadastro;
			return "redirect:adesao";
		}
	}

	@RequestMapping(value = "/adesao_novo", method = POST)
	public String novo(@Valid final Cadastro cadastro, BindingResult result, RedirectAttributes redirectAttributes, 
			HttpSession session, HttpServletRequest request) {
		String concat = (cadastro.getPatrocinador() == null || cadastro.getPatrocinador().isEmpty() ? "" : "?id="+cadastro.getPatrocinador());
		try {

			cadastro.setOperadoraTelefone1("CLARO");

			if (result.hasErrors()) {
				MessageUtil.erro(redirectAttributes, "ERRO", "Campos obrigatório não preenchidos");
				return "redirect:adesao" + concat;
			}

			AdesaoService manager = new AdesaoService();
			manager.enviaAdesao(cadastro);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Adesão efetuada com sucesso");

			Usuario usuario = usuarioDao.carregaPorLogin(cadastro.getUsuario(),
					AMRProperties.getInstance().getPropriedade("adclientid"));
			if (usuario != null) {
				Aceite aceite = new Aceite();
				aceite.setId(aceiteDao.getUUID());
				aceite.setAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
				aceite.setAdOrgId(AMRProperties.getInstance().getPropriedade("adorgid"));
				aceite.setAdUserId(usuario.getId());
				aceite.setAceito("Y");
				aceite = aceiteDao.salva(aceite);
				SessionUtil.setAceite(session, true);
			}

			return "redirect:login";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			this.cadastro = cadastro;
			return "redirect:adesao" + concat;
		}
	}

	@RequestMapping(value = "/getindicador", method = RequestMethod.GET)
	public @ResponseBody String getIndicador(HttpSession session) throws Exception {
		OrgInfo orgInfo = orgInfoDao.carregaPorId(AMRProperties.getInstance().getPropriedade("adorgid"));
		try {
			return orgInfo.getParceiroNegocio().getCodigo();
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/lista_paises", method = RequestMethod.GET)
	public @ResponseBody List<Pais> listaPaises() {
		List<Pais> paises = new ArrayList<Pais>();
		paises.add(new Pais("139", "Brasil"));
		return paises;
	}

	@RequestMapping(value = "/lista_estados", method = RequestMethod.GET)
	public @ResponseBody List<Estado> listaEstados(@RequestParam("pais") String pais) {
		List<Estado> estados = new ArrayList<Estado>();

		if (pais.equals("139")) {
			estados.add(new Estado("397", "ACRE", "AC"));
			estados.add(new Estado("398", "ALAGOAS", "AL"));
			estados.add(new Estado("399", "AMAPÁ", "AP"));
			estados.add(new Estado("400", "AMAZONAS", "AM"));
			estados.add(new Estado("401", "BAHIA", "BA"));
			estados.add(new Estado("402", "CEARÁ", "CE"));
			estados.add(new Estado("403", "DISTRITO FEDERAL", "DF"));
			estados.add(new Estado("404", "GOIÁS", "GO"));
			estados.add(new Estado("405", "ESPIRITO SANTO", "ES"));
			estados.add(new Estado("406", "MARANHÃO", "MA"));
			estados.add(new Estado("408", "MATO GROSSO", "MT"));
			estados.add(new Estado("409", "MATO GROSSO DO SUL", "MS"));
			estados.add(new Estado("410", "MINAS GERAIS", "MG"));
			estados.add(new Estado("411", "PARÁ", "PA"));
			estados.add(new Estado("412", "PARAÍBA", "PB"));
			estados.add(new Estado("413", "PARANÁ", "PR"));
			estados.add(new Estado("414", "PERNAMBUCO", "PE"));
			estados.add(new Estado("415", "PIAUÍ", "PI"));
			estados.add(new Estado("416", "RIO DE JANEIRO", "RJ"));
			estados.add(new Estado("417", "RIO GRANDE DO NORTE", "RN"));
			estados.add(new Estado("418", "RIO GRANDE DO SUL", "RS"));
			estados.add(new Estado("419", "RONDÔNIA", "RO"));
			estados.add(new Estado("420", "RORÂIMA", "RR"));
			estados.add(new Estado("421", "SÃO PAULO", "SP"));
			estados.add(new Estado("422", "SANTA CATARINA", "SC"));
			estados.add(new Estado("423", "SERGIPE", "SE"));
			estados.add(new Estado("424", "TOCANTINS", "TO"));
		}

		return estados;
	}

	@RequestMapping(value = "/lista_cidades", method = RequestMethod.GET)
	public @ResponseBody List<Cidade> listaCidades(@RequestParam("estado") String estado,
			@RequestParam("completo") String completo, RedirectAttributes redirectAttributes) {
		List<Cidade> cidades = new ArrayList<Cidade>();
		try {
			if (completo.equalsIgnoreCase("N"))
				cidades = carregaCidadePorEstadoDoBrasil(estado);
			else {
				// BuscaCEPKingHostProxy proxy = new BuscaCEPKingHostProxy();
				// Cidade cidade = proxy.doGet(estado);

				BuscaCEPServiceProxy proxy = new BuscaCEPServiceProxy();

				RetBuscaCEP ret = proxy.doGet(estado);
				Cidade cidade = new Cidade();
				cidade.setNome(ret.getCidade());
				cidade.setCidade(ret.getCidade());
				cidade.setUf(ret.getUf());
				cidade.setBairro(ret.getBairro());
				cidade.setLogradouro(ret.getLogradouro());
				cidade.setComplemento(ret.getComplemento());

				Map<String, String> map = new HashMap<String, String>();
				map.put("AC", "397");
				map.put("AL", "398");
				map.put("AP", "399");
				map.put("AM", "400");
				map.put("BA", "401");
				map.put("CE", "402");
				map.put("DF", "403");
				map.put("GO", "404");
				map.put("ES", "405");
				map.put("MA", "406");
				map.put("MG", "410");
				map.put("MS", "409");
				map.put("MT", "408");
				map.put("PA", "411");
				map.put("PB", "412");
				map.put("PE", "414");
				map.put("PI", "415");
				map.put("PR", "413");
				map.put("RJ", "416");
				map.put("RN", "417");
				map.put("RO", "419");
				map.put("RR", "420");
				map.put("RS", "418");
				map.put("SC", "422");
				map.put("SE", "423");
				map.put("SP", "421");
				map.put("TO", "424");

				cidade.setUf(map.get(cidade.getUf()));
				List<Cidade> lista = carregaCidadePorEstadoDoBrasil(cidade.getUf());
				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).getNome().equalsIgnoreCase(cidade.getCidade())) {
						cidade.setCidade(lista.get(i).getId());
						break;
					}
				}
				cidades.add(cidade);
			}

		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
		}
		return cidades;
	}

	public List<Cidade> carregaCidadePorEstadoDoBrasil(String estadoId) throws Exception {
		return CidadeLoader.listaCidadesPorEstado(estadoId);
	}

	@RequestMapping(value = "/novocadastro", method = GET)
	public ModelAndView novoCadastro(HttpSession session) {

		SessionUtil.setMenuAtivo(session, "novocadastro");
		SessionUtil.setSubMenuAtivo(session, "novocadastro");

		if (cadastro == null) {
			cadastro = new Cadastro();
		}

		Usuario usuario;
		try {
			usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			cadastro.setPatrocinador(usuario.getParceiroNegocios().getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("novocadastro", "cadastro", cadastro);
		return model;
	}

	@RequestMapping(value = "/novocadastronovo", method = POST)
	public String novoCadastroNovo(@Valid final Cadastro cadastro, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request, Model model) {
		try {
			cadastro.setOperadoraTelefone1("CLARO");

			System.out.println(cadastro.isAutomatico());
			cadastro.setResidencial(true);
			cadastro.setCorrespondencia(true);
			cadastro.setCobranca(true);

			if (result.hasErrors()) {
				model.addAttribute("messageType", "0");
				model.addAttribute("messageTitle", "Erro");
				model.addAttribute("messageDetail", "Campos obrigatório não preenchidos");

				return "novocadastro";
			}

			AdesaoService manager = new AdesaoService();
			
			manager.enviaAdesao(cadastro);
			
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Novo cadastro criado com sucesso");

			if (cadastro.isAutomatico()) {
				manager.compraParaNovoCadastro(session, cadastro);
				this.cadastro = null;
				return "redirect:compras";
			}

			this.cadastro = null;
			return "redirect:novocadastro";
		} catch (Exception e) {
			model.addAttribute("messageType", "0");
			model.addAttribute("messageTitle", "Erro");
			model.addAttribute("messageDetail", e.getMessage());

			this.cadastro = cadastro;
			return "novocadastro";
		}
	}

	@RequestMapping(value = "/usuariovalido", method = RequestMethod.POST)
	public void carregaUsuario(String usuario, HttpServletResponse response) throws Exception 
	{
		Usuario usuario1 = usuarioDao.carregaPorLogin(usuario, AMRProperties.getInstance().getPropriedade("adclientid"));
		
		if (usuario1 != null)
		{
			response.getWriter().write("false");
		}
		else
			response.getWriter().write("true");
		
		response.setStatus(200);
	}
	
	@RequestMapping(value = "/cpfcnpjvalido", method = RequestMethod.POST)
	public void carregaCpfcnpjvalido(String cpfCnpj, String checkCpfCnpf, HttpServletResponse response) throws Exception 
	{
		boolean validoCpfCnpj = true;
		
		if (checkCpfCnpf.equals("CNPJ*"))
		{
			validoCpfCnpj = CNP.isValidCNPJ(cpfCnpj);
		}
		else
		{
			validoCpfCnpj = CNP.isValidCPF(cpfCnpj);
		}
		
		if (validoCpfCnpj)
		{
			ParceiroNegocios parceiroNegocios = parceiroNegociosDao.carregaPorTaxID(cpfCnpj);
			
			if (parceiroNegocios != null)
			{
				response.getWriter().write("false");
			}
			else
				response.getWriter().write("true");
			
		}else
		{
			response.getWriter().write("false");
		}
		response.setStatus(200);
	}
	

	@RequestMapping(value = "/buscarcep", method = RequestMethod.GET)
	public @ResponseBody EnderecoWS buscarCEP(@RequestParam("cep") String cep) {
		cep = cep.trim().replace(".", "").replace("-", "");
		if (cep.length() != 8) {
			return null;
		}
		for (String token : AMRProperties.getInstance().getPropriedade("kinghosttokenws").split(";")) {
			try {
				String retorno = HttpRequest.get("http://webservice.kinghost.net/web_cep.php?auth=" + token.trim() + "&formato=json&cep=" + cep);
				Gson gson = new Gson();
				EnderecoWS endereco = gson.fromJson(retorno, EnderecoWS.class);
				return endereco;
			} catch (Exception e) {
				continue;
			}
		}
		return null;
	}

}