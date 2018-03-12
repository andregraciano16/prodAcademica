package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.StatusProducaoDao;
import br.ucb.entity.StatusProducao;

public class StatusProducaoDaoImpl extends DaoGenericoImpl<StatusProducao, Integer> implements StatusProducaoDao{

	@Override
	public List<StatusProducao> findByDescricaoAndTipo(String tipo, String descricao) {
		String where = montarWhere(tipo, descricao);
		Query query = getManager().createQuery(" From StatusProducao t " + where);
		montarParametrs(query, tipo, descricao);
		return query.getResultList();	
	}
	
	public void montarParametrs(Query query, String tipo, String descricao){
		if(tipo != null && !tipo.isEmpty()){
			query.setParameter(1, "%"+ tipo + "%");
		}
		if(descricao != null && !descricao.isEmpty()){
			query.setParameter(2, "%"+ descricao + "%");
		}
	}
	
	public String montarWhere(String tipo, String descricao){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(tipo != null && !tipo.isEmpty()){
			consulta.append(" and t.tipo like ?1 ");
		}
		if(descricao != null && !descricao.isEmpty()){
			consulta.append(" and t.descricao like ?2 ");
		}
		return consulta.toString();
	}
	
}
