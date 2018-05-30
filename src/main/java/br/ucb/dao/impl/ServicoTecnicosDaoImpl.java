package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.ServicoTecnicosDao;
import br.ucb.entity.ServicosTecnicos;

public class ServicoTecnicosDaoImpl extends DaoGenericoImpl<ServicosTecnicos, Integer> implements ServicoTecnicosDao {

	@Override
	public ServicosTecnicos buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from ServicosTecnicos st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (ServicosTecnicos) query.getSingleResult();
	}

}
