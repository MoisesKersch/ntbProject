package br.com.adaptaconsultoria.amr.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.adaptaconsultoria.amr.model.Mural;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface MuralDao extends Dao<Mural>
{
	public List<Mural> carregaPorUsuario(String adUserId) throws Exception;
	public BigDecimal getQualificacaoRede(String cBpartnerId);
	public BigDecimal getBonusIncentivoRede(String cBpartnerId);
	public BigDecimal getIndicePagamentosDiretos(String cBpartnerId);
	public BigDecimal getContasAtivaRede(String cBpartnerId);
}