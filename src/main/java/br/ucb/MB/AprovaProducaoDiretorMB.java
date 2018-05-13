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


@ManagedBean(name = "aprovaProducaoDiretorMB")
@ViewScoped
public class AprovaProducaoDiretorMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<AprovacaoProducaoVO> aprovaProducoes;
	private ProducaoAcademicaDao producaoAcademicaDao;
	private StatusAprovacao statusAprovacao;
	private StatusAprovacaoDao statusAprovacaoDao;
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
		this.statusAprovacao = new StatusAprovacao();
		this.statusAprovacaoDao = new StatusAprovacaoDaoImpl();

	}

	public void aprovar(AprovacaoProducaoVO producao) {
		this.statusAprovacao = statusAprovacaoDao.findByDescricao("Aprovado").get(0);
		producao.setStatusAprovacao(this.statusAprovacao);
		this.producaoAcademicaDao.updateResultado(producao);
		this.aprovaProducoes.remove(producao);
	}

	public void reprovar(AprovacaoProducaoVO producao) {
		this.statusAprovacao = statusAprovacaoDao.findByDescricao("Reprovado").get(0);
		producao.setStatusAprovacao(this.statusAprovacao);
		this.producaoAcademicaDao.updateResultado(producao);
		this.aprovaProducoes.remove(producao);
	}

	

	public ProducaoAcademicaDao getProducaoAcademicaDao() {
		return producaoAcademicaDao;
	}

	public void setProducaoAcademicaDao(ProducaoAcademicaDao producaoAcademicaDao) {
		this.producaoAcademicaDao = producaoAcademicaDao;
	}

	public StatusAprovacao getStatusAprovacao() {
		return statusAprovacao;
	}

	public void setStatusAprovacao(StatusAprovacao statusAprovacao) {
		this.statusAprovacao = statusAprovacao;
	}

	public StatusAprovacaoDao getStatusAprovacaoDao() {
		return statusAprovacaoDao;
	}

	public void setStatusAprovacaoDao(StatusAprovacaoDao statusAprovacaoDao) {
		this.statusAprovacaoDao = statusAprovacaoDao;
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
