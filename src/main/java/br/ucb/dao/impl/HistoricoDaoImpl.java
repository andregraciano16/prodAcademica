package br.ucb.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.HistoricoDao;
import br.ucb.entity.Historico;

public class HistoricoDaoImpl extends DaoGenericoImpl<Historico, Integer> implements HistoricoDao{

	@Override
	public List<Historico> findByData(Date dataAlteracao) {
			Query query = getManager().createQuery(" From Historico h  WHERE h.dataAlteracao like ?1 ");
			query.setParameter(1, "%"+ dataAlteracao + "%");
			
			return query.getResultList();

		
	}

}
