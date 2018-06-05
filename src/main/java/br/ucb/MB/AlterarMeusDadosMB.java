package br.ucb.MB;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.CursoDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.EnderecoDao;
import br.ucb.dao.HistoricoDao;
import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.TipoDocenteDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.dao.impl.HistoricoDaoImpl;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.dao.impl.TipoDocenteDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.Curso;
import br.ucb.entity.Docente;
import br.ucb.entity.Endereco;
import br.ucb.entity.Historico;
import br.ucb.entity.StatusAluno;
import br.ucb.entity.TipoDocente;
import br.ucb.enums.AcaoEnum;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;
import br.ucb.util.StringUtil;

@ManagedBean(name = "alterarMeusDadosMB")
@ViewScoped
public class AlterarMeusDadosMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<Aluno> alunos;
	private Aluno aluno;
	private AlunoDao alunoDao;
	private Aluno editavel;
	private List<Curso> cursos;
	private CursoDao cursoDao;
	private Curso curso;
	private List<StatusAluno> variosStatus;
	private StatusAlunoDao statusAlunoDao;
	private List<Endereco> enderecos;
	private EnderecoDao enderecoDao;
	private Endereco endereco;
	private AcaoEnum acaoEnum;
	private Historico historico;
	private HistoricoDao historicoDao;
	private UsuarioSistema user;
	private DocenteDao docenteDao;
	private String verificaSenha;
	private String senhaAtual;
	private boolean resultado;
	private Docente docente;
	private String confirmarSenha;
	private TipoDocenteDao tipoDocenteDao;
	private TipoDocente tipoDocente;
	private List<Docente> docentes;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		if (isDiscente()) {
			this.aluno = this.alunoDao.getAlunobyMatricula(user.getUsuario().getMatricula());
			this.endereco = this.aluno.getEndereco();
			this.senhaAtual = this.aluno.getSenha();
		} else {
			this.docente = this.docenteDao.getDocentebyMatricula(user.getUsuario().getMatricula());
			this.endereco = this.docente.getEndereco();
			this.senhaAtual = this.docente.getSenha();
		}
	}

	private void buscar() {
		this.cursos = this.cursoDao.list();
		this.variosStatus = this.statusAlunoDao.list();
		this.alunos = this.alunoDao.list();
		this.docentes = this.docenteDao.list();
	}

	public void editar() {

		if (this.aluno != null && !verificaVazio(this.aluno, this.endereco)) {
			if (StringUtil.isNotNullIsNotEmpty(this.verificaSenha)
					|| StringUtil.isNotNullIsNotEmpty(this.aluno.getSenha())) {
				if (validarSenhaAluno()) {
					this.enderecoDao.update(this.endereco);
					this.resultado = this.alunoDao.updateM(this.aluno);
					if (this.resultado) {
						cadastraHistoricoAluno("Foi alterado com sucesso.", this.aluno);
						setMessageSuccess("Atualizado com sucesso.");
						init();
					} else
						setMessageError("Houve um erro ao salvar no sistema.");
				}
			} else {
				this.aluno.setSenha(this.senhaAtual);
				this.enderecoDao.update(this.endereco);
				this.resultado = this.alunoDao.updateM(this.aluno);
				if (this.resultado) {
					cadastraHistoricoAluno("Foi alterado com sucesso.", this.aluno);
					setMessageSuccess("Atualizado com sucesso.");
					init();
				} else
					setMessageError("Houve um erro ao salvar no sistema.");
			}
		} else {
			setMessageError("Preencha os campos corretamente.");
		}

	}

	private boolean validarSenhaAluno() {
		boolean isValido = Boolean.TRUE;
		if (!StringUtil.isNotNullIsNotEmpty(this.aluno.getSenha())) {
			setMessageError("Informe a senha");
			isValido = Boolean.FALSE;
		}
		if (!StringUtil.isNotNullIsNotEmpty(this.verificaSenha)) {
			setMessageError("Informe a confimração da senha");
			isValido = Boolean.FALSE;
		}
		if (StringUtil.isNotNullIsNotEmpty(this.aluno.getSenha())
				&& StringUtil.isNotNullIsNotEmpty(this.verificaSenha)) {
			if (!this.aluno.getSenha().equals(this.verificaSenha)) {
				setMessageError("Senhas diferentes");
				isValido = Boolean.FALSE;
			}
		}
		return isValido;
	}

	private boolean validarSenhaDocente() {
		boolean isValido = Boolean.TRUE;
		if (!StringUtil.isNotNullIsNotEmpty(this.docente.getSenha())) {
			setMessageError("Informe a senha");
			isValido = Boolean.FALSE;
		}
		if (!StringUtil.isNotNullIsNotEmpty(this.confirmarSenha)) {
			setMessageError("Informe a confimração da senha");
			isValido = Boolean.FALSE;
		}
		if (StringUtil.isNotNullIsNotEmpty(this.docente.getSenha())
				&& StringUtil.isNotNullIsNotEmpty(this.confirmarSenha)) {
			if (!this.docente.getSenha().equals(this.confirmarSenha)) {
				setMessageError("Senhas diferentes");
				isValido = Boolean.FALSE;
			}
		}
		return isValido;
	}

	public void editarDocente() {
		if (StringUtil.isNotNullIsNotEmpty(this.confirmarSenha)
				|| StringUtil.isNotNullIsNotEmpty(this.docente.getSenha())) {
			if (validarSenhaDocente()) {
				this.docente.setEndereco(this.docente.getEndereco());
				this.docenteDao.update(this.docente);
				cadastraHistoricoDocente("Foi alterado com sucesso.", this.docente);
				setMessageSuccess("Atualizado com sucesso!");
			}
		} else {
			this.docente.setSenha(this.senhaAtual);
			this.docente.setEndereco(this.docente.getEndereco());
			this.docenteDao.update(this.docente);
			cadastraHistoricoDocente("Foi alterado com sucesso.", this.docente);
			setMessageSuccess("Atualizado com sucesso!");
		}
	}


	private void inicializa() {
		this.alunos = new ArrayList<Aluno>();
		this.aluno = new Aluno();
		this.aluno.setNome("");
		this.aluno.setTelefoneFixo("");
		this.aluno.setSexo('\0');
		this.aluno.setCelular("");
		this.aluno.setMatricula("");
		this.aluno.setEmail("");
		this.aluno.setAtivo(false);
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
		this.historico = new Historico();
		this.historicoDao = new HistoricoDaoImpl();
		this.user = new Seguranca().getUsuarioLogado();
		this.docenteDao = new DocenteDaoImpl();
		this.tipoDocenteDao = new TipoDocenteDaoImpl();
		this.cursoDao = new CursoDaoImpl();
		this.enderecoDao = new EnderecoDaoImpl();
		this.tipoDocente = new TipoDocente();
		this.docentes = docenteDao.list();
		this.docente = new Docente();
		this.docente.setEndereco(new Endereco());
	}

	public TipoDocente getTipoDocente() {
		return tipoDocente;
	}

	public void setTipoDocente(TipoDocente tipoDocente) {
		this.tipoDocente = tipoDocente;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public void cadastraHistoricoAluno(String mensagem, Aluno aluno) {
		this.historico.setDataAlteracao(new Date());
		this.historico.setAluno(this.alunoDao.getAlunobyMatricula(user.getUsuario().getMatricula()));
		this.historico.setAlteracao(
				"Aluno: " + aluno.getNome() + "\n" + "Matrícula: " + aluno.getMatricula() + "\n" + mensagem + "\n");
		this.historicoDao.save(historico);
	}
	
	public void cadastraHistoricoDocente(String mensagem, Docente docente) {
		this.historico.setDataAlteracao(new Date());
		this.historico.setDocente(this.docenteDao.getDocentebyMatricula(user.getUsuario().getMatricula()));
		this.historico.setAlteracao("Docente: " + docente.getNome() + "\n" + "Matrícula: " + docente.getMatricula() + "\n"
				+ mensagem + "\n");
		this.historicoDao.save(historico);
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

		if (aluno.getEmail() == null || aluno.getEmail().trim().isEmpty()) {
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

	public DocenteDao getDocenteDao() {
		return docenteDao;
	}

	public void setDocenteDao(DocenteDao docenteDao) {
		this.docenteDao = docenteDao;
	}

	public String getVerificaSenha() {
		return verificaSenha;
	}

	public void setVerificaSenha(String verificaSenha) {
		this.verificaSenha = verificaSenha;
	}

	public UsuarioSistema getUser() {
		return user;
	}

	public void setUser(UsuarioSistema user) {
		this.user = user;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public List<TipoDocente> getTipoDocentes(){
		return tipoDocenteDao.list();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
	
	
}
