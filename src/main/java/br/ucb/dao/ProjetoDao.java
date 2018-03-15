package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Projeto;


public interface ProjetoDao extends DaoGenerico<Projeto, Integer>{
	
	public List<Projeto> findByDescricao(String descricao);

	public Projeto findById(Integer integer);
	
}

