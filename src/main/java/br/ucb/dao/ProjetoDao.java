package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Aluno;
import br.ucb.entity.Docente;
import br.ucb.entity.Projeto;


public interface ProjetoDao extends DaoGenerico<Projeto, Integer>{

	public Projeto findById(Integer integer);

	public List<Projeto> findBySearch(Projeto projeto);
	
	public Projeto find(Projeto projeto);
	
	public List<Projeto> findByAutorDocente(Docente docente);
	
	public List<Projeto> findByAutorAluno(Aluno aluno);
	
}

