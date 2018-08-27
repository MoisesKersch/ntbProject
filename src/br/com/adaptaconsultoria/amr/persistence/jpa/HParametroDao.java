package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.Parametro;
import br.com.adaptaconsultoria.amr.persistence.dao.ParametroDao;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HParametroDao extends HDao<Parametro> implements ParametroDao {

	@Override
	public synchronized Parametro carregaPorClientAndNome(String adClientId, String nome) throws Exception {
		String query = "SELECT obj FROM Parametro obj WHERE obj.adClientId = :adClientId and obj.nome = :nome";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adClientId", adClientId);
		parametros.put("nome"      , nome      );

		return carrega(query, parametros);
	}

	@Override
	public String carregaUrlPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "urlpagseguro");
		return parametro.getValor();
	}

	@Override
	public String carregaEmailPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "emailpagseguro");
		return parametro.getValor();
	}

	@Override
	public String carregaTokenPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "tokenpagseguro");
		return parametro.getValor();
	}

	@Override
	public String carregaLibJSPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "libjspagseguro");
		return parametro.getValor();
	}

	@Override
	public String carregaURLImagemPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "urlimagempagseguro");
		return parametro.getValor();
	}

	@Override
	public String carregaParcelaJurosPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "parcelajurospagseguro");
		return parametro.getValor();
	}

	@Override
	public String carregaURLNotificacaoPagSeguro(String adClientId) throws Exception {
		Parametro parametro = carregaPorClientAndNome(adClientId, "urlnotificacaopagseguro");
		return parametro.getValor();
	}

}