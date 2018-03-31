package br.ucb.MB;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.TipoProducaoDao;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.dao.impl.TipoProducaoDaoImpl;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.entity.StatusProducao;
import br.ucb.entity.TipoProducao;

@ManagedBean(name = "producaoAcademicaMB")
@ViewScoped
public class ProducaoAcademicaMB extends BaseMB {

	private static final long serialVersionUID = -8679220128687524726L;
	private ProducaoAcademicaDao producaoAcDao;
	private TipoProducaoDao tipoProducaoDao;
	private StatusProducaoDao statusProducaoDao;
	private LinhaPesquisaDao linhaPesquisaDao;
	private ProducaoAcademica producaoAcademica;
    private TipoProducao tipoProducao;
    private LinhaPesquisa linhaPesquisa;
    private StatusProducao statusProducao;
	
	
	@PostConstruct
	public void init() {
		this.producaoAcDao     = new ProducaoAcademicaDaoImpl();
		this.producaoAcademica = new ProducaoAcademica();
	    this.linhaPesquisaDao  = new LinhaPesquisaDaoImpl();
	    this.statusProducaoDao = new StatusProducaoDaoImpl();
	    this.tipoProducaoDao   = new TipoProducaoDaoImpl();
	}

	public void cadastrar() {
		this.producaoAcademica.setDataCadastro(new Date());
		this.producaoAcademica.setLinhaPesquisa(this.linhaPesquisaDao.findById(this.linhaPesquisa.getIdLinhaPesquisa()));
		this.producaoAcademica.setTipoProducao(this.tipoProducao);
		this.producaoAcademica.setStatusProducao(this.statusProducao);
		this.producaoAcDao.save(this.producaoAcademica);
	}

	public void editar(ProducaoAcademica producaoAcademica) {
		this.producaoAcDao.update(producaoAcademica);
	}

	public void excluir(ProducaoAcademica producaoAcademica) {
		this.producaoAcDao.remove(producaoAcademica);
	}

	public void buscar() {
		// this.docentes =
		// this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(),
		// tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}
	
	public List<TipoProducao> getTipo(){
		return this.tipoProducaoDao.list();
	}
	
	public List<StatusProducao> getStatus(){
		return this.statusProducaoDao.list();
	}
	
	public List<LinhaPesquisa> getLinhas(){
		return this.linhaPesquisaDao.list();
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return this.linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public StatusProducao getStatusProducao() {
		return this.statusProducao;
	}

	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}

	public TipoProducao getTipoProducao() {
		return this.tipoProducao;
	}

	public void setTipoProducao(TipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}
	

	
}
