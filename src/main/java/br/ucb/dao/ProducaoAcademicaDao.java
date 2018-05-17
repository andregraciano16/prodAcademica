package br.ucb.dao;

import java.util.Date;
import java.util.List;

import br.ucb.VO.AprovacaoProducaoVO;
import br.ucb.entity.ProducaoAcademica;

public interface ProducaoAcademicaDao extends DaoGenerico<ProducaoAcademica, Integer> {

	ProducaoAcademica findById(Integer id);
	
	ProducaoAcademica findByProdAc(ProducaoAcademica pa);
	
	public List<Object[]> listSimpleTipo();
	
	public List<Object[]> listSimpleLinha();
	
	public List<Object[]> listSimpleQualis();
	
	public List<Date> listSimpleDatas();
	
	public List<Object[]> listSimpleQualisFiltro(String anoInicio, String anoFim);
	
	public List<Date> listSimpleProdFiltro(String anoInicio, String anoFim);
	
	public List<AprovacaoProducaoVO> listAprovaDiretor();
	
	public List<AprovacaoProducaoVO> listAprovaProfessor(Integer cod);
	
	public void updateResultado(AprovacaoProducaoVO prodAcademica);
	

}
