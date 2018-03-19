
package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Comentario;

public interface ComentarioDao extends DaoGenerico<Comentario, Integer>{

	public List<Comentario> find(Comentario comentario);
	
}

