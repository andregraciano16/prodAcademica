package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.MateriaDao;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Materia;


public class MateriaDaoImpl extends DaoGenericoImpl<Materia, Integer> implements MateriaDao{
	
	
	public List<Materia> findBySearch(Materia materia) {
		String where = montarWhere(materia.getDescricao(), materia.getLinhaPesquisa());
		Query query = getManager().createQuery(" From Materia m " + where);
		montarParametrs(query, materia.getDescricao(), materia.getLinhaPesquisa());
		return query.getResultList();	
	}
	
	public void montarParametrs(Query query, String descricao, LinhaPesquisa linhaPesquisa){
		if(descricao != null && !descricao.isEmpty()){
			query.setParameter(1, "%"+ descricao + "%");
		}
		if(linhaPesquisa != null){
			query.setParameter(2,linhaPesquisa);
		}
	}
	
	public String montarWhere(String descricao, LinhaPesquisa linhaPesquisa){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(descricao != null && !descricao.isEmpty()){
			consulta.append(" and m.descricao like ?1 ");
		}
		if(linhaPesquisa != null){
			consulta.append(" and m.linhaPesquisa like ?2 ");
		}
		return consulta.toString();
	}


}
