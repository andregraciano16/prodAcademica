
package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Materia;


public interface MateriaDao extends DaoGenerico<Materia, Integer> {

	public List<Materia> findBySearch(Materia materia);
	

}
