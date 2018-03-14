
package br.ucb.dao;

import java.util.List;
import br.ucb.entity.LinhaPesquisa;


public interface LinhaPesquisaDao extends DaoGenerico<LinhaPesquisa, Integer>{

	public List<LinhaPesquisa> findByDescricao(String descricao);
	public LinhaPesquisa findById(Integer id);

}

