package br.ucb.MB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.entity.LinhaPesquisa;

@ManagedBean
@ViewScoped
public class LinhaPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private LinhaPesquisa linhaPesquisa;
	private List<LinhaPesquisa> linhasPesquisa;
	private LinhaPesquisa editavel;

	public LinhaPesquisaMB() {
		this.linhaPesquisa = new LinhaPesquisa();
		this.setLinhasPesquisa(new ArrayList<LinhaPesquisa>());
		this.editavel = new LinhaPesquisa();
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public List<LinhaPesquisa> getLinhasPesquisa() {
		return this.linhasPesquisa;
	}

	public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}

	public LinhaPesquisa getEditavel() {
		return editavel;
	}

	public void setEditavel(LinhaPesquisa editavel) {
		this.editavel = editavel;
	}

	public void cadastrarLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		String msg;
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		if (linhasPesquisa.contains(linhaPesquisa)) {
			msg = linhaPesquisaDAO.alterar(linhaPesquisa);
		} else {
			msg = linhaPesquisaDAO.cadastrar(getLinhaPesquisa());
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaLinhasPesquisa();
	}

	public void buscaLinhasPesquisa() {
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		this.linhasPesquisa = linhaPesquisaDAO.buscaTodos();
	}

	public void excluiLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		String msg;
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		msg = linhaPesquisaDAO.excluir(linhaPesquisa);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaLinhasPesquisa();
	}

	public void editaLinhaPesquisa(RowEditEvent event) {
		String msg;
		LinhaPesquisa linhaPesquisa = (LinhaPesquisa) event.getObject();
		linhaPesquisa.setDescricao(editavel.getDescricao());

		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		msg = linhaPesquisaDAO.alterar(linhaPesquisa);
		buscaLinhasPesquisa();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void cancelaEdit(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscaLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		this.linhasPesquisa = linhaPesquisaDAO.buscaLinhaPorPesquisa(linhaPesquisa.getDescricao());
	}

}
