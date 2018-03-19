package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.ComentarioDao;
import br.ucb.entity.Comentario;
import br.ucb.util.StringUtil;

public class ComentarioDaoImpl extends DaoGenericoImpl<Comentario, Integer> implements ComentarioDao {


	@Override
	public List<Comentario> find(Comentario comentario) {
		String where = montarWhere(comentario);
		Query query = getManager().createQuery(" From Comentario t " + where + " order by date(t.dataCadastro) desc ");
		montarParametrs(query, comentario);
		return query.getResultList();	
	}
	
	public void montarParametrs(Query query, Comentario comentario){
		if(StringUtil.isNotNullIsNotEmpty(comentario.getTitulo())){
			query.setParameter(1, "%"+ comentario.getTitulo() + "%");
		}
		if(comentario.getDataCadastro() != null){
			query.setParameter(2, "%"+ comentario.getDataCadastro() + "%");
		}
	}
	
	public String montarWhere(Comentario comentario){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(StringUtil.isNotNullIsNotEmpty(comentario.getTitulo())){
			consulta.append(" and t.titulo like ?1 ");
		}
		if(comentario.getDataCadastro() != null){
			consulta.append(" and date(t.dataCadastro) = date(?2) ");
		}
		return consulta.toString();
	}

}
