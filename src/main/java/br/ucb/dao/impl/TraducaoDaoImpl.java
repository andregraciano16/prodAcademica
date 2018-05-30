package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.TraducaoDao;
import br.ucb.entity.DesenvDidaticoInstitucional;
import br.ucb.entity.Traducao;

public class TraducaoDaoImpl extends DaoGenericoImpl<Traducao, Integer>  implements TraducaoDao {

	@Override
	public Traducao buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from Traducao st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (Traducao) query.getSingleResult();
	}

}
