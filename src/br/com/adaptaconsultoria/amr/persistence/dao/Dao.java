package br.com.adaptaconsultoria.amr.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public interface Dao<T> {
	
	public T salva(T obj) throws Exception;

	public T atualiza(T obj) throws Exception;

	public T carregaPorId(Serializable id) throws Exception;

	public boolean exclui(T obj) throws Exception;

	public List<T> lista() throws Exception;

	public List<T> lista(String ordem) throws Exception;

	public String getUUID() throws Exception;

}