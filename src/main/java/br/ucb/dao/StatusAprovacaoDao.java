package br.ucb.dao;

import java.util.List;

import br.ucb.entity.StatusAprovacao;

public interface StatusAprovacaoDao extends DaoGenerico<StatusAprovacao, Integer> {

	List<StatusAprovacao> findByDescricao(String descricao);

	StatusAprovacao findById(Integer integer);

}
