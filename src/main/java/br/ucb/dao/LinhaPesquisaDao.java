package br.ucb.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.dao.impl.DaoGenericoImpl;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.util.JPAUtil;

public class LinhaPesquisaDao {
	
	public void cadastrar(LinhaPesquisa linhaPesquisa) {
		
		EntityManager em = JPAUtil.getEntityManaged();
		
		em.getTransaction().begin();
		em.merge(linhaPesquisa);
		em.getTransaction().commit();
		em.close();
		
		
		/*
		DaoGenerico<Serializable, LinhaPesquisa> linhaPesquisaDAO = new DaoGenericoImpl<>();
		linhaPesquisaDAO.save(linhaPesquisa);
		
		*/
		
	}

	public void alterar(LinhaPesquisa linhaPesquisa) {
		EntityManager em = JPAUtil.getEntityManaged();

		LinhaPesquisa alterarLinhaPesquisa = em.find(LinhaPesquisa.class, linhaPesquisa.getIdLinhaPesquisa());
		if (alterarLinhaPesquisa != null) {
			em.getTransaction().begin();
			alterarLinhaPesquisa.setDescricao(linhaPesquisa.getDescricao());
			em.getTransaction().commit();

		}

		em.close();
		
		/*
		DaoGenerico<Serializable, LinhaPesquisa> linhaPesquisaDAO = new DaoGenericoImpl<>();
		linhaPesquisaDAO.update(linhaPesquisa);*/
	}

	public void excluir(LinhaPesquisa linhaPesquisa) {
		
		EntityManager em = JPAUtil.getEntityManaged();

		LinhaPesquisa excluirLinhaPesquisa = em.find(LinhaPesquisa.class, linhaPesquisa.getIdLinhaPesquisa());
		if (excluirLinhaPesquisa != null) {
			em.getTransaction().begin();
			em.remove(excluirLinhaPesquisa);
			em.getTransaction().commit();

		}
		em.close();
		/*
		DaoGenerico<Serializable, LinhaPesquisa> linhaPesquisaDAO = new DaoGenericoImpl<>();
		linhaPesquisaDAO.remove(linhaPesquisa);*/
	}

	public List<LinhaPesquisa> buscaTodosStatus() {

		
		 EntityManager em = JPAUtil.getEntityManaged();
		 List<LinhaPesquisa> linhasPesquisa = em.createQuery("from LinhaPesquisa", LinhaPesquisa.class).getResultList();

		return  linhasPesquisa;
	}

	public List<LinhaPesquisa> buscaStatusPorPesquisa(String descricao) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<LinhaPesquisa> linhasPesquisa = em
				.createQuery("from LinhaPesquisa where descricao like '%" + descricao + "%'", LinhaPesquisa.class)
				.getResultList();

		return linhasPesquisa;
	}

}
