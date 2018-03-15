package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.AlunoDao;
import br.ucb.entity.Aluno;

public class AlunoDaoImpl extends DaoGenericoImpl<Aluno, Integer> implements AlunoDao{

	@Override
	public List<Aluno> findByNome(String nome) {
		Query query = getManager().createQuery(" From Aluno a  WHERE a.nome like ?1 ");
		query.setParameter(1, "%"+ nome + "%");
		
		return query.getResultList();
	}

	
}
