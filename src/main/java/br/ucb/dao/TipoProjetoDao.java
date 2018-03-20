package br.ucb.dao;

import java.util.List;

import br.ucb.entity.TipoProjeto;


public interface TipoProjetoDao extends DaoGenerico<TipoProjeto, Integer>{
	
	
	public TipoProjeto findById(Integer id);
	public List<TipoProjeto> findBySearch(TipoProjeto tipoProjeto);

}

