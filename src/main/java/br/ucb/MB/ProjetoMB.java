package br.ucb.MB;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.ProjetoDao;
import br.ucb.dao.StatusProjetoDao;
import br.ucb.dao.impl.ProjetoDaoImpl;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.StatusProjetoDaoImpl;
import br.ucb.dao.impl.TipoProjetoDaoImpl;
import br.ucb.entity.Projeto;
import br.ucb.entity.Aluno;
import br.ucb.entity.Docente;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.StatusProjeto;
import br.ucb.entity.TipoProjeto;
import br.ucb.enums.AcaoEnum;

@ManagedBean(name = "projetoMB")
@ViewScoped
public class ProjetoMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<Projeto> projetos;
	private Projeto projeto;
	private ProjetoDao projetoDao;
	private List<StatusProjeto> variosStatus;
	private List<TipoProjeto> variosTipos;
	private List<LinhaPesquisa> linhasPesquisa;
	private List<Docente> docentes;
	private List<Docente> docentesSelecionados;
	private Docente docenteSelecionado;
	private List<Aluno> alunos;
	private List<Aluno> alunosSelecionados;
	private AlunoDao alunoDao;
	private Aluno alunoSelecionado;
	private Docente temp;
	private DocenteDao docenteDao;
	private StatusProjetoDao statusProjetoDao;
	private TipoProjetoDaoImpl tipoProjetoDao;
	private LinhaPesquisaDaoImpl linhaPesquisaDao;
	private AcaoEnum acaoEnum;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.LISTAR);
	}

	public void cadastrar() {

		montar(this.projeto);
		if (!verificaVazio(this.projeto)) {

			if (this.projetos.contains(this.projeto)) {
				setMessageError("Já contém este registro, por favor insira um novo.");
			} else {
				this.projetoDao.save(this.projeto);
				setMessageSuccess("Cadastrado com sucesso.");
			}

		} else if (this.docentesSelecionados.contains(projeto.getDocenteResponsavel())) {
			setMessageError(
					"Este docente não pode ser um participante, pois já é o responsavel do projeto, por favor retire o mesmo da lista de docentes.");
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();
	}

	public void excluir(Projeto projeto) {
		this.projetoDao.remove(projeto);
		setMessageSuccess("Excluído com sucesso.");
		init();
	}

	public void editar() {

		if (this.projeto != null && !verificaVazio(this.projeto)) {
			this.projetoDao.update(this.projeto);
			setMessageSuccess("Atualizado com sucesso.");
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();

	}

	public void prepararEdicao(Projeto projeto) {
		this.projeto = projeto;
		this.docentesSelecionados = projeto.getDocentesParticipantes();
		this.alunosSelecionados = projeto.getAlunosParticipantes();
		acaoEnum = AcaoEnum.EDITAR;
	}

	public void visualizar(Projeto projeto) {
		this.projeto = projeto;
		this.docentesSelecionados = projeto.getDocentesParticipantes();
		this.alunosSelecionados = projeto.getAlunosParticipantes();
		acaoEnum = AcaoEnum.VISUALIZAR;
	}

	public List<Aluno> escolheAluno(String query) {
		List<Aluno> alunosFiltrados = new ArrayList<Aluno>();

		for (int i = 0; i < this.alunos.size(); i++) {
			Aluno aluno = this.alunos.get(i);
			if (aluno.getNome().toLowerCase().startsWith(query)) {
				alunosFiltrados.add(aluno);
			}
		}

		return alunosFiltrados;
	}

	public List<Aluno> escolheParticipanteAluno(String query) {
		List<Aluno> alunosFiltrados = new ArrayList<Aluno>();

		for (int i = 0; i < this.alunos.size(); i++) {
			Aluno aluno = this.alunos.get(i);
			if (aluno.getNome().toLowerCase().startsWith(query)) {
				alunosFiltrados.add(aluno);
			}
		}

		return alunosFiltrados;
	}

	public void guardaParticipanteAluno(Aluno selecionado) {
		if (selecionado != null && this.alunos.contains(selecionado)) {
			this.alunosSelecionados.add(selecionado);
			this.alunos.remove(selecionado);
		} else {
			if (selecionado == null)
				setMessageError("Esse aluno não existe.");
			if (selecionado != null && this.alunosSelecionados.contains(selecionado))
				setMessageError("Este aluno já foi selecionado.");
		}

	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void removeParticipanteAluno(Aluno selecionado) {
		this.alunosSelecionados.remove(selecionado);
		this.alunos.add(selecionado);
	}

	public void verificaDocenteResponsavel(Docente responsavel) {
		if (responsavel != null && this.docentes.contains(responsavel)) {
			this.docentes.remove(responsavel);
			this.temp = responsavel;
		} else if (responsavel == null && this.temp != null) {
			this.docentes.add(this.temp);
			this.temp = null;
		}
	}

	public List<Docente> escolheDocente(String query) {
		List<Docente> docentesFiltrados = new ArrayList<Docente>();

		for (int i = 0; i < this.docentes.size(); i++) {
			Docente docente = this.docentes.get(i);
			if (docente.getNome().toLowerCase().startsWith(query)) {
				docentesFiltrados.add(docente);
			}
		}

		return docentesFiltrados;
	}

	public List<Docente> escolheParticipanteDocente(String query) {
		List<Docente> docentesFiltrados = new ArrayList<Docente>();

		for (int i = 0; i < this.docentes.size(); i++) {
			Docente docente = this.docentes.get(i);
			if (docente.getNome().toLowerCase().startsWith(query)) {
				docentesFiltrados.add(docente);
			}
		}

		return docentesFiltrados;
	}

	public void guardaParticipanteDocente(Docente selecionado) {
		if (selecionado != null && this.docentes.contains(selecionado)) {
			this.docentesSelecionados.add(selecionado);
			this.docentes.remove(selecionado);
		} else {
			if (selecionado == null)
				setMessageError("Esse docente não existe ou já é o reponsável pelo projeto.");
			if (selecionado != null && this.docentesSelecionados.contains(selecionado))
				setMessageError("Este docente já foi selecionado.");
		}

	}

	public void removeParticipanteDocente(Docente selecionado) {
		this.docentesSelecionados.remove(selecionado);
		this.docentes.add(selecionado);
	}

	public void buscar() {

		if (this.projeto != null) {
			if (verificaVazioPesq(this.projeto)) {
				this.projetos = this.projetoDao.list();
				this.variosStatus = this.statusProjetoDao.list();
				this.variosTipos = this.tipoProjetoDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
				this.docentes = this.docenteDao.list();
				this.alunos = this.alunoDao.list();
			} else {
				this.projetos = this.projetoDao.findBySearch(this.projeto);
				this.variosStatus = this.statusProjetoDao.list();
				this.variosTipos = this.tipoProjetoDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
				this.docentes = this.docenteDao.list();
				this.alunos = this.alunoDao.list();
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
		this.projetos = new ArrayList<Projeto>();
		this.projeto = new Projeto();
		this.projeto.setNome("");
		this.projeto.setDescricao("");
		this.projeto.setOrgaoFinanciador("");
		this.projeto.setDataInicio(null);
		this.projeto.setStatusProjeto(null);
		this.projeto.setLinhaPesquisa(null);
		this.projeto.setTipoProjeto(null);
		this.projeto.setDocenteResponsavel(null);

		this.projeto.setAlunosParticipantes(null);
		this.projeto.setDocentesParticipantes(null);

		this.variosTipos = new ArrayList<TipoProjeto>();
		this.variosStatus = new ArrayList<StatusProjeto>();
		this.linhasPesquisa = new ArrayList<LinhaPesquisa>();
		this.docentesSelecionados = new ArrayList<Docente>();
		this.alunosSelecionados = new ArrayList<Aluno>();
		this.projetoDao = new ProjetoDaoImpl();
		this.statusProjetoDao = new StatusProjetoDaoImpl();
		this.tipoProjetoDao = new TipoProjetoDaoImpl();
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		this.docenteDao = new DocenteDaoImpl();
		this.alunoDao = new AlunoDaoImpl();

	}

	private void montar(Projeto projeto) {
		if (projeto == null) {
			projeto = new Projeto();
		}
		projeto.setIdProjeto(null);
		projeto.setNome(projeto.getNome().trim());
		projeto.setDescricao(projeto.getDescricao().trim());
		projeto.setOrgaoFinanciador(projeto.getOrgaoFinanciador().trim());
		projeto.setDocentesParticipantes(this.docentesSelecionados);
		projeto.setAlunosParticipantes(this.alunosSelecionados);

	}

	public void habilitarNovo() {
		this.acaoEnum = AcaoEnum.CADASTRAR;
	}

	private boolean verificaVazioPesq(Projeto projeto) {

		if (projeto.getNome() == null || projeto.getNome().trim().isEmpty() && projeto.getStatusProjeto() == null
				&& projeto.getTipoProjeto() == null && projeto.getLinhaPesquisa() == null
				&& projeto.getDocenteResponsavel() == null)
			return true;

		return false;

	}

	private boolean verificaVazio(Projeto projeto) {

		if (projeto.getNome() == null || projeto.getNome().trim().isEmpty()) {
			return true;
		}

		if (projeto.getDescricao() == null || projeto.getDescricao().trim().isEmpty()) {
			return true;
		}

		if (projeto.getOrgaoFinanciador() == null || projeto.getOrgaoFinanciador().trim().isEmpty()) {
			return true;
		}

		if (projeto.getStatusProjeto() == null) {
			return true;
		}

		if (projeto.getTipoProjeto() == null) {
			return true;
		}

		if (projeto.getLinhaPesquisa() == null) {
			return true;
		}

		if (projeto.getAlunosParticipantes() == null) {
			return true;
		}

		if (projeto.getDocentesParticipantes() == null) {
			return true;
		}

		if (projeto.getDocenteResponsavel() == null) {
			return true;
		}

		if (projeto.getDataInicio() == null) {
			return true;
		}

		return false;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public ProjetoDao getProjetoDao() {
		return projetoDao;
	}

	public void setProjetoDao(ProjetoDao projetoDao) {
		this.projetoDao = projetoDao;
	}

	public Projeto getProjeto() {
		return this.projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getTiposProjeto() {
		return projetos;
	}

	public void setTiposProjeto(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<StatusProjeto> getVariosStatus() {
		return variosStatus;
	}

	public void setVariosStatus(List<StatusProjeto> variosStatus) {
		this.variosStatus = variosStatus;
	}

	public StatusProjetoDao getStatusProjetoDao() {
		return statusProjetoDao;
	}

	public void setStatusProjetoDao(StatusProjetoDao statusProjetoDao) {
		this.statusProjetoDao = statusProjetoDao;
	}

	public AcaoEnum getAcaoEnum() {
		return acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}

	public List<TipoProjeto> getVariosTipos() {
		return variosTipos;
	}

	public void setVariosTipos(List<TipoProjeto> variosTipos) {
		this.variosTipos = variosTipos;
	}

	public List<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}

	public TipoProjetoDaoImpl getTipoProjetoDao() {
		return tipoProjetoDao;
	}

	public void setTipoProjetoDao(TipoProjetoDaoImpl tipoProjetoDao) {
		this.tipoProjetoDao = tipoProjetoDao;
	}

	public LinhaPesquisaDaoImpl getLinhaPesquisaDao() {
		return linhaPesquisaDao;
	}

	public void setLinhaPesquisaDao(LinhaPesquisaDaoImpl linhaPesquisaDao) {
		this.linhaPesquisaDao = linhaPesquisaDao;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public List<Docente> getDocentesSelecionados() {
		return docentesSelecionados;
	}

	public void setDocentesSelecionados(List<Docente> docentesSelecionados) {
		this.docentesSelecionados = docentesSelecionados;
	}

	public Docente getDocenteSelecionado() {
		return docenteSelecionado;
	}

	public void setDocenteSelecionado(Docente docenteSelecionado) {
		this.docenteSelecionado = docenteSelecionado;
	}

	public List<Aluno> getAlunosSelecionados() {
		return alunosSelecionados;
	}

	public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
		this.alunosSelecionados = alunosSelecionados;
	}

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

}
