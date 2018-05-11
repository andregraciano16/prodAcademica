package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.ExternoDao;
import br.ucb.entity.Externo;

public class ExternoDaoImpl extends DaoGenericoImpl<Externo, Integer> implements ExternoDao {

	@Override
	public Externo findById(Integer id) {
		Query query = getManager().createQuery(" from Externo e  WHERE e.idExterno like ?1 ");
		query.setParameter(1,id);
		
		return (Externo) query.getSingleResult();
	}
	
}
