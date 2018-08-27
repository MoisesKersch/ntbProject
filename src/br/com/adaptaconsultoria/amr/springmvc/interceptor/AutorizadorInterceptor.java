package br.com.adaptaconsultoria.amr.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.adaptaconsultoria.amr.model.Aceite;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.AceiteDao;
import br.com.adaptaconsultoria.amr.persistence.dao.ManutencaoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;
import br.com.adaptaconsultoria.amr.springmvc.util.SessionUtil;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Component
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	private ManutencaoDao manutencaoDao = DaoFactory.getInstance().getManutencaoDao();
	private AceiteDao aceiteDao = DaoFactory.getInstance().getAceiteDao();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {

		String uri = request.getRequestURI();
		try {
			System.out.println("URI: " + uri + "?" + request.getQueryString());

			// Removendo JSession

			if (uri.contains(";jsessionid")) {
				int x1 = uri.indexOf(";jsessionid");
				int x2 = uri.length();
				if (uri.contains("?"))
					x2 = uri.indexOf("?");
				uri = uri.replace(uri.substring(x1, x2), "");
			}

		} catch (Exception e) {
		}

		if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".ttf") || uri.endsWith(".woff")
				|| uri.endsWith(".woff2") || uri.endsWith(".pdf")) {
			return true;
		}

		if (uri.endsWith("envianovasenha")) {
			return true;
		}

		if (uri.endsWith("manutencao")) {
			try {
				if (manutencaoDao.temAtivas(AMRProperties.getInstance().getPropriedade("adclientid")))
					return true;
			} catch (Exception e) {
			}
		}
		try {
			String homologacao = AMRProperties.getInstance().getPropriedade("homologacao");
			if (homologacao == null || !homologacao.equalsIgnoreCase("true")) {
				if (manutencaoDao.temAtivas(AMRProperties.getInstance().getPropriedade("adclientid"))) {
					response.sendRedirect("manutencao");
					return false;
				}
			}
		} catch (Exception e) {
		}

		System.out.println("Procurando sessão ativa");
		String usuario = SessionUtil.getUsuarioId(request.getSession());
		if (usuario != null && !usuario.isEmpty()) {
			System.out.println("Usuario existente: " + usuario);

			if (uri.endsWith("logout")) {
				return true;
			}

			if (uri.endsWith("aceite")) {
				return true;
			}

			Aceite aceite = aceiteDao.carregaPorUsuario(SessionUtil.getUsuarioId(request.getSession()));
			if (aceite == null) {
				if (!uri.endsWith("termosuso"))
					response.sendRedirect("termosuso");
				return true;
			} else {
				return true;
			}
		} else
			System.out.println("Sessão perdida");

		if (uri.endsWith("login") || uri.endsWith("adesao") || uri.endsWith("novaadesao")) {
			return true;
		}

		if (uri.endsWith("validapatrocinador")) {
			return true;
		}

		if (uri.endsWith("patrocinador")) {
			return true;
		}

		if (uri.endsWith("adesao_novo")) {
			return true;
		}

		if (uri.endsWith("lista_paises")) {
			return true;
		}
		if (uri.endsWith("lista_estados")) {
			return true;
		}
		if (uri.endsWith("lista_cidades")) {
			return true;
		}
		if (uri.endsWith("lista_produtos")) {
			return true;
		}

		if (uri.endsWith("esqueciminhasenha")) {
			return true;
		}

		if (uri.endsWith("esqueciminhasenha")) {
			return true;
		}

		if (uri.contains("ktaut")) {
			return true;
		}

		if (uri.endsWith("cookiesdesabilitados")) {
			return true;
		}

		if (uri.endsWith("getindicador")) {
			return true;
		}

		if (uri.endsWith("notificacaoPagSeguro")) {
			return true;
		}

		if (uri.endsWith("autenticar")) {
			return true;
		}

		if (uri.endsWith("setcurso")) {
			return true;
		}

		if (uri.endsWith("getmatricula")) {
			return true;
		}

		if (uri.endsWith("buscarcep")) {
			return true;
		}
		
		if (uri.endsWith("cpfcnpjvalido")) {
			return true;
		}
		
		if (uri.endsWith("usuariovalido")) {
			return true;
		}

		response.sendRedirect("login");
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}