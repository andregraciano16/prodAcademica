package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.DesenvAppDao;
import br.ucb.entity.DesenvApp;

public class DesenvAppDaoImpl extends DaoGenericoImpl<DesenvApp, Integer> implements DesenvAppDao {

	@Override
	public DesenvApp buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from DesenvApp st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (DesenvApp) query.getSingleResult();
	}

}
