package br.ucb.dao;

import java.util.List;

import br.ucb.entity.StatusProjeto;

public interface StatusProjetoDao extends DaoGenerico<StatusProjeto, Integer>{
	
	List<StatusProjeto> findByDescricao(String descricao);

	StatusProjeto findById(Integer integer);


	
}