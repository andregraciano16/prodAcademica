package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.ProjetoDao;
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
		
		if(projeto.getDadosOficiais() != null && !projeto.getDadosOficiais().trim().isEmpty()){
			query.setParameter(3, "%"+ projeto.getDadosOficiais() + "%");
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
		
		if(projeto.getDadosOficiais() != null && !projeto.getDadosOficiais().trim().isEmpty()){
			consulta.append(" and p.dadosOficiais like ?3 ");
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
		
		return consulta.toString();
	}


}