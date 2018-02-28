package br.ucb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.entity.StatusAluno;
import br.ucb.util.JPAUtil;

public class StatusAlunoDao {

	public String cadastrar(StatusAluno statusAluno) {

		EntityManager em = JPAUtil.getEntityManaged();
		try {
			em.getTransaction().begin();
			em.merge(statusAluno);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Cadastrado com sucesso!";

		/*
		 * DaoGenerico<Serializable, StatusAluno> statusAlunoDAO = new
		 * DaoGenericoImpl<>(); statusAlunoDAO.save(statusAluno);
		 */

	}

	public String alterar(StatusAluno statusAluno) {
		EntityManager em = JPAUtil.getEntityManaged();

		try {
			StatusAluno alterarStatus = em.find(StatusAluno.class, statusAluno.getId_statusAluno());
			if (alterarStatus != null) {
				em.getTransaction().begin();
				alterarStatus.setDescricao(statusAluno.getDescricao());
				em.getTransaction().commit();
			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Atualizado com sucesso!";

		/*
		 * DaoGenerico<Serializable, StatusAluno> statusAlunoDAO = new
		 * DaoGenericoImpl<>(); statusAlunoDAO.update(statusAluno);
		 */
	}

	public String excluir(StatusAluno statusAluno) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {
			StatusAluno excluirStatus = em.find(StatusAluno.class, statusAluno.getId_statusAluno());
			if (excluirStatus != null) {
				em.getTransaction().begin();
				em.remove(excluirStatus);
				em.getTransaction().commit();
			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Excluído com sucesso!";

		/*
		 * DaoGenerico<Serializable, StatusAluno> statusAlunoDAO = new
		 * DaoGenericoImpl<>(); statusAlunoDAO.remove(statusAluno);
		 */
	}

	public List<StatusAluno> buscaTodosStatus() {

		EntityManager em = JPAUtil.getEntityManaged();
		List<StatusAluno> variosStatus = em.createQuery("from StatusAluno", StatusAluno.class).getResultList();

		return variosStatus;
	}

	public List<StatusAluno> buscaStatusPorPesquisa(String descricao) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<StatusAluno> variosStatus = em
				.createQuery("from StatusAluno where descricao like '%" + descricao + "%'", StatusAluno.class)
				.getResultList();

		return variosStatus;
	}

}
