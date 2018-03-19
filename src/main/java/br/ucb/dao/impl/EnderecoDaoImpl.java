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
	
	@Override
	public Endereco find(Endereco end) {
		Query query = getManager().createQuery(" from Endereco st  WHERE st.estado like ?1  and st.bairro like ?2 and cidade like ?3 and complemento like ?4 and rua like ?5"
				+ " and numero = ?6");
		query.setParameter(1, end.getEstado());
		query.setParameter(2, end.getBairro());
		query.setParameter(3, end.getCidade());
		query.setParameter(4, end.getComplemento());
		query.setParameter(5, end.getRua());
		query.setParameter(6, end.getNumero());
		return (Endereco) query.getResultList().get(0);
	}

}
