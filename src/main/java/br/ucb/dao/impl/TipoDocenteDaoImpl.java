package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.TipoDocenteDao;
import br.ucb.entity.TipoDocente;

public class TipoDocenteDaoImpl extends DaoGenericoImpl<TipoDocente, Integer> implements TipoDocenteDao{

	@Override
	public List<TipoDocente> findByTipo(String tipo) {
		Query query = getManager().createQuery(" From TipoDocente t  WHERE t.tipo like ?1 ");
		query.setParameter(1, "%"+ tipo + "%");
		return query.getResultList();
	}

}
