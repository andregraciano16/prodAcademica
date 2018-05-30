package br.ucb.dao;

import br.ucb.entity.ServicosTecnicos;

public interface ServicoTecnicosDao extends DaoGenerico<ServicosTecnicos, Integer> {

	ServicosTecnicos buscarByIdProducao(Integer idProducaoAcademica);

}
