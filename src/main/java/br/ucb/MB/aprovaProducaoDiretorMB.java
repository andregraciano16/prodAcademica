package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.VO.AprovacaoProducaoVO;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.entity.StatusProducao;

@ManagedBean(name = "aprovaProducaoDiretorMB")
@ViewScoped
public class aprovaProducaoDiretorMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<AprovacaoProducaoVO> aprovaProducoes;
	private ProducaoAcademicaDao producaoAcademicaDao;
	private StatusProducao statusProducao;
	private StatusProducaoDao statusProducaoDao;
	private AprovacaoProducaoVO producao;

	

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
	}

	public void buscar() {
		this.setAprovaProducoes(producaoAcademicaDao.listAprovaDiretor());

	}

	private void inicializa() {
		this.setAprovaProducoes(new ArrayList<AprovacaoProducaoVO>());
		this.producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
		this.statusProducao = new StatusProducao();
		this.statusProducaoDao = new StatusProducaoDaoImpl();

	}

	public void aprovar(AprovacaoProducaoVO producao) {
		this.statusProducao = statusProducaoDao.findByDescricaoAndTipo(null, "Aprovado").get(0);
		producao.setStatusProducao(this.statusProducao);
		this.producaoAcademicaDao.updateResultado(producao);
		this.aprovaProducoes.remove(producao);
	}

	public void reprovar(AprovacaoProducaoVO producao) {
		this.statusProducao = statusProducaoDao.findByDescricaoAndTipo(null, "Reprovado").get(0);
		producao.setStatusProducao(this.statusProducao);
		this.producaoAcademicaDao.updateResultado(producao);
		this.aprovaProducoes.remove(producao);
	}

	

	public ProducaoAcademicaDao getProducaoAcademicaDao() {
		return producaoAcademicaDao;
	}

	public void setProducaoAcademicaDao(ProducaoAcademicaDao producaoAcademicaDao) {
		this.producaoAcademicaDao = producaoAcademicaDao;
	}

	public StatusProducao getStatusProducao() {
		return statusProducao;
	}

	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}

	public StatusProducaoDao getStatusProducaoDao() {
		return statusProducaoDao;
	}

	public void setStatusProducaoDao(StatusProducaoDao statusProducaoDao) {
		this.statusProducaoDao = statusProducaoDao;
	}

	public AprovacaoProducaoVO getProducao() {
		return producao;
	}

	public void setProducao(AprovacaoProducaoVO producao) {
		this.producao = producao;
	}

	public List<AprovacaoProducaoVO> getAprovaProducoes() {
		return aprovaProducoes;
	}

	public void setAprovaProducoes(List<AprovacaoProducaoVO> aprovaProducoes) {
		this.aprovaProducoes = aprovaProducoes;
	}

}
