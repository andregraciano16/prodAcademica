package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
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
import br.ucb.dao.ProjetoDao;
import br.ucb.dao.impl.ProjetoDaoImpl;

@ManagedBean(name="historicoMB")
@ViewScoped
public class HistoricoMB extends BaseMB{

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

	
	
	
	@PostConstruct
	public void init() {
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
		
		buscar();
	}

	public void cadastrar(Historico historico) {
		if (this.historico.getDataAlteracao() != null &&
				this.historico.getAluno() != null && 
				this.historico.getDocente() != null &&
				this.historico.getProducaoAcademica() != null &&
				this.historico.getProjeto() != null) {
			if (this.historicos.contains(this.historico)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarHistorico();
				this.historicoDao.save(this.historico);
				msg = "Cadastrado com sucesso.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
			}
		} else {
			msg = "Preencha os campos corretamente.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
		init();
	}

	private void montarHistorico() {
		if (this.historico == null) {
			this.historico = new Historico();
		}
		this.historico.setIdHistorico(null);
		this.historico.setDataAlteracao(this.historico.getDataAlteracao());
		this.historico.setAluno(this.historico.getAluno());
		this.historico.setDocente(this.historico.getDocente());
		this.historico.setProducaoAcademica(this.historico.getProducaoAcademica());
		this.historico.setProjeto(this.historico.getProjeto());
	}

	public void excluir(Historico historico) {
		this.historicoDao.remove(historico);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getDataAlteracao() != null) {

			historico = (Historico) event.getObject();
			historico.setDataAlteracao(editavel.getDataAlteracao());
			historico.setAluno(editavel.getAluno());
			historico.setDocente(editavel.getDocente());
			historico.setProducaoAcademica(editavel.getProducaoAcademica());
			historico.setProjeto(editavel.getProjeto());
			this.historicoDao.update(this.historico);
			msg = "Atualizado com sucesso.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		} else {
			msg = "Preencha os campos corretamente.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
		init();

	}

	public void cancela(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscar() {

		if (this.historico.getDataAlteracao() != null) {
				this.historicos = this.historicoDao.findByData(this.historico.getDataAlteracao());
				this.projetos = this.projetoDao.list();
			} else {
				this.historicos = this.historicoDao.list();
				this.alunos = this.alunoDao.list();
				this.docentes = this.docenteDao.list();
				this.producoes = this.producaoAcademicaDao.list();
				this.projetos = this.projetoDao.list();
			}
		}

	

	public void limpar() {
		init();
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


}

