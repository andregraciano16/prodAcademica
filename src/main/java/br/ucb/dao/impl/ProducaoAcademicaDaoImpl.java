package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.entity.ProducaoAcademica;


public class ProducaoAcademicaDaoImpl extends DaoGenericoImpl<ProducaoAcademica, Integer> implements ProducaoAcademicaDao {

	@Override
	public ProducaoAcademica findById(Integer id) {
		Query query = getManager().createQuery(" from ProducaoAcademica st  WHERE st.idProducaoAcademica like ?1 ");
		query.setParameter(1,id);
		
		return (ProducaoAcademica) query.getSingleResult();
	}

}
