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
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.entity.LinhaPesquisa;

@ManagedBean(name = "linhaPesquisaMB")
@ViewScoped
public class LinhaPesquisaMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<LinhaPesquisa> linhasPesquisa;
	private LinhaPesquisa linhaPesquisa;
	private LinhaPesquisaDao linhaPesquisaDao;
	private String msg;
	private LinhaPesquisa editavel;

	@PostConstruct
	public void init() {
		this.linhasPesquisa = new ArrayList<LinhaPesquisa>();
		this.linhaPesquisa = new LinhaPesquisa();
		this.linhaPesquisa.setDescricao("");
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		this.editavel = new LinhaPesquisa();
		buscar();
	}

	public void cadastrar(LinhaPesquisa linhaPesquisa) {
		if (this.linhaPesquisa.getDescricao() != null && !this.linhaPesquisa.getDescricao().trim().isEmpty()) {
			if (this.linhasPesquisa.contains(this.linhaPesquisa)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarLinhaPesquisa();
				this.linhaPesquisaDao.save(this.linhaPesquisa);
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

	private void montarLinhaPesquisa() {
		if (this.linhaPesquisa == null) {
			this.linhaPesquisa = new LinhaPesquisa();
		}
		this.linhaPesquisa.setIdLinhaPesquisa(null);
		this.linhaPesquisa.setDescricao(this.linhaPesquisa.getDescricao().trim());
	}

	public void excluir(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisaDao.remove(linhaPesquisa);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getDescricao() != null && !this.editavel.getDescricao().isEmpty()) {

			linhaPesquisa = (LinhaPesquisa) event.getObject();
			linhaPesquisa.setDescricao(editavel.getDescricao());
			this.linhaPesquisaDao.update(this.linhaPesquisa);
			msg = "Atualizado com sucesso.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		} else {
			msg = "Descrição não pode ficar vazia.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
		init();

	}

	public void cancela(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscar() {

		if (this.linhaPesquisa.getDescricao() != null) {
			if (!this.linhaPesquisa.getDescricao().isEmpty()) {
				this.linhasPesquisa = this.linhaPesquisaDao.findByDescricao(this.linhaPesquisa.getDescricao());
			} else if (this.linhaPesquisa.getDescricao().isEmpty()) {
				this.linhasPesquisa = this.linhaPesquisaDao.list();
			}
		}

	}

	public void limpar() {
		init();
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

	public LinhaPesquisa getEditavel() {
		return editavel;
	}

	public void setEditavel(LinhaPesquisa editavel) {
		this.editavel = editavel;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return this.linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

}
