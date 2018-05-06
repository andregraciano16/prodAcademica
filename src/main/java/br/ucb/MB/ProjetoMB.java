package br.ucb.MB;


import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.ProjetoDao;
import br.ucb.dao.StatusProjetoDao;
import br.ucb.dao.impl.ProjetoDaoImpl;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.StatusProjetoDaoImpl;
import br.ucb.dao.impl.TipoProjetoDaoImpl;
import br.ucb.entity.Projeto;
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
	private Projeto editavel;
	private List<StatusProjeto> variosStatus;
	private List<TipoProjeto> variosTipos;
	private List<LinhaPesquisa> linhasPesquisa;
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
		if (!verificaVazio(this.projeto)) {

			if (this.projetos.contains(this.projeto)) {
				setMessageError("Já contém este registro, por favor insira um novo.");
			} else {
				montar(this.projeto);
				this.projetoDao.save(this.projeto);
				setMessageSuccess("Cadastrado com sucesso.");
			}

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
		acaoEnum = AcaoEnum.EDITAR;
	}

	public void visualizar(Projeto projeto) {
		this.projeto = projeto;
		acaoEnum = AcaoEnum.VISUALIZAR;
	}

	public void buscar() {

		if (this.projeto != null) {
			if (verificaVazio(this.projeto)) {
				this.projetos = this.projetoDao.list();
				this.variosStatus = this.statusProjetoDao.list();
				this.variosTipos = this.tipoProjetoDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
			} else {
				this.projetos = this.projetoDao.findBySearch(this.projeto);
				this.variosStatus = this.statusProjetoDao.list();
				this.variosTipos = this.tipoProjetoDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
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
		this.projetoDao = new ProjetoDaoImpl();
		this.statusProjetoDao = new StatusProjetoDaoImpl();
		this.tipoProjetoDao = new TipoProjetoDaoImpl();
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		this.editavel = new Projeto();



	}

	private void montar(Projeto projeto) {
		if (projeto == null) {
			projeto = new Projeto();
		}
		projeto.setIdProjeto(null);
		projeto.setNome(projeto.getNome().trim());
		projeto.setDescricao(projeto.getDescricao().trim());
		projeto.setOrgaoFinanciador(projeto.getOrgaoFinanciador().trim());

	}

	public void habilitarNovo() {
		this.acaoEnum = AcaoEnum.CADASTRAR;
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

	public Projeto getEditavel() {
		return editavel;
	}

	public void setEditavel(Projeto editavel) {
		this.editavel = editavel;
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

	

}
