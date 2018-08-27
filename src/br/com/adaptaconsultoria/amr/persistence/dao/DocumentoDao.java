package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Documento;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface DocumentoDao extends Dao<Documento> {

	public List<Documento> carregaPorUsuario(String adUserId) throws Exception;

}