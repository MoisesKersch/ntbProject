package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.MeusPontos;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface MeusPontosDao extends Dao<MeusPontos> {

	public List<MeusPontos> carregaPorUsuario(String adUserId) throws Exception;

}