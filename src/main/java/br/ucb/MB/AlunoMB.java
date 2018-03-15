package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.CursoDao;
import br.ucb.dao.EnderecoDao;
import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.Curso;
import br.ucb.entity.Endereco;
import br.ucb.entity.StatusAluno;

@ManagedBean(name="alunoMB")
@ViewScoped
public class AlunoMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<Aluno> alunos;
	private Aluno aluno;
	private AlunoDao alunoDao;
	private String msg;
	private Aluno editavel;
	private List<Curso> cursos;
	private CursoDao cursoDao;
	private List<StatusAluno> variosStatus;
	private StatusAlunoDao statusAlunoDao;
	private List<Endereco> enderecos;
	private EnderecoDao enderecoDao;

	@PostConstruct
	public void init() {
		this.alunos = new ArrayList<Aluno>();
		this.aluno = new Aluno();
		this.aluno.setNome("");
		this.aluno.setTelefoneFixo("");
		this.aluno.setSexo('\0');
		this.aluno.setCelular("");
		this.aluno.setDataCadastro(null);
		this.aluno.setMatricula(null);
		this.aluno.setDataNascimento(null);
		this.aluno.setEndereco(null);
		this.aluno.setCurso(null);
		this.aluno.setStatusAluno(null);
		this.alunoDao = new AlunoDaoImpl();
		this.cursoDao = new CursoDaoImpl();
		this.statusAlunoDao = new StatusAlunoDaoImpl();
		this.editavel = new Aluno();
		this.enderecos = new ArrayList<Endereco>();
		this.enderecoDao = new EnderecoDaoImpl();
		buscar();
	}

	public void cadastrar(Aluno aluno) {
		if (this.aluno.getNome() != null && !this.aluno.getNome().trim().isEmpty()
				&& this.aluno.getTelefoneFixo() != null && !this.aluno.getTelefoneFixo().trim().isEmpty()
				&& this.aluno.getSexo() != '\0' 
				&& this.aluno.getCelular() != null && !this.aluno.getCelular().trim().isEmpty()
				&& this.aluno.getDataCadastro() != null 
				&& this.aluno.getMatricula() != null && !this.aluno.getMatricula().trim().isEmpty() 
				&& this.aluno.getDataNascimento() != null
				&& this.aluno.getCurso() != null
				&& this.aluno.getStatusAluno() != null 
				&& this.aluno.getEndereco() != null) {
			if (this.alunos.contains(this.aluno)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarAluno();
				this.alunoDao.save(this.aluno);
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

	private void montarAluno() {
		if (this.aluno == null) {
			this.aluno = new Aluno();
		}
		this.aluno.setIdAluno(null);
		aluno.setNome(this.aluno.getNome().trim());
		aluno.setTelefoneFixo(this.aluno.getTelefoneFixo().trim());
		aluno.setCelular(this.aluno.getCelular().trim());
		aluno.setSexo(this.aluno.getSexo());
		aluno.setDataCadastro(this.aluno.getDataCadastro());
		aluno.setMatricula(this.aluno.getMatricula().trim());
		aluno.setDataNascimento(this.aluno.getDataNascimento());
		aluno.setCurso(this.aluno.getCurso());
		aluno.setStatusAluno(this.aluno.getStatusAluno());
		aluno.setEndereco(this.aluno.getEndereco());
	}

	public void excluir(Aluno aluno) {
		this.alunoDao.remove(aluno);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getNome() != null) {

			aluno = (Aluno) event.getObject();
			aluno.setNome(editavel.getNome().trim());
			aluno.setTelefoneFixo(editavel.getTelefoneFixo().trim());
			aluno.setCelular(editavel.getCelular().trim());
			aluno.setSexo(editavel.getSexo());
			aluno.setDataCadastro(editavel.getDataCadastro());
			aluno.setMatricula(editavel.getMatricula().trim());
			aluno.setDataNascimento(editavel.getDataNascimento());
			aluno.setCurso(editavel.getCurso());
			aluno.setStatusAluno(editavel.getStatusAluno());
			aluno.setEndereco(editavel.getEndereco());
			this.alunoDao.update(this.aluno);
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

		if (this.aluno.getNome() != null) {
			this.alunos = this.alunoDao.findByNome(this.aluno.getNome());
			this.enderecos = this.enderecoDao.list();
		} else {
			this.alunos = this.alunoDao.list();
			this.enderecos = this.enderecoDao.list();
			this.setCursos(this.cursoDao.list());
			this.setVariosStatus(this.statusAlunoDao.list());
		}
	}

	public void limpar() {
		init();
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

	public Aluno getEditavel() {
		return editavel;
	}

	public void setEditavel(Aluno editavel) {
		this.editavel = editavel;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Endereco> getLinhasPesquisa() {
		return enderecos;
	}

	public void setLinhasPesquisa(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public EnderecoDao getEnderecoDao() {
		return enderecoDao;
	}

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	/*
	 * public AlunoDao getAlunoDao() { return alunoDao; }
	 * 
	 * public void setAlunoDao(AlunoDao alunoDao) { this.alunoDao = alunoDao; }
	 */

	public List<Aluno> getTiposAluno() {
		return alunos;
	}

	public void setTiposAluno(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<StatusAluno> getVariosStatus() {
		return variosStatus;
	}

	public void setVariosStatus(List<StatusAluno> variosStatus) {
		this.variosStatus = variosStatus;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
