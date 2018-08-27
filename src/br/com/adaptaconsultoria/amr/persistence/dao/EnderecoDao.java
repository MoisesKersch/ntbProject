package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Endereco;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface EnderecoDao extends Dao<Endereco> {

	public List<Endereco> listaPorParceiroNegocios(String cBPartnerId) throws Exception;

	public String carregaCEPOrigem(String adOrgId) throws Exception;

	public String carregaCEPDestino(String adUserId) throws Exception;

}