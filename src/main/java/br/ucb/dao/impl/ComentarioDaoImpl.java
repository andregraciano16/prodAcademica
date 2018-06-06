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
		int contador = 0;
		if(StringUtil.isNotNullIsNotEmpty(comentario.getTitulo())){
			contador++;
			query.setParameter(contador, "%"+ comentario.getTitulo() + "%");
		}
		if(comentario.getDataCadastro() != null){
			contador++;
			query.setParameter(contador, comentario.getDataCadastro());
		}
	}
	
	public String montarWhere(Comentario comentario){
		int contador = 0;
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(StringUtil.isNotNullIsNotEmpty(comentario.getTitulo())){
			contador++;
			consulta.append(" and t.titulo like ?" + contador);
		}
		if(comentario.getDataCadastro() != null){
			contador++;
			consulta.append(" and DATE(t.dataCadastro) = DATE_FORMAT(?"+contador+",'%Y-%m-%d') ");
		}
		return consulta.toString();
	}

}
