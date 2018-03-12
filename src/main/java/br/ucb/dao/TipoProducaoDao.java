package br.ucb.dao;

import java.util.List;

import br.ucb.entity.TipoProducao;

public interface TipoProducaoDao extends DaoGenerico<TipoProducao, Integer> {

	public List<TipoProducao> findByDescricaoAndTipo(String tipo, String descricao);
	
}
