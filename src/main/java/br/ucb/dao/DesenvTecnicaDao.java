package br.ucb.dao;

import br.ucb.entity.DesenvTecnica;

public interface DesenvTecnicaDao extends DaoGenerico<DesenvTecnica, Integer>{

	DesenvTecnica buscarByIdProducao(Integer idProducaoAcademica);

}
