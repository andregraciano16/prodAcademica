package br.ucb.dao;

import br.ucb.entity.DesenvDidaticoInstitucional;

public interface DesenvDidaticoInstDao extends DaoGenerico<DesenvDidaticoInstitucional, Integer> {

	DesenvDidaticoInstitucional buscarByIdProducao(Integer idProducaoAcademica);

}
