package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Manutencao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface ManutencaoDao extends Dao<Manutencao> {

	public boolean temAtivas(String adClientId) throws Exception;

	public List<Manutencao> listaAtivas(String adClientId) throws Exception;

}