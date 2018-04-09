package br.ucb.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ucb.VO.DocenteVO;
import br.ucb.VO.UsuarioVO;
import br.ucb.dao.DocenteDao;
import br.ucb.entity.Docente;
import br.ucb.util.StringUtil;


public class DocenteDaoImpl extends DaoGenericoImpl<Docente, Integer> implements DocenteDao {

	@Override
	public Docente findById(Integer id) {
		Query query = getManager().createQuery(" from Docente d  WHERE d.idDocente like ?1 ");
		query.setParameter(1,id);
		
		return (Docente) query.getSingleResult();
	}

	@Override
	public List<Docente> find(DocenteVO docentePesq) {
		String where = montarWhere(docentePesq);
		Query query = getManager().createQuery(" from Docente t " + where);
        montarParametrs(docentePesq, query);		
		return query.getResultList();
	}
	
	public void montarParametrs(DocenteVO filtro, Query query){
		if(StringUtil.isNotNullIsNotEmpty(filtro.getMatricula())){
			query.setParameter(1, "%"+ filtro.getMatricula() + "%");
		}
		if(StringUtil.isNotNullIsNotEmpty(filtro.getNome())){
			query.setParameter(2, "%"+ filtro.getNome() + "%");
		}
	}
	
	public String montarWhere(DocenteVO filtro){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" WHERE 1=1 ");
		if(StringUtil.isNotNullIsNotEmpty(filtro.getMatricula())){
			consulta.append(" and t.matricula like ?1 ");
		}
		if(StringUtil.isNotNullIsNotEmpty(filtro.getNome())){
			consulta.append(" and t.nome like ?2 ");
		}
		return consulta.toString();
	}

	@Override
	public UsuarioVO findByMatricula(String matricula) {
		Query query = getManager().createQuery(" from Docente t  WHERE t.matricula like ?1 ");
		query.setParameter(1, matricula);
		Docente docente = null;
		UsuarioVO usuario = null;
		try{
		   docente = (Docente) query.getSingleResult();
		}catch(NoResultException e){ 
			
		}
		if(docente != null){
			usuario = new UsuarioVO();
			usuario.setMatricula(docente.getMatricula());
			usuario.setSenha(docente.getSenha());
			usuario.setGrupo(docente.getTipoDocente().getTipo());
		}
		return usuario;
	}

}
