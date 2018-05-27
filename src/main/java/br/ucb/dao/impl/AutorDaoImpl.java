package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.AutorDao;
import br.ucb.entity.Autor;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.filtro.ProdAcFiltro;

public class AutorDaoImpl  extends DaoGenericoImpl<Autor, Integer> implements AutorDao {

	@Override
	public List<ProducaoAcademica> findProducaoByFiltro(ProdAcFiltro filtro) {
		String where = montarWhereFiltro(filtro);
		Query query = getManager().createQuery(" SELECT distinct t.producaoAcademica From Autor t " + where);
		montarParametrsFiltro(query, filtro);
		return  query.getResultList();
	}
	
	private void montarParametrsFiltro(Query query, ProdAcFiltro filtro) {
		int contador = 1;
		if(filtro.getCodigoParticipante() != null){
			query.setParameter(contador, filtro.getCodigoParticipante());
			contador++;
		}
		if (filtro.getTipoAutor() != null && !filtro.getTipoAutor().isEmpty()) {
			query.setParameter(contador, filtro.getTipoAutor());
			contador++;
		}
		if (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) {
			query.setParameter(contador, "%" + filtro.getDescricao() + "%");
			contador++;
		}
		if (filtro.getTitulo() != null && !filtro.getTitulo().isEmpty()) {
			query.setParameter(contador, "%" + filtro.getTitulo() + "%");
			contador++;
		}
		if(filtro.getDataCadastro() != null){
			query.setParameter(contador, filtro.getDataCadastro());	
			contador++;
		}
		if(filtro.getCodigo() != null){
			query.setParameter(contador, filtro.getCodigo());
			contador++;
		}
	}

	private String montarWhereFiltro(ProdAcFiltro filtro) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if (filtro.getCodigoParticipante() != null) {
			consulta.append(" and t.codigoAutor = ? ");
		}
		if (filtro.getTipoAutor()!= null && !filtro.getTipoAutor().isEmpty()) {
			consulta.append(" and t.tipoAutor like ? ");
		}
		if (filtro.getDescricao() != null && !filtro.getDescricao().isEmpty()) {
			consulta.append(" and t.producaoAcademica.descricao like ? ");
		}
		if (filtro.getTitulo() != null && !filtro.getTitulo().isEmpty()) {
			consulta.append(" and t.producaoAcademica.titulo like ? ");
		}
		if(filtro.getDataCadastro() != null){
			consulta.append(" and t.producaoAcademica.dataCadastro = ? ");			
		}
		if(filtro.getCodigo() != null){
			consulta.append(" and t.producaoAcademica.idProducaoAcademica = ? ");
		}
		return consulta.toString();
	}

}
