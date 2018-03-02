package br.ucb.dao;


import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.entity.Materia;
import br.ucb.util.JPAUtil;

public class MateriaDao {
	
	public String cadastrar(Materia materia) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {
			em.getTransaction().begin();
			em.merge(materia);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Cadastrado com sucesso!";

		/*
		 * DaoGenerico<Serializable, Materia> materiaDAO = new
		 * DaoGenericoImpl<>(); materiaDAO.save(materia);
		 * 
		 */

	}

	public String alterar(Materia materia) {
		EntityManager em = JPAUtil.getEntityManaged();

		try {
			Materia alterarMateria = em.find(Materia.class, materia.getIdMateria());
			if (alterarMateria != null) {
				em.getTransaction().begin();
				alterarMateria.setDescricao(materia.getDescricao());
				alterarMateria.setLinhaPesquisa(materia.getLinhaPesquisa());
				em.getTransaction().commit();
			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Atualizado com sucesso!";

		/*
		 * DaoGenerico<Serializable, Materia> materiaDAO = new
		 * DaoGenericoImpl<>(); materiaDAO.update(materia);
		 */
	}

	public String excluir(Materia materia) {

		EntityManager em = JPAUtil.getEntityManaged();
		
		try {
			Materia excluirMateria = em.find(Materia.class, materia.getIdMateria());
			if (excluirMateria != null) {
				em.getTransaction().begin();
				em.remove(excluirMateria);
				em.getTransaction().commit();

			}
			em.close();
		} catch (Exception ex) {
			
			return ex.getMessage();
		}
		return "Excluído com sucesso!";

		/*
		 * DaoGenerico<Serializable, Materia> materiaDAO = new
		 * DaoGenericoImpl<>(); materiaDAO.remove(materia);
		 */
	}

	public List<Materia> buscaTodos() {

		EntityManager em = JPAUtil.getEntityManaged();
		List<Materia> materias = em.createQuery("from Materia", Materia.class).getResultList();

		return materias;
	}

	public List<Materia> buscaMateriaPorPesquisa(String descricao) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<Materia> materias = em
				.createQuery("from Materia where descricao like '%" + descricao + "%'", Materia.class).getResultList();

		return materias;
	}

}
