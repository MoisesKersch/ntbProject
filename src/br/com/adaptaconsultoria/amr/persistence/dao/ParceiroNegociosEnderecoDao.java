package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.ParceiroNegociosEndereco;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ParceiroNegociosEnderecoDao extends Dao<ParceiroNegociosEndereco> {

	public List<ParceiroNegociosEndereco> listaPorParceiroNegocios(String cBPartnerId) throws Exception;

}