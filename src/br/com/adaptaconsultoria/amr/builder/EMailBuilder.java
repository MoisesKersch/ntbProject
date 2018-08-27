package br.com.adaptaconsultoria.amr.builder;

import br.com.adaptaconsultoria.amr.model.ServidorEMail;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class EMailBuilder {

	public static ServidorEMail criaServidorPorProperties() throws Exception {
		ServidorEMail conta = new ServidorEMail();
		conta.setEmail(AMRProperties.getInstance().getPropriedade("conta.email"));
		conta.setUsuario(AMRProperties.getInstance().getPropriedade("conta.usuario"));
		// conta.setSenha(Criptografia.decrypt(AMRProperties.getInstance().getPropriedade("conta.senha")));
		conta.setSenha(AMRProperties.getInstance().getPropriedade("conta.senha"));
		conta.setHost(AMRProperties.getInstance().getPropriedade("conta.host"));
		conta.setPorta(Integer.parseInt(AMRProperties.getInstance().getPropriedade("conta.porta")));
		conta.setSsl(AMRProperties.getInstance().getPropriedade("conta.ssl").equals("true"));
		conta.setStarttls(AMRProperties.getInstance().getPropriedade("conta.starttls").equals("true"));
		conta.setAuth(AMRProperties.getInstance().getPropriedade("conta.auth").equals("true"));
		return conta;
	}

	public static String criaMensagemEsqueciMinhaSenha(String login, String senha) throws Exception {
		String mensagem = "Foi solicitada uma redefinição de senha no sistema para sua conta.";
		mensagem += "<h2>Dados de acesso</h2>";
		mensagem += "Login: <b>" + login + "</b>";
		mensagem += "<br>";
		mensagem += "Senha: <b>" + senha + "</b>";
		return mensagem;
	}

	public static String criaMensagemEsqueciMinhaSenhaFinanceira(String senha) throws Exception {
		String mensagem = "Foi solicitada uma redefinição de senha financeira no sistema para sua conta.";
		mensagem += "<br>";
		mensagem += "Senha financeira: <b>" + senha + "</b>";
		return mensagem;
	}

	public static String criaMensagemEsqueciMinhaSenhaSolicitacao(String login, String senha) throws Exception {
		String link = AMRProperties.getInstance().getPropriedade("basecontext") + "/envianovasenha?id=" + senha;

		String mensagem = "Foi solicitada uma redefinição de senha no sistema para sua conta.";
		mensagem += "<br>Clique <a href=\"" + link + "\" target=\"_blank\">aqui</a> para confirmar a solicitação de uma nova senha.";
		mensagem += "<h2>Dados de acesso</h2>";
		mensagem += "Login: <b>" + login + "</b>";
		return mensagem;
	}

}