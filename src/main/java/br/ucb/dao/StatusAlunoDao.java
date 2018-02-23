package br.ucb.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.dao.impl.DaoGenericoImpl;
import br.ucb.entity.StatusAluno;
import br.ucb.util.JPAUtil;

public class StatusAlunoDao{

	public void cadastrar(StatusAluno statusAluno) {
		
		EntityManager em = JPAUtil.getEntityManaged();
		
		em.getTransaction().begin();
		em.merge(statusAluno);
		em.getTransaction().commit();
		em.close();
		
		
		/*
		DaoGenerico<Serializable, StatusAluno> statusAlunoDAO = new DaoGenericoImpl<>();
		statusAlunoDAO.save(statusAluno);
		*/
		
		
	}

	public void alterar(StatusAluno statusAluno) {
		EntityManager em = JPAUtil.getEntityManaged();

		StatusAluno alterarStatus = em.find(StatusAluno.class, statusAluno.getIdStatusAluno());
		if (alterarStatus != null) {
			em.getTransaction().begin();
			alterarStatus.setDescricao(statusAluno.getDescricao());
			em.getTransaction().commit();

		}

		em.close();
		
		/*
		DaoGenerico<Serializable, StatusAluno> statusAlunoDAO = new DaoGenericoImpl<>();
		statusAlunoDAO.update(statusAluno);
		*/
	}

	public void excluir(StatusAluno statusAluno) {
		
		EntityManager em = JPAUtil.getEntityManaged();

		StatusAluno excluirStatus = em.find(StatusAluno.class, statusAluno.getIdStatusAluno());
		if (excluirStatus != null) {
			em.getTransaction().begin();
			em.remove(excluirStatus);
			em.getTransaction().commit();

		}
		em.close();
		/*
		DaoGenerico<Serializable, StatusAluno> statusAlunoDAO = new DaoGenericoImpl<>();
		statusAlunoDAO.remove(statusAluno);
		*/
	}

	public List<StatusAluno> buscaTodosStatus() {

		
		 EntityManager em = JPAUtil.getEntityManaged();
		 List<StatusAluno> variosStatus = em.createQuery("from StatusAluno", StatusAluno.class).getResultList();

		return  variosStatus;
	}

	public List<StatusAluno> buscaStatusPorPesquisa(String descricao) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<StatusAluno> variosStatus = em
				.createQuery("from StatusAluno where descricao like '%" + descricao + "%'", StatusAluno.class)
				.getResultList();

		return variosStatus;
	}

	
}
