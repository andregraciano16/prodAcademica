package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.CartasMapasSimilaresDao;
import br.ucb.entity.CartaMapaSimilares;

public class CartasMapasSimilaresDaoImpl extends DaoGenericoImpl<CartaMapaSimilares, Integer> implements CartasMapasSimilaresDao {

	@Override
	public CartaMapaSimilares buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from CartaMapaSimilares st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (CartaMapaSimilares) query.getSingleResult();
	}

}
