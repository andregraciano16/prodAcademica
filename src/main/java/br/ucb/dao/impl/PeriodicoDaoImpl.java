package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.PeriodicoDao;
import br.ucb.entity.ArtigoPeriodico;

public class PeriodicoDaoImpl extends DaoGenericoImpl<ArtigoPeriodico, Integer> implements PeriodicoDao  {

	@Override
	public ArtigoPeriodico buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from ArtigoPeriodico st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (ArtigoPeriodico) query.getSingleResult();
	}

}
