package br.ucb.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.ucb.dao.DaoGenerico;
import br.ucb.util.JPAUtil;

public class DaoGenericoImpl<T extends Serializable, Key> implements DaoGenerico<T, Key> {

	private EntityManager manager;

	public void save(T t) {
		try {
			manager = JPAUtil.getEntityManaged();
			manager.getTransaction().begin();
			manager.persist(manager.merge(t));
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public void update(T t) {
		try {
			manager = JPAUtil.getEntityManaged();
			manager.getTransaction().begin();
			manager.merge(t);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public List<T> list() {

		manager = JPAUtil.getEntityManaged();
		List<T> t = manager.createQuery("from " + getTypeClass().getName()).getResultList();
		manager.close();
		return t;
	}

	public T findByKey(Class<T> t, Key key) {
		manager = JPAUtil.getEntityManaged();
		T t2 = manager.find(t, key);
		manager.close();
		return t2;
	}

	public void remove(T t) {
		try {
			manager = JPAUtil.getEntityManaged();
			manager.getTransaction().begin();
			manager.remove(manager.merge(t));
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	public boolean saveM(T t) {
		try {
			manager = JPAUtil.getEntityManaged();
			manager.getTransaction().begin();
			manager.persist(manager.merge(t));
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
			// manager.getTransaction().rollback();

		} finally {
			manager.close();
		}
		return true;
	}

	public boolean updateM(T t) {
		try {
			manager = JPAUtil.getEntityManaged();
			manager.getTransaction().begin();
			manager.merge(t);
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
			return false;

		} finally {
			manager.close();
		}
		return true;
	}

	public boolean removeM(T t) {
		try {
			manager = JPAUtil.getEntityManaged();
			manager.getTransaction().begin();
			manager.remove(manager.merge(t));
			manager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
			// manager.getTransaction().rollback();

		} finally {
			manager.close();
		}
		return true;
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}

	public EntityManager getManager() {
		if (this.manager == null || !this.manager.isOpen()) {
			this.manager = JPAUtil.getEntityManaged();
		}
		return this.manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
