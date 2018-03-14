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
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.ProjetoDaoImpl;
import br.ucb.dao.impl.TipoProjetoDaoImpl;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Projeto;
import br.ucb.entity.TipoProjeto;


@ManagedBean(name="projetoMB")
@ViewScoped
public class ProjetoMB extends BaseMB{
	
	private static final long serialVersionUID = 1645825813671808166L;
	
	private List<Projeto> projetos;
	private Projeto projeto;
	private ProjetoDao projetoDao;
	private String msg;
	private Projeto editavel;
	private List<LinhaPesquisa> linhasPesquisa;
	private LinhaPesquisaDao linhaPesquisaDao;
	private List<TipoProjeto> tiposProjeto;
	private TipoProjetoDao tipoProjetoDao;
	
	
	@PostConstruct
	public void init() {
		this.projetos = new ArrayList<Projeto>();
		this.projeto = new Projeto();
		this.projeto.setDescricao("");
		this.projeto.setNome("");
		this.projeto.setOrgaoFinanciador("");
		this.projeto.setDadosOficiais("");
		this.projetoDao = new ProjetoDaoImpl();
		this.editavel = new Projeto();
		this.linhasPesquisa = new ArrayList<LinhaPesquisa>();
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		this.tiposProjeto = new ArrayList<TipoProjeto>();
		this.tipoProjetoDao = new TipoProjetoDaoImpl();
		buscar();
	}

	public void cadastrar(Projeto projeto) {
		if (this.projeto.getDescricao() != null && !this.projeto.getDescricao().trim().isEmpty() &&
				this.projeto.getNome() != null && !this.projeto.getNome().trim().isEmpty() &&
				this.projeto.getOrgaoFinanciador() != null && !this.projeto.getOrgaoFinanciador().trim().isEmpty() &&
				this.projeto.getDadosOficiais() != null && !this.projeto.getDadosOficiais().trim().isEmpty() &&
				this.projeto.getLinhaPesquisa() != null && this.projeto.getTipoProjeto() != null) {
			if (this.projetos.contains(this.projeto)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarProjeto();
				this.projetoDao.save(this.projeto);
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

	private void montarProjeto() {
		if (this.projeto == null) {
			this.projeto = new Projeto();
		}
		this.projeto.setIdProjeto(null);
		this.projeto.setDescricao(this.projeto.getDescricao().trim());
		this.projeto.setNome(this.projeto.getNome().trim());
		this.projeto.setDadosOficiais(this.projeto.getDadosOficiais().trim());
		this.projeto.setOrgaoFinanciador(this.projeto.getOrgaoFinanciador().trim());
		this.projeto.setLinhaPesquisa(this.projeto.getLinhaPesquisa());
		this.projeto.setTipoProjeto(this.projeto.getTipoProjeto());
	}

	public void excluir(Projeto projeto) {
		this.projetoDao.remove(projeto);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getDescricao() != null && !this.editavel.getDescricao().isEmpty()) {

			projeto = (Projeto) event.getObject();
			projeto.setDescricao(editavel.getDescricao());
			projeto.setNome(editavel.getNome());
			projeto.setDadosOficiais(editavel.getDadosOficiais());
			projeto.setOrgaoFinanciador(editavel.getOrgaoFinanciador());
			projeto.setLinhaPesquisa(editavel.getLinhaPesquisa());
			projeto.setTipoProjeto(editavel.getTipoProjeto());
			this.projetoDao.update(this.projeto);
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

		if (this.projeto.getDescricao() != null) {
			if (!this.projeto.getDescricao().isEmpty()) {
				this.projetos = this.projetoDao.findByDescricao(this.projeto.getDescricao());
				this.linhasPesquisa = this.linhaPesquisaDao.list();
			} else if (this.projeto.getDescricao().isEmpty()) {
				this.projetos = this.projetoDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
				this.tiposProjeto = this.tipoProjetoDao.list();
			}
		}

	}

	public void limpar() {
		init();
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

	public List<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}

	public LinhaPesquisaDao getLinhaPesquisaDao() {
		return linhaPesquisaDao;
	}

	public void setLinhaPesquisaDao(LinhaPesquisaDao linhaPesquisaDao) {
		this.linhaPesquisaDao = linhaPesquisaDao;
	}

	public TipoProjetoDao getTipoProjetoDao() {
		return tipoProjetoDao;
	}

	public void setTipoProjetoDao(TipoProjetoDao tipoProjetoDao) {
		this.tipoProjetoDao = tipoProjetoDao;
	}

	public List<TipoProjeto> getTiposProjeto() {
		return tiposProjeto;
	}

	public void setTiposProjeto(List<TipoProjeto> tiposProjeto) {
		this.tiposProjeto = tiposProjeto;
	}

}
