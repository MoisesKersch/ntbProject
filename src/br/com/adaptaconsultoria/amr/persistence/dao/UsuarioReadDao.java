package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.UsuarioRead;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface UsuarioReadDao extends Dao<UsuarioRead> {

	public List<UsuarioRead> listaPorUsuarioBase(String adUserId) throws Exception;

}