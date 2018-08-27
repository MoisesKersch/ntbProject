package br.com.adaptaconsultoria.amr.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.pagseguro.objectxml.IniciarSessao;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.ParametroDao;
import br.com.adaptaconsultoria.amr.util.HttpRequest;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class CheckoutPagSeguroService {

	private ParametroDao parametroDao = DaoFactory.getInstance().getParametroDao();

	/**
	 * 
	 * Método utilizado para abrir uma sessão do PagSeguro
	 * Retorna o ID da Sessão
	 * 
	 * @param adClientId
	 * @return idSessao
	 * @throws Exception
	 */
	public String iniciarSessaoPagamento(String adClientId) throws Exception {
		String url = "", email = "", token = "";

		try {
			url = parametroDao.carregaUrlPagSeguro(adClientId);
			if (url == null || url.isEmpty()) {
				throw new Exception("Parâmetro 'urlpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'urlpagseguro' não configurado!");
		}

		try {
			email = parametroDao.carregaEmailPagSeguro(adClientId);
			if (email == null || email.isEmpty()) {
				throw new Exception("Parâmetro 'emailpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'emailpagseguro' não configurado!");
		}

		try {
			token = parametroDao.carregaTokenPagSeguro(adClientId);
			if (token == null || token.isEmpty()) {
				throw new Exception("Parâmetro 'tokenpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'tokenpagseguro' não configurado!");
		}

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("token", token));

		url += "sessions/";
		String retorno = HttpRequest.post(url, nameValuePairs);
		System.out.println(retorno);

		if (retorno.substring(0, 5).equalsIgnoreCase("ERROR")) {
			throw new Exception(url + " : " + retorno);
		}

		StringReader unmarshal = new StringReader(retorno);
		IniciarSessao sessao = JAXB.unmarshal(unmarshal, IniciarSessao.class);

		return sessao.getId();
	}

	/**
	 * 
	 * Método utilizado para efetuar o pagamento com PagSeguro
	 * 
	 * @param adClientId, nameValuePairs (Lista de Parametros)
	 * @return xml correspondente a classe Transação
	 * @throws Exception
	 */
	public String efetuarPagamento(String adClientId, List<NameValuePair> nameValuePairs) throws Exception {
		String url = "", email = "", token = "";

		try {
			url = parametroDao.carregaUrlPagSeguro(adClientId);
			if (url == null || url.isEmpty()) {
				throw new Exception("Parâmetro 'urlpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'urlpagseguro' não configurado!");
		}

		try {
			email = parametroDao.carregaEmailPagSeguro(adClientId);
			if (email == null || email.isEmpty()) {
				throw new Exception("Parâmetro 'emailpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'emailpagseguro' não configurado!");
		}

		try {
			token = parametroDao.carregaTokenPagSeguro(adClientId);
			if (token == null || token.isEmpty()) {
				throw new Exception("Parâmetro 'tokenpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'tokenpagseguro' não configurado!");
		}

		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("token", token));
		nameValuePairs.add(new BasicNameValuePair("receiverEmail", email));

		url += "transactions/";
		String retorno = HttpRequest.post(url, nameValuePairs);
		System.out.println(retorno);

		if (retorno.substring(0, 5).equalsIgnoreCase("ERROR")) {
			throw new Exception(url + " : " + retorno);
		}

		return retorno;
	}

	/**
	 * 
	 * Método utilizado para efetuar a consulta da transação com PagSeguro
	 * 
	 * @param adClientId, notificationCode
	 * @return xml correspondente a classe Transação
	 * @throws Exception
	 */
	public String consultarNotificacao(String adClientId, String notificationCode) throws Exception {
		String url = "", email = "", token = "";

		try {
			url = parametroDao.carregaUrlPagSeguro(adClientId);
			if (url == null || url.isEmpty()) {
				throw new Exception("Parâmetro 'urlpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'urlpagseguro' não configurado!");
		}

		try {
			email = parametroDao.carregaEmailPagSeguro(adClientId);
			if (email == null || email.isEmpty()) {
				throw new Exception("Parâmetro 'emailpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'emailpagseguro' não configurado!");
		}

		try {
			token = parametroDao.carregaTokenPagSeguro(adClientId);
			if (token == null || token.isEmpty()) {
				throw new Exception("Parâmetro 'tokenpagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'tokenpagseguro' não configurado!");
		}

		url = url.replace("/v2", "/v3");
		url += "transactions/notifications/" + notificationCode + "?email=" + email + "&token=" + token;
		String retorno = HttpRequest.get(url);
		System.out.println(retorno);

		if (retorno.substring(0, 5).equalsIgnoreCase("ERROR")) {
			throw new Exception(url + " : " + retorno);
		}

		return retorno;
	}

	/**
	 * 
	 * Método utilizado para buscar URL da lib JS PagSeguro
	 * Retorna a URL
	 * 
	 * @param adClientId
	 * @return libJsPS
	 * @throws Exception
	 * 
	 */
	public String getLibJS(String adClientId) throws Exception {
		String libJsPS = "";
		try {
			libJsPS = parametroDao.carregaLibJSPagSeguro(adClientId);
			if (libJsPS == null || libJsPS.isEmpty()) {
				throw new Exception("Parâmetro 'libjspagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'libjspagseguro' não configurado!");
		}

		return libJsPS;
	}

	/**
	 * 
	 * Método utilizado para buscar URL das Imagens PagSeguro
	 * Retorna a URL
	 * 
	 * @param adClientId
	 * @return urlImagePS
	 * @throws Exception
	 */
	public String getURLImage(String adClientId) throws Exception {
		String urlImagePS = "";
		try {
			urlImagePS = parametroDao.carregaURLImagemPagSeguro(adClientId);
			if (urlImagePS == null || urlImagePS.isEmpty()) {
				throw new Exception("Parâmetro 'urlimagempagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'urlimagempagseguro' não configurado!");
		}

		return urlImagePS;
	}

	/**
	 * 
	 * Método utilizado para buscar Parcela Mínima Sem Juros PagSeguro
	 * Retorna número de parcelas minimas
	 * 
	 * @param adClientId
	 * @return urlImagePS
	 * @throws Exception
	 */
	public String getParcelaJuros(String adClientId) throws Exception {
		String minParcPS = "";
		try {
			minParcPS = parametroDao.carregaParcelaJurosPagSeguro(adClientId);
			if (minParcPS == null || minParcPS.isEmpty()) {
				throw new Exception("Parâmetro 'parcelajurospagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'parcelajurospagseguro' não configurado!");
		}

		return minParcPS;
	}

	/**
	 * 
	 * Método utilizado para buscar URL de Notificação PagSeguro
	 * Retorna a URL
	 * 
	 * @param adClientId
	 * @return urlImagePS
	 * @throws Exception
	 */
	public String getURLNotificacao(String adClientId) throws Exception {
		String urlNotificacaoPS = "";
		try {
			 urlNotificacaoPS = parametroDao.carregaURLNotificacaoPagSeguro(adClientId);
			if ( urlNotificacaoPS == null ||  urlNotificacaoPS.isEmpty()) {
				throw new Exception("Parâmetro 'urlnotificacaopagseguro' sem valor!");
			}
		} catch (Exception e) {
			throw new Exception("Parâmetro 'urlnotificacaopagseguro' não configurado!");
		}

		return urlNotificacaoPS;
	}

}