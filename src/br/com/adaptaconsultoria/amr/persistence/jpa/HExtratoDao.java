package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Extrato;
import br.com.adaptaconsultoria.amr.model.ExtratoNovo;
import br.com.adaptaconsultoria.amr.persistence.dao.ExtratoDao;

/**
 *
 * @author Moises Kerschner
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HExtratoDao extends HDao<Extrato> implements ExtratoDao 
{
	@Override
	public List<Extrato> carregaPorUsuario(String adUserId, Date dataInicial, Date dataFinal) throws Exception 
	{
		String query = "SELECT obj FROM Extrato obj WHERE obj.adUserId = :adUserId ";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		query += "AND TRUNC(obj.dataInicial) <= :dataFinal AND TRUNC(obj.dataFinal) >= :dataInicial";

		parametros.put("dataInicial", dataInicial);
		parametros.put("dataFinal", dataFinal);

		return pesquisa(query, parametros);
	}
	
	@Override
	public BigDecimal getCreditoInicial(String cbPartnerId, String dataInicial) 
	{
		try {
			Query q = getEntityManager().createNativeQuery(" select amr_saldoanterior('"+cbPartnerId+"' , '"+dataInicial+"') ");
			return (BigDecimal) q.getSingleResult();
			
		} catch (Exception e) 
		{
			return null;
		}
	}
	
	public  List<ExtratoNovo> getExtrato(String cbPartnerId, String dataInicial, String dataFinal)
	{
		Query q = getEntityManager().createNativeQuery(" select * from amr_extratofinanceiro('"+cbPartnerId+"', '"+dataInicial+"', '"+dataFinal+"')", ExtratoNovo.class);
		
		try 
		{
			@SuppressWarnings("unchecked")
			List<ExtratoNovo> resultado = q.getResultList();
			return resultado;
		}catch(Exception e)
		{
			return null;
		}
		
	}
}