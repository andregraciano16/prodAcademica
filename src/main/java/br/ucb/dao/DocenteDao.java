package br.ucb.dao;

import br.ucb.entity.Docente;

public interface DocenteDao extends DaoGenerico<Docente, Integer> {

	Docente findById(Integer integer);

}
