package br.com.adaptaconsultoria.amr.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HttpRequest {

	private HttpRequest() {
	}

	public static String post(String url, List<NameValuePair> nameValuePairs) throws Exception {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpResponse response = client.execute(post);

		System.out.println("**POST** request Url: " + post.getURI());
		// System.out.println("Parameters : " + nameValuePairs);
		System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
		System.out.println("Content:-\n");
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "", retorno = "";
		if (response.getStatusLine().getStatusCode() == 200) {
			while ((line = rd.readLine()) != null) {
				retorno += line;
				System.out.println(line);
			}
		} else {
			retorno = "Error: " + response.getStatusLine().getStatusCode();
		}
		return retorno;
	}

	public static String get(String url) throws Exception {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);

		System.out.println("**GET** request Url: " + request.getURI());
		System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
		System.out.println("Content:-\n");
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "", retorno = "";
		if (response.getStatusLine().getStatusCode() == 200) {
			while ((line = rd.readLine()) != null) {
				retorno += line;
				System.out.println(line);
			}
		} else {
			retorno = "Error: " + response.getStatusLine().getStatusCode();
		}

		return retorno;
	}
}