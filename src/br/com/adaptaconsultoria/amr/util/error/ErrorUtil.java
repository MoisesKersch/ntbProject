package br.com.adaptaconsultoria.amr.util.error;

/**
 * 
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 * 
 */
public class ErrorUtil {

	public static String getLastMessage(Throwable e) {
		if (e.getCause() == null) {
			try {
				return e.getMessage().replaceAll("\"", "").replaceAll("\n", "");
			} catch (Exception ex) {
				return e.getMessage();
			}
		}

		return getLastMessage(e.getCause());
	}

	public static String escreveMessageError(Exception e) {
		String html = "<div class=\"div-erro\">";
		html += "<h1>";
		html += "ERRO";
		html += "</h1>";
		html += e.getMessage();
		html += "</div>";
		return html;
	}

}