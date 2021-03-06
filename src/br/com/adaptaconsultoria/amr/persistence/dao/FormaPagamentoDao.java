package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.FormaPagamento;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface FormaPagamentoDao extends Dao<FormaPagamento> {

	public List<FormaPagamento> porAdClientId(String adClientId);

}