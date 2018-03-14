
package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Materia;


public interface MateriaDao extends DaoGenerico<Materia, Integer> {

	public List<Materia> findByDescricao(String descricao);
	
	//public List<Materia> findByDescricaoAndLinhaPesquisa(String descricao, LinhaPesquisa linhaPesquisa);

}
