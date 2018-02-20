package br.ucb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ucb.entity.StatusAluno;
import br.ucb.util.JPAUtil;

public class StatusAlunoDao {
	
	public void cadastrar(StatusAluno statusAluno){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		EntityManager em = emf.createEntityManager();	
		
		em.getTransaction().begin();
		em.merge(statusAluno);
		em.getTransaction().commit();
		em.close();
	}
	
	public void alterar(StatusAluno statusAluno){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		EntityManager em = emf.createEntityManager();	
		
		StatusAluno alterarStatus = em.find(StatusAluno.class, statusAluno.getIdStatusAluno());
		if(alterarStatus != null){
			em.getTransaction().begin();
			alterarStatus.setDescricao(statusAluno.getDescricao());
			em.getTransaction().commit();
			
		}
		
		em.close();
	}
	
	
	public void excluir(StatusAluno statusAluno){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		EntityManager em = emf.createEntityManager();	
		
		StatusAluno excluirStatus = em.find(StatusAluno.class, statusAluno.getIdStatusAluno());
		if(excluirStatus != null){
			em.getTransaction().begin();
			em.remove(excluirStatus);
			em.getTransaction().commit();
			
		}
		
		em.close();
	}
	
	public List<StatusAluno> buscaTodosStatus(){
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		//EntityManager em = emf.createEntityManager();
		EntityManager em = JPAUtil.getEntityManaged();
		
		List<StatusAluno> variosStatus = em.createQuery("from StatusAluno",StatusAluno.class).getResultList();
		
		return variosStatus;
	}
	
	
public List<StatusAluno> buscaStatusPorPesquisa(String descricao){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prodAc");
		EntityManager em = emf.createEntityManager();
		
		List<StatusAluno> variosStatus = em.createQuery("from StatusAluno where descricao like '%"+descricao+"%'" ,StatusAluno.class).getResultList();
		
		return variosStatus;
	}
}
