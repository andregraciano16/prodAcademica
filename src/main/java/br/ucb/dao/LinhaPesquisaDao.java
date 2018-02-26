package br.ucb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.entity.LinhaPesquisa;
import br.ucb.util.JPAUtil;

public class LinhaPesquisaDao {

	public String cadastrar(LinhaPesquisa linhaPesquisa) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {
			em.getTransaction().begin();
			em.merge(linhaPesquisa);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Cadastrado com sucesso!";

		/*
		 * DaoGenerico<Serializable, LinhaPesquisa> linhaPesquisaDAO = new
		 * DaoGenericoImpl<>(); linhaPesquisaDAO.save(linhaPesquisa);
		 * 
		 */

	}

	public String alterar(LinhaPesquisa linhaPesquisa) {
		EntityManager em = JPAUtil.getEntityManaged();

		try {
			LinhaPesquisa alterarLinhaPesquisa = em.find(LinhaPesquisa.class, linhaPesquisa.getIdLinhaPesquisa());
			if (alterarLinhaPesquisa != null) {
				em.getTransaction().begin();
				alterarLinhaPesquisa.setDescricao(linhaPesquisa.getDescricao());
				em.getTransaction().commit();

			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Atualizado com sucesso!";

		/*
		 * DaoGenerico<Serializable, LinhaPesquisa> linhaPesquisaDAO = new
		 * DaoGenericoImpl<>(); linhaPesquisaDAO.update(linhaPesquisa);
		 */
	}

	public String excluir(LinhaPesquisa linhaPesquisa) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {

			LinhaPesquisa excluirLinhaPesquisa = em.find(LinhaPesquisa.class, linhaPesquisa.getIdLinhaPesquisa());
			if (excluirLinhaPesquisa != null) {
				em.getTransaction().begin();
				em.remove(excluirLinhaPesquisa);
				em.getTransaction().commit();
				em.close();
			}

		} catch (Exception ex) {			
			return ex.getMessage();
		}
		return "Excluido com sucesso.";

		/*
		 * DaoGenerico<Serializable, LinhaPesquisa> linhaPesquisaDAO = new
		 * DaoGenericoImpl<>(); linhaPesquisaDAO.remove(linhaPesquisa);
		 */
	}

	public List<LinhaPesquisa> buscaTodos() {

		EntityManager em = JPAUtil.getEntityManaged();
		List<LinhaPesquisa> linhasPesquisa = em.createQuery("from LinhaPesquisa", LinhaPesquisa.class).getResultList();

		return linhasPesquisa;
	}

	public List<LinhaPesquisa> buscaLinhaPorPesquisa(String descricao) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<LinhaPesquisa> linhasPesquisa = em
				.createQuery("from LinhaPesquisa where descricao like '%" + descricao + "%'", LinhaPesquisa.class)
				.getResultList();

		return linhasPesquisa;
	}

	public LinhaPesquisa buscaLinhaPorId(int id) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<LinhaPesquisa> linhasPesquisa = em
				.createQuery("from LinhaPesquisa where idLinhaPesquisa = " + id, LinhaPesquisa.class).getResultList();

		return linhasPesquisa.get(0);
	}

}
