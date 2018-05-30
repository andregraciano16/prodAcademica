package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.CursoCurtaDuracaoDao;
import br.ucb.entity.CursoCurtaDuracao;

public class CursoCurtaDuracaoDaoImpl extends DaoGenericoImpl<CursoCurtaDuracao, Integer> implements CursoCurtaDuracaoDao{

	@Override
	public CursoCurtaDuracao buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from CursoCurtaDuracao st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (CursoCurtaDuracao) query.getSingleResult();
	}

}
