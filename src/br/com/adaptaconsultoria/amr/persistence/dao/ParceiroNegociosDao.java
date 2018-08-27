package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ParceiroNegociosDao extends Dao<ParceiroNegocios> {

	public ParceiroNegocios carregaPorTaxID(String taxId) throws Exception;

	public ParceiroNegocios carregaPorCodigoDeParceiroDeNegocios(String codigo) throws Exception;
	
	public ParceiroNegocios carregaPorCodigoDeParceiroDeNegocios() throws Exception;

}