package br.com.adaptaconsultoria.amr.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class AMRProperties {

	public static final String nomeArquivo = "amr.properties";
	private static AMRProperties instance;
	private Properties properties;

	public static AMRProperties getInstance() {
		if (instance == null)
			instance = new AMRProperties();
		return instance;
	}

	private AMRProperties() {
		try {
			this.properties = new Properties();
			URI uri = AMRProperties.class.getResource(nomeArquivo).toURI();
			FileInputStream fis = new FileInputStream(new File(uri));
			this.properties.load(fis);
			fis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public String getPropriedade(String nome) {
		String propriedade = null;
		if (propriedade == null)
			propriedade = this.properties.getProperty(nome);

		if (propriedade == null || propriedade.isEmpty())
			return "";

		return propriedade;
	}
}