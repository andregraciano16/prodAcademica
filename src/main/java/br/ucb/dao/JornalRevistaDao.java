package br.ucb.dao;

import br.ucb.entity.ArtigoJornalRevista;

public interface JornalRevistaDao extends DaoGenerico<ArtigoJornalRevista, Integer> {

	ArtigoJornalRevista buscarByIdProducao(Integer idProducaoAcademica);

}
