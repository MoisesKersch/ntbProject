package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.model.RedeLinear;
import br.com.adaptaconsultoria.amr.persistence.dao.RedeLinearDao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HRedeLinearDao extends HDao<RedeLinear> implements RedeLinearDao {

	@Override
	public synchronized List<RedeLinear> carregaPorUsuario(String adUserId) throws Exception {
		String query = "SELECT obj FROM RedeLinear obj WHERE obj.adUserId = :adUserId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("adUserId", adUserId);

		return pesquisa(query, parametros);
	}

	@Override
	public Boolean descente(String cBPartnerRaizId, String cBPartnerId) throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT amr_descendentelinear('" + cBPartnerRaizId + "', '" + cBPartnerId + "')");
		String resultado = (String) q.getSingleResult();
		return resultado.equalsIgnoreCase("Y");
	}

	@Override
	public List<RedeLinear> getRedeLinear(String cBPartnerId, String adClientId) 
	{
		Query q = getEntityManager().createNativeQuery( "SELECT\n" + 
				"	bp.c_bpartner_id as amr_redelinear_id,\n" + 
				"	bp.value as id,\n" + 
				"	bp.name as nome,\n" + 
				"	u.username as login,\n" + 
				"	ac_telefone(bp.c_bpartner_id) as celular,\n" + 
				"	bp.em_af_email as email,\n" + 
				"	up.username as indicador,\n" + 
				"	COALESCE(b.created, bp.created) as posicionadoem,\n" + 
				"	( SELECT (e.cidade || ' ') || e.uf FROM ac_endereco e WHERE e.c_bpartner_id = bp.c_bpartner_id LIMIT 1) AS cidade,\n" + 
				"	p.nome as perfil,\n" + 
				"	g.graduacao,\n" + 
				"	tbl.nivel,\n" + 
				"	amr_ativopago(u.ad_user_id, CURRENT_DATE) ativopago\n" + 
				"FROM (\n" + 
				"	SELECT * FROM (\n" + 
				"		SELECT\n" + 
				"			bp.c_bpartner_id,\n" + 
				"			amr_descendentelinear('"+cBPartnerId+"', bp.c_bpartner_id, null) as nivel\n" + 
				"		FROM c_bpartner bp\n" + 
				"		WHERE bp.ad_client_id = '"+adClientId+"'\n" + 
				"	) tblx\n" + 
				"	WHERE tblx.nivel <> 0\n" + 
				") tbl\n" + 
				"JOIN c_bpartner bp ON bp.c_bpartner_id = tbl.c_bpartner_id\n" + 
				"JOIN ad_user u ON u.c_bpartner_id = bp.c_bpartner_id\n" + 
				"LEFT JOIN ad_user up ON up.c_bpartner_id = bp.bpartner_parent_id\n" + 
				"JOIN amr_perfil p ON p.amr_perfil_id = bp.em_amr_perfil_id\n" + 
				"LEFT JOIN amr_binario b ON b.ad_user_id = u.ad_user_id\n" + 
				"LEFT JOIN amr_graduacao g ON g.c_bpartner_id = bp.c_bpartner_id\n" + 
				"ORDER BY tbl.nivel, u.username ", RedeLinear.class);
		
		@SuppressWarnings("unchecked")
		List<RedeLinear> redeLinear = q.getResultList();
		 
		return redeLinear;
	}

}