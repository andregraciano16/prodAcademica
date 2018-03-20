package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.TipoProjetoDao;
import br.ucb.entity.TipoProjeto;

public class TipoProjetoDaoImpl extends DaoGenericoImpl<TipoProjeto, Integer> implements TipoProjetoDao{

	
	@Override
	public TipoProjeto findById(Integer id) {
		
		Query query = getManager().createQuery(" from TipoProjeto tp  WHERE tp.idTipoProjeto like ?1 ");
		query.setParameter(1,id);
		
		return (TipoProjeto) query.getSingleResult();
	}
	
	public List<TipoProjeto> findBySearch(TipoProjeto tipoProjeto) {
		String where = montarWhere(tipoProjeto.getTipo(), tipoProjeto.getDescricao());
		Query query = getManager().createQuery(" From TipoProjeto  " + where);
		montarParametrs(query,tipoProjeto.getTipo(), tipoProjeto.getDescricao());
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
			consulta.append(" and tipo like ?1 ");
		}
		if(descricao != null && !descricao.isEmpty()){
			consulta.append(" and descricao like ?2 ");
		}
		return consulta.toString();
	}

	

}
