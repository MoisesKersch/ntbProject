package br.com.adaptaconsultoria.amr.util.cors;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CorsAutenticar {

	  @RequestMapping(value = "/autenticar", method = RequestMethod.OPTIONS)
	  public void autenticarOptions(HttpServletResponse theHttpServletResponse) throws IOException {
		  theHttpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
		  theHttpServletResponse.addHeader("Access-Control-Max-Age", "60");
		  theHttpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		  theHttpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
	  }

}