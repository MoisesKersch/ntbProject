package br.com.adaptaconsultoria.amr.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@SuppressWarnings("serial")
public class EMail implements Serializable {

	private ServidorEMail servidor = new ServidorEMail();
	private List<String> destinatario = new ArrayList<String>();
	private String assunto = null;
	private List<File> anexos = new ArrayList<File>();
	private String mensagem = null;
	private int timeout = 5000;
	private boolean html = true;

	public EMail() {
		super();
	}

	public ServidorEMail getServidor() {
		return servidor;
	}

	public void setServidor(ServidorEMail servidor) {
		this.servidor = servidor;
	}

	public List<String> getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(List<String> destinatario) {
		this.destinatario = destinatario;
	}

	public void addDestinatario(String destinatario) {
		destinatario = destinatario.trim();
		if (destinatario != null && !destinatario.isEmpty())
			getDestinatario().add(destinatario);
	}

	public void addDestinatario(String... destinatario) {
		for (String para : destinatario)
			addDestinatario(para);
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<File> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<File> anexos) {
		this.anexos = anexos;
	}

	public void addAnexo(File arquivo) {
		if (arquivo != null)
			this.getAnexos().add(arquivo);
	}

	public void addAnexo(File... arquivo) {
		for (File file : arquivo) {
			addAnexo(file);
		}
	}

	public void addAnexo(String... arquivo) {
		for (String file : arquivo) {
			addAnexo(new File(file));
		}
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public void envia() throws Exception {

		if (servidor == null)
			throw new Exception("Nenhuma servidor de email");

		String smtpHostName = servidor.getHost();
		int smtpHostPort = servidor.getPorta();
		String smtpAuthUser = servidor.getUsuario();
		String smtpAuthPwd = servidor.getSenha();
		boolean ssl = servidor.isSsl();
		String sMailFrom = servidor.getEmail();
		boolean starttls = servidor.isStarttls();
		boolean auth = servidor.isAuth();

		Properties props = new Properties();

		if (ssl) {

			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.host", smtpHostName);
			props.put("mail.smtps.auth", auth ? "true" : "false");
			props.put("mail.smtps.starttls.enable", starttls ? "true" : "false");
			props.put("mail.smtps.ssl.enable", "true");
			props.put("mail.smtps.timeout", timeout);
			props.put("mail.smtps.connectiontimeout", timeout);
			props.put("mail.smtps.user", smtpAuthUser);
			props.put("mail.smtps.port", smtpHostPort);
			props.put("mail.smtps.socketFactory.port", smtpHostPort);
			props.put("mail.smtps.socketFactory.fallback", "false");

		}

		else {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", smtpHostName);
			props.put("mail.smtp.auth", auth ? "true" : "false");
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.timeout", timeout);
			props.put("mail.smtp.connectiontimeout", timeout);
			props.put("mail.smtp.user", smtpAuthUser);
			props.put("mail.smtp.port", smtpHostPort);
			props.put("mail.smtp.socketFactory.port", smtpHostPort);
			props.put("mail.smtp.socketFactory.fallback", "false");

		}

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);

		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(sMailFrom));
		message.setSubject(assunto);

		message.setHeader("Content-Type", "text/plain; charset=UTF-8; format=flowed");
		message.setHeader("Content-Transfer-Encoding", "8bit");
		message.setHeader("X-Accept-Language", "pt-br, pt");

		MimeBodyPart parteMensagem = new MimeBodyPart();
		if (this.html)
			parteMensagem.setDataHandler(new DataHandler(new ByteArrayDataSource(mensagem, "text/html")));
		else
			parteMensagem.setText(mensagem);

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(parteMensagem);

		if (anexos != null && !anexos.isEmpty()) {
			for (File anexo : anexos) {
				MimeBodyPart parteAnexos = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(anexo);
				parteAnexos.setDataHandler(new DataHandler(fds));
				parteAnexos.setFileName(fds.getName());
				mp.addBodyPart(parteAnexos);
			}
		}
		message.setContent(mp);

		for (String to : destinatario) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		}

		transport.connect(smtpHostName, smtpHostPort, smtpAuthUser, smtpAuthPwd);
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

}