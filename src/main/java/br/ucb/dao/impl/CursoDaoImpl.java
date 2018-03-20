package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.CursoDao;
import br.ucb.entity.Curso;


public class CursoDaoImpl extends DaoGenericoImpl<Curso, Integer> implements CursoDao {

	@Override
	public Curso findById(Integer id) {
		Query query = getManager().createQuery(" from Curso st  WHERE st.idCurso like ?1 ");
		query.setParameter(1,id);
		
		return (Curso) query.getSingleResult();
	}

}
