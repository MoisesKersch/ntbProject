package br.com.adaptaconsultoria.amr.persistence.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.adaptaconsultoria.amr.persistence.dao.Dao;

/**
 *
 * @author Will Zaniol
 * @author www.adaptaconsultoria.com.br
 * @version 1.0.0
 *
 */
public class HDao<T> implements Dao<T> {

	private static EntityManager entityManager;
	protected final Class<T> oClass;

	@SuppressWarnings("unchecked")
	public HDao() {
		super();
		this.oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("provider");
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}

	public boolean exclui(T obj) {
		if (obj != null) {
			getEntityManager().clear();
			getEntityManager().getTransaction().begin();
			obj = getEntityManager().merge(obj);
			getEntityManager().remove(obj);
			getEntityManager().getTransaction().commit();
			return true;
		}
		return false;
	}

	public List<T> lista() {
		return lista(null);
	}

	@SuppressWarnings("unchecked")
	public List<T> lista(String ordem) {
		getEntityManager().clear();
		String command;
		if (ordem != null && !ordem.isEmpty()) {
			command = "SELECT obj FROM " + oClass.getSimpleName() + " obj order by obj." + ordem;
		} else {
			command = "SELECT obj FROM " + oClass.getSimpleName() + " obj";
		}
		return getEntityManager().createQuery(command).getResultList();
	}

	public T carregaPorId(Serializable id) {
		getEntityManager().clear();
		if (id == null)
			return null;
		return (T) getEntityManager().find(oClass, id);
	}

	public T salva(T obj) throws Exception {
		getEntityManager().clear();
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(obj);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			if (getEntityManager().getTransaction().isActive())
				getEntityManager().getTransaction().rollback();
			throw e;
		}
		return obj;
	}
	
	public T atualiza(T obj) throws Exception {
		try {
			getEntityManager().clear();
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(obj);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			if (getEntityManager().getTransaction().isActive())
				getEntityManager().getTransaction().rollback();
			throw e;
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public T carrega(String query, Map<String, Object> parametros) {
		getEntityManager().clear();
		Query q = getEntityManager().createQuery(query);
		for (String chave : parametros.keySet()) {
			q.setParameter(chave, parametros.get(chave));
		}
		q.setMaxResults(1);
		try {
			return (T) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> pesquisa(String query, Map<String, Object> params) {
		getEntityManager().clear();
		Query q = getEntityManager().createQuery(query);
		for (String chave : params.keySet()) {
			q.setParameter(chave, params.get(chave));
		}
		try {
			return q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public String getUUID() throws Exception {
		Query q = getEntityManager().createNativeQuery("SELECT get_UUID()");
		return (String) q.getSingleResult();
//		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	@SuppressWarnings("unchecked")
	public List<T> executa(String query, Map<String, Object> params) {
		Query q = getEntityManager().createQuery(query);
		for (String chave : params.keySet()) {
			q.setParameter(chave, params.get(chave));
		}
		try {
			return q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

}