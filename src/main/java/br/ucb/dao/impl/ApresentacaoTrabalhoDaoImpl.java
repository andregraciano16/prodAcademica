package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.ApresentacaoTrabalhoDao;
import br.ucb.entity.ApresentacaoTrabalho;
import br.ucb.entity.ProducaoAcademica;

public class ApresentacaoTrabalhoDaoImpl extends DaoGenericoImpl<ApresentacaoTrabalho, Integer> implements ApresentacaoTrabalhoDao{

	@Override
	public ApresentacaoTrabalho buscarByIdProducao(Integer id) {
		Query query = getManager().createQuery(" from ApresentacaoTrabalho st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, id);
		return (ApresentacaoTrabalho) query.getSingleResult();
	}

}
