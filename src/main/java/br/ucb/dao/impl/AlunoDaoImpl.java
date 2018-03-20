package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import br.ucb.dao.AlunoDao;
import br.ucb.entity.Aluno;


public class AlunoDaoImpl extends DaoGenericoImpl<Aluno, Integer> implements AlunoDao{

	@Override
	public List<Aluno> findBySearch(Aluno aluno) {
		String where = montarWhere(aluno);
		Query query = getManager().createQuery(" From Aluno a " + where);
		montarParametrs(query, aluno);
		return query.getResultList();
	}

	
	public void montarParametrs(Query query, Aluno aluno){
		if(aluno.getNome()!= null && !aluno.getNome().trim().isEmpty()){
			query.setParameter(1,"%"+ aluno.getNome() +"%");
		}
		
		if(aluno.getCelular()!= null && !aluno.getCelular().trim().isEmpty()){
			query.setParameter(2, "%"+ aluno.getCelular() +"%");
		}
		
		if(aluno.getCurso()!= null){
			query.setParameter(3, aluno.getCurso());
		}
		
		if(aluno.getDataNascimento()!= null){
			query.setParameter(4, aluno.getDataNascimento(), TemporalType.TIMESTAMP);
		}
		
		if(aluno.getMatricula()!= null && !aluno.getMatricula().trim().isEmpty()){
			query.setParameter(5, "%"+ aluno.getMatricula() +"%");
		}
		
		if(aluno.getSexo()!= '\0'){
			query.setParameter(6, aluno.getSexo());
		}
		
		if(aluno.getStatusAluno()!= null){
			query.setParameter(7, aluno.getStatusAluno());
		}
		
		if(aluno.getTelefoneFixo()!= null && !aluno.getTelefoneFixo().trim().isEmpty()){
			query.setParameter(8,"%"+ aluno.getTelefoneFixo()+"%");
		}
	}
	
	public String montarWhere(Aluno aluno){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(aluno.getNome() != null && !aluno.getNome().trim().isEmpty()){
			consulta.append(" and a.nome like ?1 ");
		}
		
		if(aluno.getCelular() != null && !aluno.getCelular().trim().isEmpty()){
			consulta.append(" and a.celular like ?2 ");
		}
		
		if(aluno.getCurso() != null){
			consulta.append(" and a.curso like ?3 ");
		}
		
		if(aluno.getDataNascimento() != null){
			consulta.append(" and a.dataNascimento like ?4 ");
		}
		
		if(aluno.getMatricula() != null && !aluno.getMatricula().trim().isEmpty()){
			consulta.append(" and a.matricula like ?5 ");
		}
		
		if(aluno.getSexo() != '\0'){
			consulta.append(" and a.sexo like ?6 ");
		}
		
		if(aluno.getStatusAluno()!= null){
			consulta.append(" and a.statusAluno like ?7 ");
		}
		
		if(aluno.getTelefoneFixo()!= null && !aluno.getTelefoneFixo().trim().isEmpty()){
			consulta.append(" and a.telefoneFixo like ?8 ");
		}
		return consulta.toString();
	}
	
	
	@Override
	public Aluno findById(Integer id) {
		Query query = getManager().createQuery(" from Aluno a  WHERE a.idAluno like ?1 ");
		query.setParameter(1,id);
		
		return (Aluno) query.getSingleResult();
	}
	
	
}
