package br.ucb.dao;

import br.ucb.entity.Endereco;

public interface EnderecoDao extends DaoGenerico<Endereco, Integer>{

	Endereco findById(Integer integer);
	
	public Endereco find(Endereco end);

}
