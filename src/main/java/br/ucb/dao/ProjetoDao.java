package br.ucb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.entity.Projeto;
import br.ucb.util.JPAUtil;

public class ProjetoDao {
	
	public String cadastrar(Projeto projeto) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {
			em.getTransaction().begin();
			em.merge(projeto);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Cadastrado com sucesso!";

		/*
		 * DaoGenerico<Serializable, Projeto> projetoDAO = new
		 * DaoGenericoImpl<>(); projetoDAO.save(projeto);
		 * 
		 */

	}

	public String alterar(Projeto projeto) {
		EntityManager em = JPAUtil.getEntityManaged();

		try {
			Projeto alterarProjeto = em.find(Projeto.class, projeto.getIdProjeto());
			if (alterarProjeto != null) {
				em.getTransaction().begin();
				alterarProjeto.setNome(projeto.getNome());
				alterarProjeto.setDescricao(projeto.getDescricao());
				alterarProjeto.setOrgaoFinanciador(projeto.getOrgaoFinanciador());
				alterarProjeto.setDadosOficiais(projeto.getDadosOficiais());
				alterarProjeto.setTipoProjeto(projeto.getTipoProjeto());
				alterarProjeto.setLinhaPesquisa(projeto.getLinhaPesquisa());
				em.getTransaction().commit();
			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Atualizado com sucesso!";

		/*
		 * DaoGenerico<Serializable, Projeto> projetoDAO = new
		 * DaoGenericoImpl<>(); projetoDAO.update(projeto);
		 */
	}

	public String excluir(Projeto projeto) {

		EntityManager em = JPAUtil.getEntityManaged();
		
		try {
			Projeto excluirProjeto = em.find(Projeto.class, projeto.getIdProjeto());
			if (excluirProjeto != null) {
				em.getTransaction().begin();
				em.remove(excluirProjeto);
				em.getTransaction().commit();

			}
			em.close();
		} catch (Exception ex) {
			
			return ex.getMessage();
		}
		return "Excluído com sucesso!";

		/*
		 * DaoGenerico<Serializable, Projeto> projetoDAO = new
		 * DaoGenericoImpl<>(); projetoDAO.remove(projeto);
		 */
	}

	public List<Projeto> buscaTodos() {

		EntityManager em = JPAUtil.getEntityManaged();
		List<Projeto> projetos = em.createQuery("from Projeto", Projeto.class).getResultList();

		return projetos;
	}

	public List<Projeto> buscaProjetoPorPesquisa(String descricao) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<Projeto> projetos = em
				.createQuery("from Projeto where descricao like '%" + descricao + "%'", Projeto.class).getResultList();

		return projetos;
	}

}

