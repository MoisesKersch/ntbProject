package br.com.adaptaconsultoria.amr.persistence.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.adaptaconsultoria.amr.model.Extrato;
import br.com.adaptaconsultoria.amr.model.ExtratoNovo;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ExtratoDao extends Dao<Extrato> 
{
	public List<Extrato> carregaPorUsuario(String adUserId, Date dataInicial, Date dataFinal) throws Exception;
	
	public BigDecimal getCreditoInicial(String cbPartnerId, String dataInicial);
	
	List<ExtratoNovo> getExtrato(String cbPartnerId, String dataInicial, String dataFinal);
}