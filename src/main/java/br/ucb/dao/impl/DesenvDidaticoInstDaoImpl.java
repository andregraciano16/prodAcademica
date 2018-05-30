package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.DesenvDidaticoInstDao;
import br.ucb.entity.DesenvDidaticoInstitucional;

public class DesenvDidaticoInstDaoImpl extends DaoGenericoImpl<DesenvDidaticoInstitucional, Integer> implements DesenvDidaticoInstDao{

	@Override
	public DesenvDidaticoInstitucional buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from DesenvDidaticoInstitucional st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (DesenvDidaticoInstitucional) query.getSingleResult();
	}

}
