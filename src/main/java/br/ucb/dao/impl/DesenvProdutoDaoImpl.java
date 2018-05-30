package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.DesenvProdutoDao;
import br.ucb.entity.DesenvProduto;

public class DesenvProdutoDaoImpl extends DaoGenericoImpl<DesenvProduto, Integer> implements DesenvProdutoDao {

	@Override
	public DesenvProduto buscarByIdProducao(Integer idProducaoAcademica) {
		Query query = getManager().createQuery(" from DesenvProduto st  WHERE st.producaoAcademica.idProducaoAcademica = ? ");
		query.setParameter(1, idProducaoAcademica);
		return (DesenvProduto) query.getSingleResult();
	}

}
