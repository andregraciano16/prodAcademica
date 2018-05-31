package br.ucb.dao;

import java.util.List;

import br.ucb.entity.Autor;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.filtro.ProdAcFiltro;

public interface AutorDao extends DaoGenerico<Autor, Integer> {

	List<ProducaoAcademica> findProducaoByFiltro(ProdAcFiltro filtro);
	
	List<Autor> findAutorByIDProducao(Integer id);
	
}
