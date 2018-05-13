package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.StatusAprovacaoDao;
import br.ucb.entity.StatusAprovacao;

public class StatusAprovacaoDaoImpl extends DaoGenericoImpl<StatusAprovacao,Integer> implements StatusAprovacaoDao{

	@Override
	public List<StatusAprovacao> findByDescricao(String descricao) {
		Query query = getManager().createQuery(" From StatusAprovacao sa  WHERE sa.descricao like ?1 ");
		query.setParameter(1, "%"+ descricao + "%");
		
		return query.getResultList();

	}

	@Override
	public StatusAprovacao findById(Integer id) {
		
		Query query = getManager().createQuery(" from StatusAprovacao st  WHERE st.idStatusAprovacao like ?1 ");
		query.setParameter(1,id);
		
		return (StatusAprovacao) query.getSingleResult();
	}

	
}
