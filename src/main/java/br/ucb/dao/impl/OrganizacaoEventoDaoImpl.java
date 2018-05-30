package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.OrganizacaoEventoDao;
import br.ucb.entity.OrganizacaoEvento;

public class OrganizacaoEventoDaoImpl extends DaoGenericoImpl<OrganizacaoEvento, Integer> implements OrganizacaoEventoDao{

	@Override
	public OrganizacaoEvento buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from OrganizacaoEvento st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (OrganizacaoEvento) query.getSingleResult();
	}

}
