package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Arquivo;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ArquivoDao extends Dao<Arquivo> {

	public List<Arquivo> listaPorParceiroNegocios(String cBPartnerId) throws Exception;

	public int getProximoNumeroDaSequencia(String cBPartnerId) throws Exception;

	public String getDataType(String extensao) throws Exception;

}