package br.ucb.dao;

import java.util.List;

import br.ucb.entity.StatusAluno;

public interface StatusAlunoDao extends DaoGenerico<StatusAluno, Integer>{
	
	List<StatusAluno> findByDescricao(String descricao);

	StatusAluno findById(Integer integer);


	
}
