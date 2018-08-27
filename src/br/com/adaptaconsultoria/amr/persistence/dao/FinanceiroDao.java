package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.Date;
import java.util.List;

import br.com.adaptaconsultoria.amr.model.Financeiro;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface FinanceiroDao extends Dao<Financeiro> {

	public List<Financeiro> carregaPorUsuario(String adUserId) throws Exception;

	public List<Financeiro> carregaPorUsuarioMesEAno(String adUserId, int mes, int ano, Date dataInicial, Date dataFinal) throws Exception;

}