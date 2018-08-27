package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.adaptaconsultoria.amr.model.ParceiroNegocios;
import br.com.adaptaconsultoria.amr.persistence.dao.ParceiroNegociosDao;
import br.com.adaptaconsultoria.amr.properties.AMRProperties;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HParceiroNegociosDao extends HDao<ParceiroNegocios> implements ParceiroNegociosDao {

	@Override
	public ParceiroNegocios carregaPorTaxID(String taxId) throws Exception {
		String query = "SELECT obj FROM ParceiroNegocios obj WHERE regexp_replace(obj.cnpj, '[^0-9]', '', 'gi') = regexp_replace(:taxId, '[^0-9]', '', 'gi') AND obj.empresa.id = :adClientId";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("taxId", taxId);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		List<ParceiroNegocios> lista = pesquisa(query, parametros);
		if (lista == null || lista.isEmpty())
			return null;
		return lista.get(0);
	}

	@Override
	public ParceiroNegocios carregaPorCodigoDeParceiroDeNegocios(String codigo) throws Exception {
		String query = "SELECT pn from ParceiroNegocios pn where pn.codigo = :codigo AND pn.empresa.id = :adClientId";
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", codigo);
		parametros.put("adClientId", AMRProperties.getInstance().getPropriedade("adclientid"));

		return carrega(query, parametros);
	}

	@Override
	public ParceiroNegocios carregaPorCodigoDeParceiroDeNegocios() throws Exception {
		String query = "SELECT pn from ParceiroNegocios pn where pn.empresa.id = :emp and pn.organizacao.id = :org";

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("emp", AMRProperties.getInstance().getPropriedade("adclientid"));
		parametros.put("org", AMRProperties.getInstance().getPropriedade("adorgid"));

		return carrega(query, parametros);
	}

}