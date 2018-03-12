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
		}
	}

	public List<T> list() {
		manager = JPAUtil.getEntityManaged();
		return manager.createQuery("from " + getTypeClass().getName()).getResultList();
	}

	public T findByKey(Class<T> t, Key key) {
		manager = JPAUtil.getEntityManaged();
		return manager.find(t, key);
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
		}
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}

	public EntityManager getManager() {
		if(this.manager == null){
			this.manager = JPAUtil.getEntityManaged();
		}
		return this.manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
}
