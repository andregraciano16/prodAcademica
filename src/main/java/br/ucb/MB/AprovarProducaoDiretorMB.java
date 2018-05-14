package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.VO.AprovacaoProducaoVO;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.StatusAprovacaoDao;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.StatusAprovacaoDaoImpl;
import br.ucb.entity.StatusAprovacao;

@ManagedBean(name = "aprovarProducaoDiretorMB")
@ViewScoped
public class AprovarProducaoDiretorMB extends BaseMB {

	private static final long serialVersionUID = 3018502226740620474L;

	private List<AprovacaoProducaoVO> aprovaProducoes;
	private ProducaoAcademicaDao producaoAcademicaDao;
	private StatusAprovacaoDao statusAprovacaoDao;
	private StatusAprovacao aprovar;
	private StatusAprovacao reprovar;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
	}

	public void inicializa() {
		this.aprovaProducoes = new ArrayList<AprovacaoProducaoVO>();
		this.producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
		this.statusAprovacaoDao = new StatusAprovacaoDaoImpl();
		this.aprovar = new StatusAprovacao();
		this.reprovar = new StatusAprovacao();

	}

	public void buscar() {
		this.aprovaProducoes = this.producaoAcademicaDao.listAprovaDiretor();
		this.aprovar = this.statusAprovacaoDao.findByDescricao("Aprovado").get(0);
		this.reprovar = this.statusAprovacaoDao.findByDescricao("Reprovado").get(0);
		

	}
	
	public void aprovar(AprovacaoProducaoVO producao){
		producao.setStatusAprovacao(this.aprovar);
		this.producaoAcademicaDao.updateResultado(producao);
		this.aprovaProducoes.remove(producao);
		
		
	}
	
	public void reprovar(AprovacaoProducaoVO producao){
		producao.setStatusAprovacao(this.reprovar);
		this.producaoAcademicaDao.updateResultado(producao);
		this.aprovaProducoes.remove(producao);
	}

	public List<AprovacaoProducaoVO> getAprovaProducoes() {
		return aprovaProducoes;
	}

	public void setAprovaProducoes(List<AprovacaoProducaoVO> aprovaProducoes) {
		this.aprovaProducoes = aprovaProducoes;
	}

	public ProducaoAcademicaDao getProducaoAcademicaDao() {
		return producaoAcademicaDao;
	}

	public void setProducaoAcademicaDao(ProducaoAcademicaDao producaoAcademicaDao) {
		this.producaoAcademicaDao = producaoAcademicaDao;
	}

	public StatusAprovacaoDao getStatusAprovacaoDao() {
		return statusAprovacaoDao;
	}

	public void setStatusAprovacaoDao(StatusAprovacaoDao statusAprovacaoDao) {
		this.statusAprovacaoDao = statusAprovacaoDao;
	}

	public StatusAprovacao getAprovar() {
		return aprovar;
	}

	public void setAprovar(StatusAprovacao aprovar) {
		this.aprovar = aprovar;
	}

	public StatusAprovacao getReprovar() {
		return reprovar;
	}

	public void setReprovar(StatusAprovacao reprovar) {
		this.reprovar = reprovar;
	}
	
	
}
