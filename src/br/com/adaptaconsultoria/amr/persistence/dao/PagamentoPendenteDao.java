package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.PagamentoPendente;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface PagamentoPendenteDao extends Dao<PagamentoPendente> {

	public List<PagamentoPendente> carregaPorUsuario(String adUserId) throws Exception;

	public String paga(String adUserId, String cDebtPaymentId, String voucher) throws Exception;

	public String pagamentoTeste(String adClientId) throws Exception;

}