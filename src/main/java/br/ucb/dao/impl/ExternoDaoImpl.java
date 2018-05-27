package br.ucb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.ExternoDao;
import br.ucb.entity.Externo;

public class ExternoDaoImpl extends DaoGenericoImpl<Externo, Integer> implements ExternoDao {

	@Override
	public Externo findById(Integer id) {
		Query query = getManager().createQuery(" from Externo e  WHERE e.idExterno like ?1 ");
		query.setParameter(1, id);

		return (Externo) query.getSingleResult();
	}

	@Override
	public List<Externo> findByNome(String nome) {
		Query query = getManager().createQuery(" From Externo e WHERE e.nome like ?1 ");
		query.setParameter(1, "%"+ nome + "%");
		
		return query.getResultList();
	}
	

	public List<Externo> listDistinct() {
		Query query = getManager().createQuery("FROM Externo e group by e.nome");
		
		return query.getResultList();
	}
}
