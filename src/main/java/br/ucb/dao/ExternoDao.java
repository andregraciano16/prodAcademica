package br.ucb.dao;

import br.ucb.entity.Externo;

public interface ExternoDao extends DaoGenerico<Externo, Integer> {

	Externo findById(Integer integer);

}
