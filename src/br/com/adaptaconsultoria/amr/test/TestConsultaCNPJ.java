package br.com.adaptaconsultoria.amr.test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestConsultaCNPJ {

	private static String URI = "http://www.xmls.com.br/cnpj/busca.php";

	private static String doGet(String cnpj) throws HttpException, IOException {

		HttpClient httpClient = new HttpClient();

		HttpHost proxy = new HttpHost("someproxy", 8080);

		GetMethod getMethod = new GetMethod(URI + "?" + "cnpj=" + cnpj + "&tipo=json");
		httpClient.executeMethod(getMethod);

		return getMethod.getResponseBodyAsString();
	}

	public static void main(String[] args) {
		try {
			System.out.println("iniciado");
			System.out.println(doGet("00000000000191"));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}