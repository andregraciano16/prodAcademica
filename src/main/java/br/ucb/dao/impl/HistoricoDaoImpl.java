package br.ucb.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.ucb.dao.HistoricoDao;
import br.ucb.entity.Historico;

public class HistoricoDaoImpl extends DaoGenericoImpl<Historico, Integer> implements HistoricoDao {
	private Calendar c = Calendar.getInstance();
	private Date date = new Date();

	@Override
	public List<Historico> findBySearch(Historico historico) {
		String where = montarWhere(historico);
		Query query = getManager().createQuery(" From Historico h" + where + " order by h.dataAlteracao desc");
		montarParametrs(query, historico);
		return query.getResultList();

	}
	
	public List <Historico> listOrder(){
		Query query = getManager().createQuery(" From Historico h order by h.dataAlteracao desc");
		return query.getResultList();
	}
	
	public void montarParametrs(Query query, Historico historico){
		if(historico.getDataAlteracao()!= null){
			this.c.setTime(historico.getDataAlteracao());
			this.c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			this.date = c.getTime();
			query.setParameter(1, historico.getDataAlteracao());
			query.setParameter(7, this.date);
		}
		if(historico.getAluno() != null){
			query.setParameter(2,historico.getAluno());
		}
		
		if(historico.getDocente() != null){
			query.setParameter(3,historico.getDocente());
		}
		
		if(historico.getProducaoAcademica() != null){
			query.setParameter(4,historico.getProducaoAcademica());
		}
		
		if(historico.getProjeto() != null){
			query.setParameter(5,historico.getProjeto());
		}
		
		if(historico.getAlteracao() != null && !historico.getAlteracao().trim().isEmpty()){
			query.setParameter(6,historico.getAlteracao());
		}
	}
	
	public String montarWhere(Historico historico){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(historico.getDataAlteracao() != null){
			consulta.append(" and h.dataAlteracao >= ?1 and h.dataAlteracao < ?7");
		}
		
		if(historico.getAluno() != null){
			consulta.append(" and h.aluno like ?2 ");
		}
		
		if(historico.getDocente() != null){
			consulta.append(" and h.docente like ?3 ");
		}
		
		if(historico.getProducaoAcademica() != null){
			consulta.append(" and h.producaoAcademica like ?4 ");
		}
		
		if(historico.getProjeto() != null){
			consulta.append(" and h.projeto like ?5 ");
		}
		
		if(historico.getAlteracao() != null && !historico.getAlteracao().trim().isEmpty()){
			consulta.append(" and h.projeto like ?6 ");
		}
		return consulta.toString();
	}


}
