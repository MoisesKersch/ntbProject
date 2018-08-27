package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.RedeBinaria;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface RedeBinariaDao extends Dao<RedeBinaria> {

	public List<RedeBinaria> listaPorID(String id) throws Exception;

	public String carregaIDPorUsuario(String adUserId) throws Exception;

	public String carregaNoRaiz(String adUserId) throws Exception;

	public RedeBinaria carregaPorUsuario(String adUserId) throws Exception;

	public Boolean descente(String adUserRaizId, String adUserId) throws Exception;

	public RedeBinaria carregaPorCodigoDeParceiroDeNegocios(String codigo) throws Exception;

}