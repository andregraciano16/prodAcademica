package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Projeto;


public interface ProjetoDao extends DaoGenerico<Projeto, Integer>{

	public Projeto findById(Integer integer);

	public List<Projeto> findBySearch(Projeto projeto);
	
}

