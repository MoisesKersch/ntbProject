package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Ativo;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface AtivoDao extends Dao<Ativo> {

	public List<Ativo> listaPorUsuario(String adUserId) throws Exception;

	public String pago(String adUserId) throws Exception;

}