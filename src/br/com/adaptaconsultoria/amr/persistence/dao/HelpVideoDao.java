package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.HelpVideo;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface HelpVideoDao extends Dao<HelpVideo> {

	public List<HelpVideo> listaPorPalavraChave(String... palavra) throws Exception;

	public List<HelpVideo> listaPorClient(String adClientId) throws Exception;

}