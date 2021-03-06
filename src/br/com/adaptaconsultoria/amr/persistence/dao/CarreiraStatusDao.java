package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.CarreiraStatus;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface CarreiraStatusDao extends Dao<CarreiraStatus> {

	public List<CarreiraStatus> carregaPorUsuario(String adUserId) throws Exception;

}