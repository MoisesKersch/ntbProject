package br.com.adaptaconsultoria.amr.springmvc.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import br.com.adaptaconsultoria.amr.model.Arquivo;
import br.com.adaptaconsultoria.amr.model.Cadastro;
import br.com.adaptaconsultoria.amr.model.CadastroPendente;
import br.com.adaptaconsultoria.amr.model.Chaveiro;
import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.model.Usuario;
import br.com.adaptaconsultoria.amr.model.UsuarioRead;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ArquivoDao;
import br.com.adaptaconsultoria.amr.persistence.dao.CadastroPendenteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeBinariaDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioDao;
import br.com.adaptaconsultoria.amr.persistence.dao.UsuarioReadDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.service.DocumentoService;
import br.com.adaptaconsultoria.amr.service.UsuarioService;
import br.com.adaptaconsultoria.amr.springmvc.propertyeditor.UsuarioPropertyEditor;
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
public class UsuarioController implements Serializable {

	private UsuarioDao usuarioDao = DaoFactory.getInstance().getUsuarioDao();
	private ArquivoDao arquivoDao = DaoFactory.getInstance().getArquivoDao();
	private CadastroPendenteDao cadastroPendenteDao = DaoFactory.getInstance().getCadastroPendenteDao();
	private RedeBinariaDao redeBinariaDao = DaoFactory.getInstance().getRedeBinariaDao();
	private ParceiroNegociosDao parceiroNegociosDao = DaoFactory.getInstance().getParceiroNegociosDao();
	private UsuarioService usuarioService = new UsuarioService();
	private DocumentoService documentoService = new DocumentoService();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Usuario.class, new UsuarioPropertyEditor());
	}

	@RequestMapping(value = "/trocasenha", method = GET)
	public ModelAndView trocaSenha(HttpSession session) {
		SessionUtil.setSubMenuAtivo(session, "trocasenha");
		ModelAndView model = new ModelAndView("trocasenha", "chaveiro", new Chaveiro());
		return model;
	}

	@RequestMapping(value = "/trocasenha", method = POST)
	public String trocaSenha(@Valid final Chaveiro chaveiro, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {

		try {

			if (result.hasErrors()) {
				return "trocasenha";
			}

			if (!chaveiro.novasSenhasIguais())
				throw new Exception("Novas senhas não conferem!");

			usuarioService.alteraSenha(SessionUtil.getUsuarioId(session), chaveiro.getSenhaAtual(), chaveiro.getSenhaNova());

			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Sua senha foi alterada com sucesso");

			return "redirect:trocasenha";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:trocasenha";
		}
	}

	@RequestMapping(value = "/cadastrospendentes", method = GET)
	public ModelAndView cadastrosPendentes(HttpSession session) {
		SessionUtil.setMenuAtivo(session, "cadastrospendentes");
		SessionUtil.setSubMenuAtivo(session, "cadastrospendentes");
		List<CadastroPendente> lista = new ArrayList<CadastroPendente>();
		try {
			lista = cadastroPendenteDao.carregaPorUsuario(SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("cadastrospendentes", "lista", lista);
		return model;
	}

	@RequestMapping(value = "/meucadastro", method = GET)
	public ModelAndView meuCadastro(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setSubMenuAtivo(session, "meucadastro");
		Cadastro cadastro = null;
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			cadastro = usuarioService.criaCadastroDeParceiroDeNegocios(usuario.getParceiroNegocios().getId(), true, usuario.getId());
		} catch (Exception e) {
			cadastro = new Cadastro();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
		}
		return new ModelAndView("meucadastro", "cadastro", cadastro);
	}

	@RequestMapping(value = "/meucadastro", method = POST)
	public String editaMeuCadastro(@Valid final Cadastro cadastro, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, HttpServletRequest request) {
		try {

			cadastro.setOperadoraTelefone1("CLARO");

			if (result.hasErrors()) {
				return "meucadastro";
			}

			usuarioService.salvaMeuCadastro(cadastro);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Dados salvos com sucesso!");

			return "redirect:meucadastro";
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:meucadastro";
		}
	}

	@RequestMapping(value = "/cadastrospendentesedita/{id}", method = GET)
	public ModelAndView editaCadastroPendente(@PathVariable String id, RedirectAttributes redirectAttributes, HttpSession session) {
		Cadastro cadastro = null;
		try {
			cadastro = usuarioService.criaCadastroDeParceiroDeNegocios(id, false, SessionUtil.getUsuarioId(session));
		} catch (Exception e) {
			cadastro = new Cadastro();
			MessageUtil.erro(redirectAttributes, "ERRO", e);
		}
		return new ModelAndView("cadastrospendentesedita", "cadastro", cadastro);
	}

	@RequestMapping(value = "/cadastrospendentesedita", method = POST)
	public String editaCadastroPendente(@Valid final Cadastro cadastro, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {
		try {

			if (result.hasErrors()) {
				return "cadastrospendentesedita";
			}

			usuarioService.salvaCadastroPendente(cadastro);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Dados salvos com sucesso!");

			return "redirect:cadastrospendentesedita/" + cadastro.getcBPartnerId();
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:cadastrospendentesedita/" + cadastro.getcBPartnerId();
		}
	}

	@RequestMapping(value = "/cadastrospendentespaga", method = POST)
	public String salvaCadastroPendenteEPaga(@Valid final Cadastro cadastro, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
			HttpServletRequest request) {
		try {

			if (result.hasErrors()) {
				return "cadastrospendentesedita";
			}

			usuarioService.salvaCadastroPendente(cadastro);
			MessageUtil.sucesso(redirectAttributes, "Parabéns!", "Dados salvos com sucesso!");

			try {
				usuarioService.pagaCadastroPendente(SessionUtil.getUsuarioId(session), cadastro);
			} catch (Exception e) {
				MessageUtil.erro(redirectAttributes, "ERRO ao pagar cadastro",
						"Registro foi salvo com sucesso, porém não foi possível pagar o titulo.<br><br>Motivo: " + e.getMessage());
				return "redirect:cadastrospendentesedita/" + cadastro.getcBPartnerId();
			}

			return "redirect:cadastrospendentesedita/" + cadastro.getcBPartnerId();
		} catch (Exception e) {
			MessageUtil.erro(redirectAttributes, "ERRO", e);
			return "redirect:cadastrospendentesedita/" + cadastro.getcBPartnerId();
		}
	}

	@RequestMapping(value = "/removercadastropendente", method = GET)
	public String removerCadastroPendente(@RequestParam("direto") String direto) {
		try {
			usuarioService.removerCadastroPendente(direto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:cadastrospendentes";
	}

	@RequestMapping("validapatrocinador")
	public void validaPatrocinador(String login, HttpServletResponse response) throws Exception {
		ParceiroNegocios parceiro = parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(login);
		
		if (parceiro != null)
			response.getWriter().write(parceiro.getNome());
		else
			response.getWriter().write("");
		response.setStatus(200);
	}
	
	@RequestMapping("validapatrocinadorcustom")
	public void validaPatrocinadorCustom(String login, HttpServletResponse response) throws Exception {
		ParceiroNegocios parceiro = parceiroNegociosDao.carregaPorCodigoDeParceiroDeNegocios(login);
		
		if (parceiro != null)
		{
			response.getWriter().write("true");
		}
		else
			response.getWriter().write("false");
		response.setStatus(200);
	}
	
	@RequestMapping("validalogin")
	public void validaLogin(String login, HttpServletResponse response) throws Exception {
		Usuario usuario = usuarioDao.carregaPorLogin(login, AMRProperties.getInstance().getPropriedade("adclientid"));
		if (usuario != null) {
			
			response.getWriter().write("Já existe um usuário com esse login no sistema");
		}
		else
			response.getWriter().write("OK");
		response.setStatus(200);
	}

	@RequestMapping(value = "/listausuariospelobase", method = RequestMethod.GET)
	public @ResponseBody List<UsuarioRead> listaUsuarios(String id, HttpSession session, UsuarioReadDao usuarioReadDao) {
		try {
			List<UsuarioRead> usuarios = usuarioReadDao.listaPorUsuarioBase(id);
			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<UsuarioRead>();
	}

	@RequestMapping(value = "/usuariofilho", method = RequestMethod.POST)
	public void setUsuarioFilho(HttpSession session, String id) {
		SessionUtil.setUsuarioFilhoId(session, id);
	}

	@RequestMapping(value = "/meusdocumentos", method = GET)
	public ModelAndView meusDocumentos(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setSubMenuAtivo(session, "meusdocumentos");
		ModelAndView model = new ModelAndView("meusdocumentos");
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			List<Arquivo> arquivos = arquivoDao.listaPorParceiroNegocios(usuario.getParceiroNegocios().getId());
			model.addObject("arquivos", arquivos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/configbackoffice", method = GET)
	public ModelAndView configuracoesDoSistema(HttpSession session, RedirectAttributes redirectAttributes) {
		SessionUtil.setSubMenuAtivo(session, "configbackoffice");
		ModelAndView model = new ModelAndView("configbackoffice");
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			model.addObject("usuario", usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/salvaconfigbackoffice", method = GET)
	public ModelAndView salvaConfigBackoffice(String skin, HttpSession session, RedirectAttributes redirectAttributes) {
		try {
			Usuario usuario = usuarioDao.carregaPorId(SessionUtil.getUsuarioId(session));
			if (skin != null && !skin.isEmpty()) {
				usuario.setSkin(skin);
				usuarioDao.atualiza(usuario);
				SessionUtil.setAttribute(session, "skinUser", skin);
			}
		} catch (Exception e) {
			ModelAndView modal = meusDocumentos(session, redirectAttributes);
			modal.addObject("messageType", "0");
			modal.addObject("messageTitle", "ERRO");
			modal.addObject("messageDetail", e.getMessage());
			return modal;
		}
		return configuracoesDoSistema(session, redirectAttributes);
	}

	@RequestMapping(value = "/removemeudocumento/{id}", method = GET)
	public ModelAndView pagamentosPendentes(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
		Arquivo arquivo;
		try {
			arquivo = arquivoDao.carregaPorId(id);
			arquivoDao.exclui(arquivo);
		} catch (Exception e) {
			ModelAndView modal = meusDocumentos(session, redirectAttributes);
			modal.addObject("messageType", "0");
			modal.addObject("messageTitle", "ERRO");
			modal.addObject("messageDetail", e.getMessage());
			return modal;
		}
		return meusDocumentos(session, redirectAttributes);
	}

	@RequestMapping(value = "/uploaddocumento", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpSession session) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				File dir = new File(AMRProperties.getInstance().getPropriedade("diretorio.temp"));
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				documentoService.adicionaDocumentoParaOUsuario(SessionUtil.getUsuarioId(session), serverFile.getAbsolutePath());

				return "";
			} catch (Exception e) {
				return "ERRO: " + e.getMessage();
			}
		} else {
			return "ERRO: Selecione um arquivo";
		}
	}

	@RequestMapping("carregausuariocodigo")
	public void carregaUsuario(String codigo, HttpServletResponse response) throws Exception {
		Usuario usuario = usuarioDao.carregaPorCodigoDoParceiroDeNegocios(codigo);
		if (usuario != null)
		{
			response.getWriter().write(usuario.getParceiroNegocios().getNome());
			response.getWriter().write("-"+usuario.getLogin());
		}
		else
			response.getWriter().write("");
		response.setStatus(200);
	}
}