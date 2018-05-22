package br.ucb.dao;

import java.util.List;

import br.ucb.VO.UsuarioVO;
import br.ucb.entity.Aluno;

public interface AlunoDao extends DaoGenerico<Aluno, Integer>{


	Aluno findById(Integer integer);

	List<Aluno> findBySearch(Aluno aluno);

	UsuarioVO findByMatricula(String matricula);

	List<Aluno> listFiltro(String anoInicio, String anoFim);
	
	Aluno getAlunobyMatricula(String matricula);

	Integer getIdbyMatricula(String matricula);
		


	
}
