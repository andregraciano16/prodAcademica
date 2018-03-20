package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.StatusAlunoDao;
import br.ucb.entity.StatusAluno;

public class StatusAlunoDaoImpl extends DaoGenericoImpl<StatusAluno, Integer> implements StatusAlunoDao{

	@Override
	public List<StatusAluno> findByDescricao(String descricao) {
		Query query = getManager().createQuery(" From StatusAluno st  WHERE st.descricao like ?1 ");
		query.setParameter(1, "%"+ descricao + "%");
		
		return query.getResultList();

	}

	@Override
	public StatusAluno findById(Integer id) {
		
		Query query = getManager().createQuery(" from StatusAluno st  WHERE st.idStatusAluno like ?1 ");
		query.setParameter(1,id);
		
		return (StatusAluno) query.getSingleResult();
	}

}
