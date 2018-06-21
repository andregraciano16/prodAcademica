package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.ucb.entity.Historico;
import br.ucb.dao.AlunoDao;
import br.ucb.dao.HistoricoDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.HistoricoDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.Docente;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.entity.Projeto;
import br.ucb.enums.AcaoEnum;
import br.ucb.dao.ProjetoDao;
import br.ucb.dao.impl.ProjetoDaoImpl;

@ManagedBean(name = "historicoMB")
@ViewScoped
public class HistoricoMB extends BaseMB {

	private static final long serialVersionUID = 4919235181119407190L;

	private List<Historico> historicos;
	private Historico historico;
	private HistoricoDao historicoDao;
	private String msg;
	private Historico editavel;
	private List<Aluno> alunos;
	private AlunoDao alunoDao;
	private List<Docente> docentes;
	private DocenteDao docenteDao;
	private List<ProducaoAcademica> producoes;
	private ProducaoAcademicaDao producaoAcademicaDao;
	private List<Projeto> projetos;
	private ProjetoDao projetoDao;
	private AcaoEnum acaoEnum;
	 

	@PostConstruct
	public void init() {	
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.LISTAR);
	}


	public void buscar() {

		if (this.historico.getDataAlteracao() == null
				&& this.historico.getAluno() == null
				&& this.historico.getDocente() == null
				&& this.historico.getProducaoAcademica() == null
				&& this.historico.getProjeto() == null) {
			this.historicos = this.historicoDao.listOrder();
			this.alunos = this.alunoDao.list();
			this.docentes = this.docenteDao.list();
			this.producoes = this.producaoAcademicaDao.list();
			this.projetos = this.projetoDao.list();
		} else {
			this.historicos = this.historicoDao.findBySearch(this.historico);
			this.alunos = this.alunoDao.list();
			this.docentes = this.docenteDao.list();
			this.producoes = this.producaoAcademicaDao.list();
			this.projetos = this.projetoDao.list();
		}
		
	}

	
	public void visualizar(Historico historico){
		this.historico = historico;
		this.setAcaoEnum(AcaoEnum.VISUALIZAR);
	}

	
	public void limpar() {
		init();
	}

	public List<Aluno> escolheAluno(String query) {
		List<Aluno> alunosFiltrados = new ArrayList<Aluno>();

		for (int i = 0; i < this.alunos.size(); i++) {
			Aluno aluno = this.alunos.get(i);
			if (aluno.getNome().toLowerCase().startsWith(query.toLowerCase())) {
				alunosFiltrados.add(aluno);
			}
		}

		return alunosFiltrados;
	}
	
	
	public List<Docente> escolheDocente(String query) {
		List<Docente> docentesFiltrados = new ArrayList<Docente>();

		for (int i = 0; i < this.docentes.size(); i++) {
			Docente docente = this.docentes.get(i);
			if (docente.getNome().toLowerCase().startsWith(query.toLowerCase())) {
				docentesFiltrados.add(docente);
			}
		}

		return docentesFiltrados;
	}
	
	
	public List<ProducaoAcademica> escolheProducao(String query) {
		List<ProducaoAcademica> producoesFiltradas = new ArrayList<ProducaoAcademica>();

		for (int i = 0; i < this.producoes.size(); i++) {
			ProducaoAcademica producao = this.producoes.get(i);
			if (producao.getTitulo().toLowerCase().startsWith(query.toLowerCase())) {
				producoesFiltradas.add(producao);
			}
		}

		return producoesFiltradas;
	}
	
	public List<Projeto> escolheProjeto(String query) {
		List<Projeto> projetosFiltrados = new ArrayList<Projeto>();

		for (int i = 0; i < this.projetos.size(); i++) {
			Projeto projeto = this.projetos.get(i);
			if (projeto.getNome().toLowerCase().startsWith(query.toLowerCase())) {
				projetosFiltrados.add(projeto);
			}
		}

		return projetosFiltrados;
	}
	
	public void excluir(){
		this.historicoDao.remove(this.historico);
		setMessageSuccess("Histórico removido com sucesso.");
		init();
	}
	
	public void inicializa(){
		this.historicos = new ArrayList<Historico>();
		this.historico = new Historico();
		this.historico.setDataAlteracao(null);
		this.historicoDao = new HistoricoDaoImpl();
		this.editavel = new Historico();
		this.alunos = new ArrayList<Aluno>();
		this.alunoDao = new AlunoDaoImpl();
		this.docentes = new ArrayList<Docente>();
		this.docenteDao = new DocenteDaoImpl();
		this.producoes = new ArrayList<ProducaoAcademica>();
		this.producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
		this.projetos = new ArrayList<Projeto>();
		this.projetoDao = new ProjetoDaoImpl();
		
	}
	public List<Historico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public HistoricoDao getHistoricoDao() {
		return historicoDao;
	}

	public void setHistoricoDao(HistoricoDao historicoDao) {
		this.historicoDao = historicoDao;
	}

	public Historico getEditavel() {
		return editavel;
	}

	public void setEditavel(Historico editavel) {
		this.editavel = editavel;
	}

	public Historico getHistorico() {
		return this.historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public List<Projeto> getLinhasPesquisa() {
		return projetos;
	}

	public void setLinhasPesquisa(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public ProjetoDao getProjetoDao() {
		return projetoDao;
	}

	public void setProjetoDao(ProjetoDao projetoDao) {
		this.projetoDao = projetoDao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public AlunoDao getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public DocenteDao getDocenteDao() {
		return docenteDao;
	}

	public void setDocenteDao(DocenteDao docenteDao) {
		this.docenteDao = docenteDao;
	}

	public List<ProducaoAcademica> getProducoes() {
		return producoes;
	}

	public void setProducoes(List<ProducaoAcademica> producoes) {
		this.producoes = producoes;
	}

	public ProducaoAcademicaDao getProducaoAcademicaDao() {
		return producaoAcademicaDao;
	}

	public void setProducaoAcademicaDao(ProducaoAcademicaDao producaoAcademicaDao) {
		this.producaoAcademicaDao = producaoAcademicaDao;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	

	public AcaoEnum getAcaoEnum() {
		return acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}
}
