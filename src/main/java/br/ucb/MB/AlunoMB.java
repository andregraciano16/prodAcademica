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
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.dao.impl.HistoricoDaoImpl;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.Curso;
import br.ucb.entity.Endereco;
import br.ucb.entity.Historico;
import br.ucb.entity.StatusAluno;
import br.ucb.enums.AcaoEnum;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;
import br.ucb.util.StringUtil;

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
	private Historico historico;
	private HistoricoDao historicoDao;
	private UsuarioSistema user;
	private DocenteDao docenteDao;
	private String verificaSenha;
	private String senhaAtual;
	private String verificaMatricula;
	private boolean resultado;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.LISTAR);
	}

	public void cadastrar() {
		if (!verificaVazio(this.aluno, this.endereco)) {

			if (this.alunos.contains(this.aluno)) {
				setMessageError("Esta matricula já está cadastrada, por favor insira uma nova.");
			} else if (!this.aluno.getSenha().equals(verificaSenha)) {
				setMessageError("Os campos de senha estão diferentes, por favor, insira novamente.");
			} else {
				montar(this.aluno, this.endereco);
				this.resultado = this.alunoDao.saveM(this.aluno);
				if (this.resultado) {
					setMessageSuccess("Cadastrado com sucesso.");
					cadastraHistorico("Foi cadastrado com sucesso.",
							this.alunoDao.getAlunobyMatricula(this.aluno.getMatricula()));
					init();
				} else
					setMessageError("Houve um erro no sistema ao salvar.");
			}

		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		
	}

	public void excluir(Aluno aluno) {
		if (aluno.isAtivo()) {
			aluno.setAtivo(Boolean.FALSE);
			this.alunoDao.update(aluno);
			cadastraHistorico("Foi desativado.", aluno);
			setMessageSuccess("Inativado com sucesso!");
		} else {
			setMessageError("Aluno já está inativado!");
		}
		init();
	}

	public void editar() {
		
		if (!this.verificaMatricula.equals(this.aluno.getMatricula())){
			this.alunos.remove(this.aluno);
		}
		
		if (this.aluno != null && !verificaVazio(this.aluno, this.endereco)) {
			if (!this.verificaMatricula.equals(this.aluno.getMatricula()) && this.alunos.contains(this.aluno)) {
				setMessageError("Esta matricula já está cadastrada, por favor insira uma nova.");
				
			} else if (StringUtil.isNotNullIsNotEmpty(this.verificaSenha)
					|| StringUtil.isNotNullIsNotEmpty(this.aluno.getSenha())) {
				
				if (validarSenhaAluno()) {
					this.enderecoDao.update(this.endereco);
					this.resultado = this.alunoDao.updateM(this.aluno);
					if (this.resultado) {
						cadastraHistorico("Foi alterado com sucesso.", this.aluno);
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
					cadastraHistorico("Foi alterado com sucesso.", this.aluno);
					setMessageSuccess("Atualizado com sucesso.");
					init();
				} else
					setMessageError("Houve um erro ao salvar no sistema.");
			}
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		
		if(!this.verificaMatricula.equals(this.aluno.getMatricula())){
			this.alunos = this.alunoDao.list();
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

	public void prepararEdicao(Aluno aluno) {
		this.aluno = aluno;
		this.senhaAtual = aluno.getSenha();
		this.endereco = aluno.getEndereco();
		this.verificaMatricula = aluno.getMatricula();
		acaoEnum = AcaoEnum.EDITAR;
	}

	public void visualizar(Aluno aluno) {
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

	public void limparCadastro() {
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.CADASTRAR);
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
		aluno.setEmail(aluno.getEmail().trim());
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

	public void habilitarNovo() {
		this.acaoEnum = AcaoEnum.CADASTRAR;
	}

	public void cadastraHistorico(String mensagem, Aluno aluno) {
		this.historico.setDataAlteracao(new Date());
		this.historico.setAluno(aluno);
		this.historico.setDocente(this.docenteDao.getDocentebyMatricula(user.getUsuario().getMatricula()));
		this.historico.setAlteracao("Aluno: " + aluno.getNome() + "\n" + "Matrícula: " + aluno.getMatricula() + "\n"
				+ mensagem + "\n" + "Responsável: " + this.historico.getDocente().getNome());
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

}
