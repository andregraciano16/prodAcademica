package br.ucb.dao;

import java.util.List;

import br.ucb.entity.StatusProducao;

public interface StatusProducaoDao extends DaoGenerico<StatusProducao, Integer> {

<<<<<<< HEAD
	List<StatusProducao>  findByDescricaoAndTipo(String tipo, String descricao);
=======
	List<StatusProducao> findByDescricaoAndTipo(String tipo, String descricao);
>>>>>>> branch 'master' of https://github.com/andregraciano16/prodAcademica.git

}
