package br.ucb.dao.impl;

import java.util.Date;
import java.util.List;
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

		return consulta.toString();
	}

		public List<Object[]> listSimpleTipo() {


		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select pa.titulo, pa.tipoProducao.tipo from ProducaoAcademica pa order by  pa.tipoProducao.tipo asc")
				.getResultList();

	
		return resultados;

	}
	
	
	public List<Object[]> listSimpleLinha() {


		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select pa.titulo, pa.linhaPesquisa.descricao from ProducaoAcademica pa order by  pa.linhaPesquisa.descricao asc")
				.getResultList();

	
		return resultados;

	}
	
	
	public List<Object[]> listSimpleQualis(){
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select year(pa.dataCadastro), pa.conceitoQualis, pa.dataCadastro from ProducaoAcademica pa order by pa.dataCadastro asc")
				.getResultList();

	
		return resultados;

		
	}
	
    public List<Date> listSimpleDatas(){
		
		List<Date> resultados = getManager()
				.createQuery(
						"select pa.dataCadastro from ProducaoAcademica pa order by pa.dataCadastro asc", Date.class)
				.getResultList();

	
		return resultados;

		
	}
}
