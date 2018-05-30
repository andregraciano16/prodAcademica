package br.ucb.dao;

import br.ucb.entity.Traducao;

public interface TraducaoDao extends DaoGenerico<Traducao, Integer> {

	Traducao buscarByIdProducao(Integer idProducaoAcademica);

}
