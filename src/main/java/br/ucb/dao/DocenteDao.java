package br.ucb.dao;

import java.util.List;

import br.ucb.VO.DocenteVO;
import br.ucb.VO.UsuarioVO;
import br.ucb.entity.Docente;

public interface DocenteDao extends DaoGenerico<Docente, Integer> {

	Docente findById(Integer integer);

	List<Docente> find(DocenteVO docentePesq);

	UsuarioVO findByMatricula(String matricula);

	List<Docente> listFiltro(String anoInicio, String anoFim);
	
	Integer getIdbyMatricula(String matricula);

	Docente getDocentebyMatricula(String matricula);
	
	String getNomebyMatricula(String matricula);

	Docente findByMatriculaDocente(String matricula);
}
