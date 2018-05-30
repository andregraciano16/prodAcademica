package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.LivroDao;
import br.ucb.entity.Livro;

public class LivroDaoImpl extends DaoGenericoImpl<Livro, Integer> implements LivroDao {

	@Override
	public Livro buscarByidProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from Livro st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (Livro) query.getSingleResult();
	}

}
