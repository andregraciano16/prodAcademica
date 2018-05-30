package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.DesenvTecnicaDao;
import br.ucb.entity.DesenvTecnica;

public class DesenvTecnicaDaoImpl extends DaoGenericoImpl<DesenvTecnica, Integer> implements DesenvTecnicaDao {

	@Override
	public DesenvTecnica buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from DesenvTecnica st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (DesenvTecnica) query.getSingleResult();
	}

}
