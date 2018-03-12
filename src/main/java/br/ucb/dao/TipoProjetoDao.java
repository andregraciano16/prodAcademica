package br.ucb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ucb.entity.TipoProjeto;
import br.ucb.util.JPAUtil;

public class TipoProjetoDao {

	public String cadastrar(TipoProjeto tipoProjeto) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {
			em.getTransaction().begin();
			em.merge(tipoProjeto);
			em.getTransaction().commit();
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Cadastrado com sucesso!";

		/*
		 * DaoGenerico<Serializable, TipoProjeto> tipoProjetoDAO = new
		 * DaoGenericoImpl<>(); tipoProjetoDAO.save(tipoProjeto);
		 * 
		 */

	}

	public String alterar(TipoProjeto tipoProjeto) {
		EntityManager em = JPAUtil.getEntityManaged();

		try {
			TipoProjeto alterarTipoProjeto = em.find(TipoProjeto.class, tipoProjeto.getIdTipoProjeto());
			if (alterarTipoProjeto != null) {
				em.getTransaction().begin();
				alterarTipoProjeto.setTipo(tipoProjeto.getTipo());
				alterarTipoProjeto.setDescricao(tipoProjeto.getDescricao());
				em.getTransaction().commit();
			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Atualizado com sucesso!";

		/*
		 * DaoGenerico<Serializable, TipoProjeto> tipoProjetoDAO = new
		 * DaoGenericoImpl<>(); tipoProjetoDAO.update(tipoProjeto);
		 */
	}

	public String excluir(TipoProjeto tipoProjeto) {

		EntityManager em = JPAUtil.getEntityManaged();

		try {
			TipoProjeto excluirTipoProjeto = em.find(TipoProjeto.class, tipoProjeto.getIdTipoProjeto());
			if (excluirTipoProjeto != null) {
				em.getTransaction().begin();
				em.remove(excluirTipoProjeto);
				em.getTransaction().commit();
			}
			em.close();
		} catch (Exception ex) {
			return ex.getMessage();
		}
		return "Excluído com sucesso!";

		/*
		 * DaoGenerico<Serializable, TipoProjeto> tipoProjetoDAO = new
		 * DaoGenericoImpl<>(); tipoProjetoDAO.remove(tipoProjeto);
		 */
	}

	public List<TipoProjeto> buscaTodos() {

		EntityManager em = JPAUtil.getEntityManaged();
		List<TipoProjeto> tiposProjeto = em.createQuery("from TipoProjeto", TipoProjeto.class).getResultList();

		return tiposProjeto;
	}

	public List<TipoProjeto> buscaPorPesquisa(TipoProjeto tipoProjeto) {

		EntityManager em = JPAUtil.getEntityManaged();

		List<TipoProjeto> tiposProjeto = em
				.createQuery("from TipoProjeto where descricao like '%" + tipoProjeto.getDescricao() + "%'",
						TipoProjeto.class)
				.getResultList();

		return tiposProjeto;
	}

	public TipoProjeto buscaTipoPorId(int id) {
		EntityManager em = JPAUtil.getEntityManaged();

		List<TipoProjeto> tiposProjeto = em
				.createQuery("from TipoProjeto where idTipoProjeto = " + id, TipoProjeto.class).getResultList();

		return tiposProjeto.get(0);
	}

}

