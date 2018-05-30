package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.RelatorioPesquisaDao;
import br.ucb.entity.RelatorioPesquisa;

public class RelatorioPesquisaDaoImpl extends DaoGenericoImpl<RelatorioPesquisa, Integer> implements RelatorioPesquisaDao {

	@Override
	public RelatorioPesquisa buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from RelatorioPesquisa st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (RelatorioPesquisa) query.getSingleResult();
	}

}
