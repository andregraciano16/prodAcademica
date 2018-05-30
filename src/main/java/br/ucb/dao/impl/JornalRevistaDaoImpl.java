package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.JornalRevistaDao;
import br.ucb.entity.ArtigoJornalRevista;

public class JornalRevistaDaoImpl extends DaoGenericoImpl<ArtigoJornalRevista, Integer> implements JornalRevistaDao {

	@Override
	public ArtigoJornalRevista buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from ArtigoJornalRevista st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (ArtigoJornalRevista) query.getSingleResult();
	}

}
