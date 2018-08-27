package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.RedeLinear;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface RedeLinearDao extends Dao<RedeLinear> {

	public List<RedeLinear> carregaPorUsuario(String cBPartnerId) throws Exception;

	public Boolean descente(String cBPartnerRaizId, String cBPartnerId) throws Exception;
	
	public List<RedeLinear> getRedeLinear(String cBPartnerId,String adClientId);
	

}