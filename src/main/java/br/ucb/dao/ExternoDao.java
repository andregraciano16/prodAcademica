package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Externo;

public interface ExternoDao extends DaoGenerico<Externo, Integer> {

	Externo findById(Integer integer);

	List<Externo> findByNome(String nome);

}
