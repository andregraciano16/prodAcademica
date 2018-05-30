package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.TrabalhoEmAnaisDao;
import br.ucb.entity.TrabalhoEmAnais;

public class TrabalhoEmAnaisDaoImpl extends DaoGenericoImpl<TrabalhoEmAnais, Integer> implements TrabalhoEmAnaisDao {

	@Override
	public TrabalhoEmAnais buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from TrabalhoEmAnais st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (TrabalhoEmAnais) query.getSingleResult();
	}

}
