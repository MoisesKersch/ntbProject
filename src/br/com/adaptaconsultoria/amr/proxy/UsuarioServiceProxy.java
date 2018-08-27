package br.com.adaptaconsultoria.amr.proxy;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.tools.ant.filters.StringInputStream;

import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class UsuarioServiceProxy {

	private static String USER = AMRProperties.getInstance().getPropriedade("openbravo.service.user");
	private static String PASSWORD = AMRProperties.getInstance().getPropriedade("openbravo.service.password");
	private static String URI_LOGIN = AMRProperties.getInstance().getPropriedade("login.service.uri");
	private static String URI_ALTERASENHA = AMRProperties.getInstance().getPropriedade("alterasenha.service.uri");

	public String doPostLogin(String envArquivo) throws Exception {
		HttpClient httpClient = new HttpClient();

		// httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, Criptografia.decrypt(PASSWORD)));
		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWORD));
		httpClient.getParams().setAuthenticationPreemptive(true);

		PostMethod postMethod = new PostMethod(URI_LOGIN);
		postMethod.setDoAuthentication(true);

		InputStreamRequestEntity inp = new InputStreamRequestEntity(new StringInputStream(envArquivo, "UTF-8"));
		postMethod.setRequestEntity(inp);

		httpClient.executeMethod(postMethod);

		InputStream inputStream = postMethod.getResponseBodyAsStream();
		String texto = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

		if (texto.startsWith("ERRO"))
			throw new Exception(texto);

		return texto;
	}

	public String doPostAlteraSenha(String envArquivo) throws Exception {
		HttpClient httpClient = new HttpClient();

		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USER, PASSWORD));
		httpClient.getParams().setAuthenticationPreemptive(true);

		PostMethod postMethod = new PostMethod(URI_ALTERASENHA);
		postMethod.setDoAuthentication(true);

		InputStreamRequestEntity inp = new InputStreamRequestEntity(new StringInputStream(envArquivo));
		postMethod.setRequestEntity(inp);

		httpClient.executeMethod(postMethod);

		InputStream inputStream = postMethod.getResponseBodyAsStream();
		String texto = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

		if (texto.startsWith("ERRO"))
			throw new Exception(texto);

		return texto;
	}

}