package br.ucb.MB;

import java.util.ArrayList;
import java.util.Date;
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

@ManagedBean(name = "alunoMB")
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
	private Endereco endereco;
	
	@PostConstruct
	public void init() {
		this.alunos = new ArrayList<Aluno>();
		this.aluno = new Aluno();
		this.aluno.setNome("");
		this.aluno.setTelefoneFixo("");
		this.aluno.setSexo('\0');
		this.aluno.setCelular("");
		this.aluno.setMatricula("");
		this.aluno.setDataCadastro(null);
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
		this.endereco = new Endereco();
		buscar();
	}

	public void cadastrar(Aluno aluno) {
		if (this.aluno.getNome() != null && !this.aluno.getNome().trim().isEmpty()
				&& this.aluno.getTelefoneFixo() != null && !this.aluno.getTelefoneFixo().trim().isEmpty()
				&& this.aluno.getSexo() != '\0' && this.aluno.getCelular() != null
				&& !this.aluno.getCelular().trim().isEmpty()
				&& this.aluno.getMatricula() != null && !this.aluno.getMatricula().trim().isEmpty()
				&& this.aluno.getDataNascimento() != null && this.aluno.getCurso() != null
				&& this.aluno.getStatusAluno() != null
				&& this.endereco.getBairro() != null && !this.endereco.getBairro().trim().isEmpty()
				&& this.endereco.getCidade() != null && !this.endereco.getCidade().trim().isEmpty()
				&& this.endereco.getEstado() != null && !this.endereco.getEstado().trim().isEmpty()
				&& this.endereco.getComplemento() != null && !this.endereco.getComplemento().trim().isEmpty()
				&& this.endereco.getNumero() != null
				&& this.endereco.getRua() != null && !this.endereco.getRua().trim().isEmpty()) {
			
			if (this.alunos.contains(this.aluno)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarAluno();
				this.enderecoDao.save(this.endereco);
				this.endereco = this.enderecoDao.find(this.endereco);
				this.aluno.setEndereco(this.endereco);
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
		aluno.setDataCadastro(new Date()) ;
		
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

		if (this.aluno != null) {
			if (this.aluno.getNome().trim().isEmpty()
					&& this.aluno.getCelular().trim().isEmpty()
					&& this.aluno.getCurso() == null
					&& this.aluno.getDataNascimento() == null
					&& this.aluno.getMatricula().trim().isEmpty()
					&& this.aluno.getSexo() == '\0'
					&& this.aluno.getStatusAluno() == null
					&& this.aluno.getTelefoneFixo().trim().isEmpty()) {
				
				this.alunos = this.alunoDao.list();
				this.enderecos = this.enderecoDao.list();
				this.cursos = this.cursoDao.list();
				this.variosStatus = this.statusAlunoDao.list();
			} else {
				this.alunos = this.alunoDao.findBySearch(this.aluno);
				this.enderecos = this.enderecoDao.list();
				this.cursos = this.cursoDao.list();
				this.variosStatus = this.statusAlunoDao.list();
			}
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
	
	public CursoDao getCursoDao() {
		return cursoDao;
	}

	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}

	public StatusAlunoDao getStatusAlunoDao() {
		return statusAlunoDao;
	}

	public void setStatusAlunoDao(StatusAlunoDao statusAlunoDao) {
		this.statusAlunoDao = statusAlunoDao;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}