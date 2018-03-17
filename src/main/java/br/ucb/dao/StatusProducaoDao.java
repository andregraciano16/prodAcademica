package br.ucb.dao;

import java.util.List;

import br.ucb.entity.StatusProducao;

public interface StatusProducaoDao extends DaoGenerico<StatusProducao, Integer> {
	
	List<StatusProducao> findByDescricaoAndTipo(String tipo, String descricao);

}
