package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.EditoriaDao;
import br.ucb.entity.Editoria;

public class EditoriaDaoImpl extends DaoGenericoImpl<Editoria, Integer> implements EditoriaDao {

	@Override
	public Editoria buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from Editoria st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (Editoria) query.getSingleResult();
	}

}
