package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.entity.LinhaPesquisa;


public class LinhaPesquisaDaoImpl extends DaoGenericoImpl<LinhaPesquisa, Integer> implements LinhaPesquisaDao {

	@Override
	public List<LinhaPesquisa> findByDescricao(String descricao) {
		Query query = getManager().createQuery(" From LinhaPesquisa lp  WHERE lp.descricao like ?1 ");
		query.setParameter(1, "%"+ descricao + "%");
		
		return query.getResultList();

	}

	@Override
	public LinhaPesquisa findById(Integer id) {
		
		Query query = getManager().createQuery(" from LinhaPesquisa lp  WHERE lp.idLinhaPesquisa like ?1 ");
		query.setParameter(1,id);
		
		return (LinhaPesquisa) query.getResultList().get(0);
	}
	
}
