package br.com.adaptaconsultoria.amr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.adaptaconsultoria.amr.model.FormaPagamento;
import br.com.adaptaconsultoria.amr.persistence.DaoFactory;
import br.com.adaptaconsultoria.amr.persistence.dao.FormaPagamentoDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
@Service
public class FormaPagamentoService {

	private FormaPagamentoDao formaPagamentoDao = DaoFactory.getInstance().getFormaPagamentoDao();

	public List<FormaPagamento> listaFormasDePagamentoPorAdUserId(String adUserId) throws Exception {
		return formaPagamentoDao.lista();
	}

	public List<FormaPagamento> listaFormasDePagamentoPorAdClientId() throws Exception {
		return formaPagamentoDao.porAdClientId(AMRProperties.getInstance().getPropriedade("adclientid"));
	}

}