package br.com.adaptaconsultoria.amr.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.adaptaconsultoria.amr.model.MetodoEntrega;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface MetodoEntregaDao extends Dao<MetodoEntrega> {

	public List<MetodoEntrega> listaPorPesoMaximo(String adClientId, String amrPreCompraId, BigDecimal pesoMaximo) throws Exception;

	public List<MetodoEntrega> porPreCompra(String preCompraId);

	public MetodoEntrega porPreCompraMetodoEntrega(String preCompraId, String metodoEntregaId);

}