package br.ucb.dao;

import java.util.List;

import br.ucb.entity.TipoDocente;

public interface TipoDocenteDao extends DaoGenerico<TipoDocente, Integer> {

	List<TipoDocente> findByTipo(String tipo);
	
}
