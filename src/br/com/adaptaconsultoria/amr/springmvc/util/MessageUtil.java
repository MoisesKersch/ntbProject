package br.com.adaptaconsultoria.amr.springmvc.util;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.adaptaconsultoria.amr.util.error.ErrorUtil;

public class MessageUtil {

	public static void erro(RedirectAttributes redirectAttributes, String title, Exception e) {
		erro(redirectAttributes, title, ErrorUtil.getLastMessage(e));
	}

	public static void erro(RedirectAttributes redirectAttributes, String title, String erro) {
		redirectAttributes.addFlashAttribute("messageType", "0");
		redirectAttributes.addFlashAttribute("messageTitle", title);
		redirectAttributes.addFlashAttribute("messageDetail", erro);
	}

	public static void sucesso(RedirectAttributes redirectAttributes, String title, String detalhes) {
		redirectAttributes.addFlashAttribute("messageType", "3");
		redirectAttributes.addFlashAttribute("messageTitle", title);
		redirectAttributes.addFlashAttribute("messageDetail", detalhes);
	}

	public static void alerta(RedirectAttributes redirectAttributes, String title, Exception e) {
		alerta(redirectAttributes, title, ErrorUtil.getLastMessage(e));
	}

	public static void alerta(RedirectAttributes redirectAttributes, String title, String detalhes) {
		redirectAttributes.addFlashAttribute("messageType", "1");
		redirectAttributes.addFlashAttribute("messageTitle", title);
		redirectAttributes.addFlashAttribute("messageDetail", detalhes);
	}

	public static void informacao(RedirectAttributes redirectAttributes, String title, String detalhes) {
		redirectAttributes.addFlashAttribute("messageType", "2");
		redirectAttributes.addFlashAttribute("messageTitle", title);
		redirectAttributes.addFlashAttribute("messageDetail", detalhes);
	}

}