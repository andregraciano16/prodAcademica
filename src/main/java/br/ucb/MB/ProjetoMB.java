package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.ProjetoDao;
import br.ucb.dao.TipoProjetoDao;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Projeto;
import br.ucb.entity.TipoProjeto;

@ManagedBean
@ViewScoped
public class ProjetoMB {
	
	private Projeto projeto;
	private List<Projeto> projetos;
	private Projeto editavel;
	private List<TipoProjeto> tiposProjeto;
	private List<LinhaPesquisa> linhasPesquisa;

	
	
	public ProjetoMB() {
		this.projeto = new Projeto();
		this.setProjetos(new ArrayList<Projeto>());
		this.editavel = new Projeto();
		this.setLinhasPesquisa(new ArrayList<LinhaPesquisa>());
		this.setTiposProjeto(new ArrayList<TipoProjeto>());
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getProjetos() {
		return this.projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Projeto getEditavel() {
		return editavel;
	}

	public void setEditavel(Projeto editavel) {
		this.editavel = editavel;
	}

	public List<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}
	
	public List<TipoProjeto> getTiposProjeto() {
		return tiposProjeto;
	}

	public void setTiposProjeto(List<TipoProjeto> tiposProjeto) {
		this.tiposProjeto = tiposProjeto;
	}


	@PostConstruct
	public void init() {
		LinhaPesquisaDao linhaPesquisaDao = new LinhaPesquisaDao();
		this.linhasPesquisa = linhaPesquisaDao.buscaTodos();
		TipoProjetoDao tipoProjetoDao = new TipoProjetoDao();
		this.tiposProjeto = tipoProjetoDao.buscaTodos();
	}

	public void cadastrarProjeto(Projeto projeto) {
		String msg;
		ProjetoDao projetoDAO = new ProjetoDao();
		if (projetos.contains(projeto)) {
			msg = projetoDAO.alterar(projeto);
		} else {
			msg = projetoDAO.cadastrar(getProjeto());
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaProjetos();
	}

	public void buscaProjetos() {
		ProjetoDao projetoDAO = new ProjetoDao();
		this.projetos = projetoDAO.buscaTodos();
	}

	public void excluiProjeto(Projeto projeto) {
		String msg;
		ProjetoDao projetoDAO = new ProjetoDao();
		msg = projetoDAO.excluir(projeto);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaProjetos();
	}

	public void editaProjeto(RowEditEvent event) {
		String msg;
		Projeto projeto = (Projeto) event.getObject();
		projeto.setNome(editavel.getNome());
		projeto.setDescricao(editavel.getDescricao());
		projeto.setOrgaoFinanciador(editavel.getOrgaoFinanciador());
		projeto.setDadosOficiais(editavel.getDadosOficiais());
		projeto.setTipoProjeto(editavel.getTipoProjeto());
		projeto.setLinhaPesquisa(editavel.getLinhaPesquisa());
		ProjetoDao projetoDAO = new ProjetoDao();
		msg = projetoDAO.alterar(projeto);
		buscaProjetos();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void cancelaEdit(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscaProjeto(Projeto projeto) {
		ProjetoDao projetoDAO = new ProjetoDao();
		this.projetos = projetoDAO.buscaProjetoPorPesquisa(projeto.getDescricao());
	}


}
