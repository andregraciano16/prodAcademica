package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.StatusProjetoDao;
import br.ucb.entity.StatusProjeto;

public class StatusProjetoDaoImpl extends DaoGenericoImpl<StatusProjeto, Integer> implements StatusProjetoDao{

	@Override
	public List<StatusProjeto> findByDescricao(String descricao) {
		Query query = getManager().createQuery(" From StatusProjeto st  WHERE st.descricao like ?1 ");
		query.setParameter(1, "%"+ descricao + "%");
		
		return query.getResultList();

	}

	@Override
	public StatusProjeto findById(Integer id) {
		
		Query query = getManager().createQuery(" from StatusProjeto st  WHERE st.idStatusProjeto like ?1 ");
		query.setParameter(1,id);
		
		return (StatusProjeto) query.getSingleResult();
	}

}