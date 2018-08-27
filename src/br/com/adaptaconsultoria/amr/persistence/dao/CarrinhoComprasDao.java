package br.com.adaptaconsultoria.amr.persistence.dao;

import br.com.adaptaconsultoria.amr.model.CarrinhoCompras;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface CarrinhoComprasDao extends Dao<CarrinhoCompras> {

	public CarrinhoCompras carregaAbertoPorUsuario(String adUserId, String vendaDireta) throws Exception;

	public String processaCarrinho(String amrCarrinhoComprasId) throws Exception;

}