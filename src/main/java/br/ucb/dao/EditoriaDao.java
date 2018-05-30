package br.ucb.dao;

import br.ucb.entity.Editoria;

public interface EditoriaDao extends DaoGenerico<Editoria, Integer> {

	Editoria buscarByIdProducao(Integer idProducaoAcademica);

}
