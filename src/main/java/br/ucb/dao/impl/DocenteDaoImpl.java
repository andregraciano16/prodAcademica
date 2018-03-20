package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.DocenteDao;
import br.ucb.entity.Docente;


public class DocenteDaoImpl extends DaoGenericoImpl<Docente, Integer> implements DocenteDao {

	@Override
	public Docente findById(Integer id) {
		Query query = getManager().createQuery(" from Docente d  WHERE d.idDocente like ?1 ");
		query.setParameter(1,id);
		
		return (Docente) query.getSingleResult();
	}

}
