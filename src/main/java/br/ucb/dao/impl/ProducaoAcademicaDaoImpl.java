package br.ucb.dao.impl;

import javax.persistence.Query;

import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.entity.ProducaoAcademica;


public class ProducaoAcademicaDaoImpl extends DaoGenericoImpl<ProducaoAcademica, Integer> implements ProducaoAcademicaDao {

	@Override
	public ProducaoAcademica findById(Integer id) {
		Query query = getManager().createQuery(" from ProducaoAcademica st  WHERE st.idProducaoAcademica like ?1 ");
		query.setParameter(1,id);
		return (ProducaoAcademica) query.getSingleResult();
	}

	@Override
	public ProducaoAcademica findByProdAc(ProducaoAcademica pa) {
		String where = montarWhere(pa);
		Query query = getManager().createQuery(" From ProducaoAcademica t " + where);
		montarParametrs(query, pa);
		return (ProducaoAcademica) query.getSingleResult();
	}
	
	public void montarParametrs(Query query, ProducaoAcademica pa){
		if(pa.getDescricao() != null && !pa.getDescricao().isEmpty()){
			query.setParameter(1, pa.getDescricao());
		}
		if(pa.getTitulo() != null && !pa.getTitulo().isEmpty()){
			query.setParameter(2, pa.getTitulo());
		}
		if(pa.getDataCadastro() != null){
			query.setParameter(3, pa.getDataCadastro());
		}
	}
	
	public String montarWhere(ProducaoAcademica pa){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(pa.getDescricao() != null && !pa.getDescricao().isEmpty()){
			consulta.append(" and t.descricao like ?1 ");
		}
		if(pa.getTitulo() != null && !pa.getTitulo().isEmpty()){
			consulta.append(" and t.titulo like ?2 ");
		}
		if(pa.getDataCadastro() != null){
			consulta.append(" and t.dataCadastro");
		}
		return consulta.toString();
	}

}
