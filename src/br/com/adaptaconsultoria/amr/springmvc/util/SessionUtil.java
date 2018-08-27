package br.com.adaptaconsultoria.amr.springmvc.util;

import javax.servlet.http.HttpSession;

import br.com.adaptaconsultoria.amr.model.Usuario;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class SessionUtil {

	public static final String USUARIO = "usuario";
	public static final String LOGIN = "login";
	public static final String READ_ONLY = "readonly";
	public static final String PATROCINADOR_ID = "patrocinadorId";
	public static final String PATROCINADOR_NOME = "patrocinadorNome";
	public static final String PATROCINADOR_LOGIN = "patrocinadorLogin";
	public static final String PATROCINADOR_CODIGO = "patrocinadorCodigo";
	public static final String MENU = "menu";
	public static final String SUBMENU = "submenu";
	public static final String USUARIO_FILHO = "usuario_filho";
	public static final String ACEITE = "aceite";

	public static void setUsuarioId(HttpSession session, Usuario usuario) throws Exception {
		session.setAttribute(USUARIO, usuario.getId());
		session.setAttribute(LOGIN, usuario.getLogin());
		session.setAttribute(READ_ONLY, usuario.getReadOnly());
	}

	public static void setPatrocinador(HttpSession session, Usuario patrocinador) throws Exception {
		session.setAttribute(PATROCINADOR_ID, patrocinador.getId());
		session.setAttribute(PATROCINADOR_NOME, patrocinador.getNome());
		session.setAttribute(PATROCINADOR_LOGIN, patrocinador.getLogin());
		session.setAttribute(PATROCINADOR_CODIGO, patrocinador.getParceiroNegocios().getCodigo());
	}

	public static void clearPatrocinador(HttpSession session) {
		session.removeAttribute(PATROCINADOR_ID);
		session.removeAttribute(PATROCINADOR_NOME);
		session.removeAttribute(PATROCINADOR_LOGIN);
		session.removeAttribute(PATROCINADOR_CODIGO);
	}

	public static String getPatrocinadorId(HttpSession session) throws Exception {
		return (String) session.getAttribute(PATROCINADOR_ID);
	}

	public static String getUsuarioId(HttpSession session) throws Exception {
		return (String) session.getAttribute(USUARIO);
	}

	public static void setAttribute(HttpSession session, String nome, String valor) {
		session.setAttribute(nome, valor);
	}

	public static String getAttribute(HttpSession session, String nome) {
		return (String) session.getAttribute(nome);
	}

	public static void logout(HttpSession session) throws Exception {
		session.removeAttribute(USUARIO);
		session.invalidate();
	}

	public static void setMenuAtivo(HttpSession session, String id) {
		session.setAttribute(MENU, id);
	}

	public static String getMenuAtivo(HttpSession session) throws Exception {
		return (String) session.getAttribute(MENU);
	}

	public static void setSubMenuAtivo(HttpSession session, String id) {
		session.setAttribute(SUBMENU, id);
	}

	public static String getSubMenuAtivo(HttpSession session) throws Exception {
		return (String) session.getAttribute(SUBMENU);
	}

	public static String getReadOnly(HttpSession session) {
		return (String) session.getAttribute(READ_ONLY);
	}

	public static void setUsuarioFilhoId(HttpSession session, String id) {
		session.setAttribute(USUARIO_FILHO, id);
	}

	public static String getUsuarioFilho(HttpSession session) {
		return (String) session.getAttribute(USUARIO_FILHO);
	}

	public static void setAceite(HttpSession session, boolean aceite) {
		session.setAttribute(ACEITE, aceite ? "Y" : "N");
	}

	public static String getAceite(HttpSession session) {
		return (String) session.getAttribute(ACEITE);
	}

}