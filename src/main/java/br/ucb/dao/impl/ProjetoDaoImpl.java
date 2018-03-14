package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.ProjetoDao;
import br.ucb.entity.Projeto;


public class ProjetoDaoImpl extends DaoGenericoImpl<Projeto, Integer> implements ProjetoDao{
	
	@Override
	public List<Projeto> findByDescricao(String descricao) {
		Query query = getManager().createQuery(" From Projeto p  WHERE p.descricao like ?1 ");
		query.setParameter(1, "%"+ descricao + "%");
		
		return query.getResultList();

	}


}
