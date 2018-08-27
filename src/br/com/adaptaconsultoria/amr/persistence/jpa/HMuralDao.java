package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.Mural;
import br.com.adaptaconsultoria.amr.persistence.dao.MuralDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HMuralDao extends HDao<Mural> implements MuralDao {

	@Override
	public List<Mural> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM Mural obj WHERE obj.adUserId = :adUserId AND adClientId = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return pesquisa(query, parametros);
	}

	@Override
	public BigDecimal getQualificacaoRede(String cBpartnerId)
	{
		Query q = getEntityManager().createNativeQuery("select  SUM(CASE WHEN qualificado = 'Y' THEN qtde ELSE 0 END) / SUM(qtde) * 100.00\n" + 
				"from (\n" + 
				"\n" + 
				"	select \n" + 
				"		u.ad_client_id,\n" + 
				"		u.em_amr_qualificado as qualificado,\n" + 
				"		count(*) as qtde\n" + 
				"	from amr_redelinear(:cBpartnerId) r\n" + 
				"	join ad_user u ON u.c_bpartner_id = r.c_bpartner_id\n" + 
				"	group by 1,2\n" + 
				"	\n" + 
				") tbl");

		q.setParameter("cBpartnerId", cBpartnerId);
		return (BigDecimal) q.getSingleResult();
	}

	@Override
	public BigDecimal getBonusIncentivoRede(String cBpartnerId)
	{
		Query q = getEntityManager().createNativeQuery("select  SUM(CASE WHEN ispaid = 'Y' THEN valor ELSE 0 END) / SUM(valor) * 100.00\n" + 
				"from (\n" + 
				"\n" + 
				"	select\n" + 
				"		dp.ispaid,\n" + 
				"		sum(dp.amount) as valor\n" + 
				"	from amr_redelinear(:cBpartnerId) r\n" + 
				"	join amr_ativacao a ON a.c_bpartner_id = r.c_bpartner_id\n" + 
				"	join c_debt_payment dp ON dp.c_settlement_generate_id = a.c_settlement_id\n" + 
				"	where dp.dateplanned < CURRENT_DATE\n" + 
				"\n" + 
				"	group by 1\n" + 
				"	\n" + 
				") tbl");

		q.setParameter("cBpartnerId", cBpartnerId);
		return (BigDecimal) q.getSingleResult();
	}

	@Override
	public BigDecimal getIndicePagamentosDiretos(String cBpartnerId)
	{
		Query q = getEntityManager().createNativeQuery("select  (SUM(CASE WHEN ispaid = 'Y' THEN valor ELSE 0.00 END) / SUM(valor)) * 100.00\n" + 
				"from (\n" + 
				"\n" + 
				"	select\n" + 
				"		dp.ispaid,\n" + 
				"		sum(dp.amount) as valor\n" + 
				"	from c_bpartner bp \n" + 
				"	join amr_ativacao a ON a.c_bpartner_id = bp.c_bpartner_id\n" + 
				"	join c_debt_payment dp ON dp.c_settlement_generate_id = a.c_settlement_id\n" + 
				"	where bpartner_parent_id = :cBpartnerId\n" + 
				"	and dp.dateplanned < CURRENT_DATE\n" + 
				"	group by 1\n" + 
				"	\n" + 
				") tbl");

		q.setParameter("cBpartnerId", cBpartnerId);
		return (BigDecimal) q.getSingleResult();
	}

	@Override
	public BigDecimal getContasAtivaRede(String cBpartnerId)
	{
		Query q = getEntityManager().createNativeQuery("select  SUM(CASE WHEN ativopago = 'Y' THEN qtde ELSE 0 END) / SUM(qtde) * 100.00\n" + 
				"from (\n" + 
				"\n" + 
				"	select\n" + 
				" 		amr_ativopago(u.ad_user_id, CURRENT_DATE) AS ativopago,\n" + 
				"		count(*) as qtde	\n" + 
				"	from amr_redelinear(:cBpartnerId) r\n" + 
				"	join ad_user u on u.c_bpartner_id = r.c_bpartner_id\n" + 
				"	where u.em_amr_qualificado = 'Y'\n" + 
				"	group by 1\n" + 
				"	\n" + 
				") tbl");

		q.setParameter("cBpartnerId", cBpartnerId);
		return (BigDecimal) q.getSingleResult();
	}

}
