package br.ucb.dao;


import java.util.Date;
import java.util.List;

import br.ucb.entity.Historico;


public interface HistoricoDao extends DaoGenerico<Historico, Integer>{

	List<Historico> findByData(Date dataAlteracao);

	
}
