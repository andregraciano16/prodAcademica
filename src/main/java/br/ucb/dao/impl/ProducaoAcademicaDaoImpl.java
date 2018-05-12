package br.ucb.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

import br.ucb.VO.AprovacaoProducaoVO;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.entity.StatusProducao;

public class ProducaoAcademicaDaoImpl extends DaoGenericoImpl<ProducaoAcademica, Integer>
		implements ProducaoAcademicaDao {

	@Override
	public ProducaoAcademica findById(Integer id) {
		Query query = getManager().createQuery(" from ProducaoAcademica st  WHERE st.idProducaoAcademica like ?1 ");
		query.setParameter(1, id);
		return (ProducaoAcademica) query.getSingleResult();
	}

	@Override
	public ProducaoAcademica findByProdAc(ProducaoAcademica pa) {
		String where = montarWhere(pa);
		Query query = getManager().createQuery(" From ProducaoAcademica t " + where);
		montarParametrs(query, pa);
		return (ProducaoAcademica) query.getSingleResult();
	}

	public void montarParametrs(Query query, ProducaoAcademica pa) {
		if (pa.getDescricao() != null && !pa.getDescricao().isEmpty()) {
			query.setParameter(1, pa.getDescricao());
		}
		if (pa.getTitulo() != null && !pa.getTitulo().isEmpty()) {
			query.setParameter(2, pa.getTitulo());
		}
		if (pa.getDataCadastro() != null) {
			query.setParameter(3, pa.getDataCadastro());
		}
	}

	public String montarWhere(ProducaoAcademica pa) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if (pa.getDescricao() != null && !pa.getDescricao().isEmpty()) {
			consulta.append(" and t.descricao like ?1 ");
		}
		if (pa.getTitulo() != null && !pa.getTitulo().isEmpty()) {
			consulta.append(" and t.titulo like ?2 ");
		}
		if (pa.getDataCadastro() != null) {
			consulta.append(" and t.dataCadastro");
		}
		return consulta.toString();
	}

	public List<Object[]> listSimpleTipo() {

		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select pa.titulo, pa.tipoProducao.tipo from ProducaoAcademica pa order by  pa.tipoProducao.tipo asc")
				.getResultList();

		return resultados;

	}

	public List<Object[]> listSimpleLinha() {

		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select pa.titulo, pa.linhaPesquisa.descricao from ProducaoAcademica pa order by  pa.linhaPesquisa.descricao asc")
				.getResultList();

		return resultados;

	}

	public List<Object[]> listSimpleQualis() {

		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select year(pa.dataCadastro), pa.conceitoQualis, pa.dataCadastro from ProducaoAcademica pa order by year(pa.dataCadastro) asc")
				.getResultList();

		return resultados;

	}

	public List<Date> listSimpleDatas() {

		List<Date> resultados = getManager()
				.createQuery("select pa.dataCadastro from ProducaoAcademica pa order by pa.dataCadastro asc",
						Date.class)
				.getResultList();

		return resultados;

	}

	public List<Object[]> listSimpleQualisFiltro(String anoInicio, String anoFim) {

		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select year(pa.dataCadastro), pa.conceitoQualis, pa.dataCadastro from ProducaoAcademica pa where year(pa.dataCadastro) between ?1 and ?2 order by year(pa.dataCadastro) asc")
				.setParameter(1, Integer.valueOf(anoInicio)).setParameter(2, Integer.valueOf(anoFim)).getResultList();

		return resultados;

	}

	public List<Date> listSimpleProdFiltro(String anoInicio, String anoFim) {

		List<Date> resultados = getManager()
				.createQuery(
						"select pa.dataCadastro from ProducaoAcademica pa where year(pa.dataCadastro) between ?1 and ?2 order by pa.dataCadastro asc",
						Date.class)
				.setParameter(1, Integer.valueOf(anoInicio)).setParameter(2, Integer.valueOf(anoFim)).getResultList();

		return resultados;
	}

	@Override
	public List<AprovacaoProducaoVO> listAprovaDiretor() {

		// Lista de Objetos
		// [0] - idProducaoAcademica
		// [1] - titulo
		// [2] - descricao
		// [3] - statusProducao

		AprovacaoProducaoVO producao = new AprovacaoProducaoVO();
		List<AprovacaoProducaoVO> producoes = new ArrayList<AprovacaoProducaoVO>();

		@SuppressWarnings("unchecked")
		List<Object[]> resultados = getManager()
				.createQuery(
						"select pa.idProducaoAcademica, pa.titulo, pa.descricao, pa.statusProducao from ProducaoAcademica pa where pa.statusProducao.descricao = 'Pendente Aprovação' order by pa.dataCadastro asc")
				.getResultList();

		for (Object[] obj : resultados) {
			producao.setId((Integer) obj[0]);
			producao.setTitulo((String) obj[1]);
			producao.setDescricao((String) obj[2]);
			producao.setStatusProducao((StatusProducao) obj[3]);
			producoes.add(producao);
		}

		return producoes;
	}

	public void updateResultado(AprovacaoProducaoVO prodAcademica) {

		ProducaoAcademica producaoAcademica = getManager().find(ProducaoAcademica.class, prodAcademica.getId());
		getManager().getTransaction().begin();
		producaoAcademica.setStatusProducao(prodAcademica.getStatusProducao());
		getManager().getTransaction().commit();

	}
}
