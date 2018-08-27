package br.com.adaptaconsultoria.amr.persistence.dao;

import java.util.List;

import br.com.adaptaconsultoria.amr.model.Matricula;

/**
 *
 * @author Gabriel Augustin
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface MatriculaDao extends Dao<Matricula> {

	public List<Matricula> porcBPartnerId(String cBPartnerId) throws Exception;

}