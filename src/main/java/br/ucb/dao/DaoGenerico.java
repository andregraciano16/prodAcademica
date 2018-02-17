package br.ucb.dao;

import java.io.Serializable;
import java.util.List;

public interface DaoGenerico<T extends Serializable, Key> {

	public void save(T t);

	public void update(T t);

	public List<T> list();

	public T findByKey(Class<T> t, Key key);

	public void remove(T t);

}
