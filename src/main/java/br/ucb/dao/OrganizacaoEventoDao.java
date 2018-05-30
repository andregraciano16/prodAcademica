package br.ucb.dao;

import br.ucb.entity.OrganizacaoEvento;

public interface OrganizacaoEventoDao extends DaoGenerico<OrganizacaoEvento, Integer> {

	OrganizacaoEvento buscarByIdProducao(Integer idProducaoAcademica);

}
