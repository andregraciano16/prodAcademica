package br.ucb.dao;

import br.ucb.entity.TrabalhoEmAnais;

public interface TrabalhoEmAnaisDao extends DaoGenerico<TrabalhoEmAnais, Integer> {

	TrabalhoEmAnais buscarByIdProducao(Integer idProducaoAcademica);

}
