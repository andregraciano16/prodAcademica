package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.ProjetoDao;
import br.ucb.entity.Aluno;
import br.ucb.entity.Docente;
import br.ucb.entity.Projeto;



public class ProjetoDaoImpl extends DaoGenericoImpl<Projeto, Integer> implements ProjetoDao{

	@Override
	public Projeto findById(Integer id) {
		
		Query query = getManager().createQuery(" from Projeto p WHERE p.idProjeto like ?1 ");
		query.setParameter(1,id);
		
		return (Projeto) query.getSingleResult();
	}
	
	@Override
	public List<Projeto> findBySearch(Projeto projeto) {
		String where = montarWhere(projeto);
		Query query = getManager().createQuery(" From Projeto p " + where);
		montarParametrs(query, projeto);
		return query.getResultList();	
	}

	private void montarParametrs(Query query, Projeto projeto) {
		if(projeto.getNome() != null && !projeto.getNome().trim().isEmpty()){
			query.setParameter(1, "%"+ projeto.getNome() + "%");
		}
		
		if(projeto.getDescricao() != null && !projeto.getDescricao().trim().isEmpty()){
			query.setParameter(2, "%"+ projeto.getDescricao() + "%");
		}
		
		if(projeto.getOrgaoFinanciador() != null && !projeto.getOrgaoFinanciador().trim().isEmpty()){
			query.setParameter(4, "%"+ projeto.getNome() + "%");
		}
		
		if(projeto.getLinhaPesquisa() != null){
			query.setParameter(5,projeto.getLinhaPesquisa());
		}
		
		if(projeto.getTipoProjeto() != null){
			query.setParameter(6, projeto.getTipoProjeto());
		}
		
		if(projeto.getStatusProjeto() != null){
			query.setParameter(7, projeto.getStatusProjeto());
		}
		
		if(projeto.getDocenteResponsavel() != null){
			query.setParameter(8, projeto.getDocenteResponsavel());
		}
		
		if(projeto.getExternoResponsavel() != null){
			query.setParameter(9, projeto.getExternoResponsavel());
		}
		
		
	}

	private String montarWhere(Projeto projeto) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(projeto.getNome() != null && !projeto.getNome().trim().isEmpty()){
			consulta.append(" and p.nome like ?1 ");
		}
		
		if(projeto.getDescricao() != null && !projeto.getDescricao().trim().isEmpty()){
			consulta.append(" and p.descricao like ?2 ");
		}
		
		if(projeto.getOrgaoFinanciador() != null && !projeto.getOrgaoFinanciador().trim().isEmpty()){
			consulta.append(" and p.orgaoFinanciador like ?4 ");
		}
		
		if(projeto.getLinhaPesquisa() != null){
			consulta.append(" and p.linhaPesquisa like ?5 ");
		}
		
		if(projeto.getTipoProjeto() != null){
			consulta.append(" and p.tipoProjeto like ?6 ");
		}
		
		if(projeto.getStatusProjeto() != null){
			consulta.append(" and p.statusProjeto like ?7 ");
		}
		
		if(projeto.getDocenteResponsavel() != null){
			consulta.append(" and p.docenteResponsavel like ?8 ");
		}
		
		if(projeto.getExternoResponsavel() != null){
			consulta.append(" and p.externoResponsavel like ?9 ");
		}
		
		return consulta.toString();
	}

	@Override
	public Projeto find(Projeto projeto) {
		Query query = getManager().createQuery(" from Projeto p  WHERE p.dataInicio like ?1  and p.descricao like ?2 and p.horasDedicadasSemana like ?3 and p.nome like ?4 and p.orgaoFinanciador like ?5"
				+ " and tipoProjeto = ?6");
		query.setParameter(1, projeto.getDataInicio());
		query.setParameter(2, projeto.getDescricao());
		query.setParameter(3, projeto.getHorasDedicadasSemana());
		query.setParameter(4, projeto.getNome());
		query.setParameter(5, projeto.getOrgaoFinanciador());
		query.setParameter(6, projeto.getTipoProjeto());
		
		return (Projeto) query.getResultList().get(0);
	}

	public List<Projeto> findByAutorDocente(Docente docente){
		
		List<Projeto> projetos = getManager().createQuery(" from Projeto p WHERE p.autorDocente = ?1 OR p.docenteResponsavel = ?1").setParameter(1, docente).getResultList();
		return projetos;
	}
	
	public List<Projeto> findByAutorAluno(Aluno aluno){
		List<Projeto> projetos = getManager().createQuery(" from Projeto p WHERE p.autorAluno = ?1").setParameter(1, aluno).getResultList();
		return projetos;
	}
	
	
}
