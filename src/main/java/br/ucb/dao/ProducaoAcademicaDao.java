package br.ucb.dao;

import br.ucb.entity.ProducaoAcademica;

public interface ProducaoAcademicaDao extends DaoGenerico<ProducaoAcademica, Integer> {

	ProducaoAcademica findById(Integer id);
	
	ProducaoAcademica findByProdAc(ProducaoAcademica pa);

}
