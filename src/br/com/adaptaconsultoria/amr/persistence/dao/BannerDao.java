package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Banner;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface BannerDao extends Dao<Banner> {

	public List<Banner> carregaPorEntidade(String entidadeId) throws Exception;

}