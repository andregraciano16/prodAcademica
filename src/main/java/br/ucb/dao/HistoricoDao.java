package br.ucb.dao;


import java.util.List;

import br.ucb.entity.Historico;


public interface HistoricoDao extends DaoGenerico<Historico, Integer>{

	List<Historico> findBySearch(Historico historico);
	
	List<Historico>listOrder();

}
