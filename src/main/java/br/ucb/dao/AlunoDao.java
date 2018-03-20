package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Aluno;

public interface AlunoDao extends DaoGenerico<Aluno, Integer>{


	Aluno findById(Integer integer);

	List<Aluno> findBySearch(Aluno aluno);



}
