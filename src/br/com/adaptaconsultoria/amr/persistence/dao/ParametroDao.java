package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.Parametro;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ParametroDao extends Dao<Parametro> {

	public Parametro carregaPorClientAndNome(String adClientId, String nome) throws Exception;

	public String carregaUrlPagSeguro(String adClientId) throws Exception;

	public String carregaEmailPagSeguro(String adClientId) throws Exception;

	public String carregaTokenPagSeguro(String adClientId) throws Exception;

	public String carregaLibJSPagSeguro(String adClientId) throws Exception;

	public String carregaURLImagemPagSeguro(String adClientId) throws Exception;

	public String carregaParcelaJurosPagSeguro(String adClientId) throws Exception;

	public String carregaURLNotificacaoPagSeguro(String adClientId) throws Exception;

}