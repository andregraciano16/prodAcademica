package br.ucb.dao;

import java.util.List;

import br.ucb.entity.TipoProjeto;


public interface TipoProjetoDao extends DaoGenerico<TipoProjeto, Integer>{
	
	public List<TipoProjeto> findByDescricaoAndTipo(String tipo, String descricao);
	public TipoProjeto findById(Integer id);

}

