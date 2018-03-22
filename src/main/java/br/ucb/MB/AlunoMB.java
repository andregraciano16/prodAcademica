package br.ucb.MB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
import br.ucb.enums.AcaoEnum;

@ManagedBean(name = "alunoMB")
@ViewScoped
public class AlunoMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<Aluno> alunos;
	private Aluno aluno;
	private AlunoDao alunoDao;
	private Aluno editavel;
	private List<Curso> cursos;
	private CursoDao cursoDao;
	private List<StatusAluno> variosStatus;
	private StatusAlunoDao statusAlunoDao;
	private List<Endereco> enderecos;
	private EnderecoDao enderecoDao;
	private Endereco endereco;
	private AcaoEnum acaoEnum;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.CADASTRAR);
	}

	public void cadastrar() {
		if (!verificaVazio(this.aluno, this.endereco)) {

			if (this.alunos.contains(this.aluno)) {
				setMessageError("Já contém este registro, por favor insira um novo.");
			} else {
				montar(this.aluno, this.endereco);
				this.alunoDao.save(this.aluno);
				setMessageSuccess("Cadastrado com sucesso.");
			}
			
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();
	}

	public void excluir(Aluno aluno) {
		this.alunoDao.remove(aluno);
		setMessageSuccess("Excluído com sucesso.");
		init();
	}

	public void editar() {

		if (this.aluno != null && !verificaVazio(this.aluno, this.endereco)) {
			this.enderecoDao.update(this.endereco);
			this.alunoDao.update(this.aluno);
			setMessageSuccess("Atualizado com sucesso.");
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();

	}

	public void prepararEdicao(Aluno aluno){
		this.aluno = aluno;
		this.endereco = aluno.getEndereco();
		acaoEnum = AcaoEnum.EDITAR;
	}
	
	public void visualizar(Aluno aluno){
		this.aluno = aluno;
		this.endereco = aluno.getEndereco();
		acaoEnum = AcaoEnum.VISUALIZAR;
	}
	
	public void buscar() {

		if (this.aluno != null) {
			if (verificaVazio(this.aluno)) {
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

	private void inicializa() {
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

	}

	private void montar(Aluno aluno, Endereco endereco) {
		if (aluno == null) {
			aluno = new Aluno();
		}
		aluno.setIdAluno(null);
		aluno.setNome(aluno.getNome().trim());
		aluno.setTelefoneFixo(aluno.getTelefoneFixo().trim());
		aluno.setCelular(aluno.getCelular().trim());
		aluno.setMatricula(aluno.getMatricula().trim());
		aluno.setDataCadastro(new Date());
		endereco.setBairro(endereco.getBairro().trim());
		endereco.setCidade(endereco.getCidade().trim());
		endereco.setComplemento(endereco.getComplemento().trim());
		endereco.setEstado(endereco.getEstado().trim());
		endereco.setRua(endereco.getRua().trim());
		this.enderecoDao.save(endereco);
		endereco = this.enderecoDao.find(endereco);
		aluno.setEndereco(endereco);

	}

	private boolean verificaVazio(Aluno aluno, Endereco endereco) {

		if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
			return true;
		}

		if (aluno.getTelefoneFixo() == null || aluno.getTelefoneFixo().trim().isEmpty()) {
			return true;
		}

		if (aluno.getSexo() == '\0') {
			return true;
		}

		if (aluno.getCelular() == null || aluno.getCelular().trim().isEmpty()) {
			return true;
		}

		if (aluno.getMatricula() == null || aluno.getMatricula().trim().isEmpty()) {
			return true;
		}

		if (aluno.getDataNascimento() == null) {
			return true;
		}

		if (aluno.getCurso() == null) {
			return true;
		}

		if (aluno.getStatusAluno() == null) {
			return true;
		}

		if (endereco.getBairro() == null || endereco.getBairro().trim().isEmpty()) {
			return true;
		}

		if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty()) {
			return true;
		}

		if (endereco.getEstado() == null || endereco.getEstado().trim().isEmpty()) {
			return true;
		}

		if (endereco.getComplemento() == null || endereco.getComplemento().trim().isEmpty()) {
			return true;
		}

		if (endereco.getNumero() == null) {
			return true;
		}

		if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean verificaVazio(Aluno aluno) {
		boolean flag = true;

		if (aluno.getNome() != null && !aluno.getNome().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getTelefoneFixo() != null && !aluno.getTelefoneFixo().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getSexo() != '\0') {
			flag = false;
		}

		if (aluno.getCelular() != null && !aluno.getCelular().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getMatricula() != null && !aluno.getMatricula().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getDataNascimento() != null) {
			flag = false;
		}

		if (aluno.getCurso() != null) {
			flag = false;
		}

		if (aluno.getStatusAluno() != null) {
			flag = false;
		}

		return flag;
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

	public AcaoEnum getAcaoEnum() {
		return acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}

}
