package br.ucb.dao;

import java.util.List;

import br.ucb.VO.DocenteVO;
import br.ucb.entity.Docente;

public interface DocenteDao extends DaoGenerico<Docente, Integer> {

	Docente findById(Integer integer);

	List<Docente> find(DocenteVO docentePesq);

}
