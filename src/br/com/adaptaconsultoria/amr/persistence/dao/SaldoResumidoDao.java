package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.SaldoResumido;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface SaldoResumidoDao extends Dao<SaldoResumido> {

	public List<SaldoResumido> carregaPorUsuario(String adUserId) throws Exception;

}