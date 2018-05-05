package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.entity.ProducaoAcademica;

public class ProducaoAcademicaDaoImpl extends DaoGenericoImpl<ProducaoAcademica, Integer>
		implements ProducaoAcademicaDao {

	@Override
	public ProducaoAcademica findById(Integer id) {
		Query query = getManager().createQuery(" from ProducaoAcademica st  WHERE st.idProducaoAcademica like ?1 ");
		query.setParameter(1, id);

		return (ProducaoAcademica) query.getSingleResult();
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
	
public List<Object[]> listSimpleDatas(){
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select pa.dataCadastro from ProducaoAcademica pa order by pa.dataCadastro asc")
				.getResultList();

	
		return resultados;

		
	}

}
