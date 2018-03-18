package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.EnderecoDao;
import br.ucb.entity.Endereco;


public class EnderecoDaoImpl extends DaoGenericoImpl<Endereco, Integer> implements EnderecoDao{

	@Override
	public Endereco findById(Integer id) {
		Query query = getManager().createQuery(" from Endereco st  WHERE st.idEndereco like ?1 ");
		query.setParameter(1,id);
		
		return (Endereco) query.getResultList().get(0);
	}

}
