package br.ucb.dao;

import br.ucb.entity.CartaMapaSimilares;

public interface CartasMapasSimilaresDao extends DaoGenerico<CartaMapaSimilares, Integer> {

	CartaMapaSimilares buscarByIdProducao(Integer idProducaoAcademica);

}
