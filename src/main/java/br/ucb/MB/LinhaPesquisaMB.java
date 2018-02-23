package br.ucb.MB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.entity.LinhaPesquisa;

@ManagedBean
@ViewScoped
public class LinhaPesquisaMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private LinhaPesquisa linhaPesquisa;
	private List<LinhaPesquisa> linhasPesquisa;

	public LinhaPesquisaMB() {
		this.linhaPesquisa = new LinhaPesquisa();
		this.setLinhasPesquisa(new ArrayList<LinhaPesquisa>());
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

	public void cadastrarLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		if (linhasPesquisa.contains(linhaPesquisa)) {
			linhaPesquisaDAO.alterar(linhaPesquisa);
		} else {
			linhaPesquisaDAO.cadastrar(getLinhaPesquisa());
		}
		
		String msg = "Cadastrado com sucesso!.";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		
		buscaLinhasPesquisa();
	}

	public void buscaLinhasPesquisa() {
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		this.linhasPesquisa = linhaPesquisaDAO.buscaTodosStatus();
	}

	public void excluiLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		linhaPesquisaDAO.excluir(linhaPesquisa);
		buscaLinhasPesquisa();
	}

	public void editaLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		
		this.linhaPesquisa = linhaPesquisa;
		String msg = "Altere o valor como desejar e clique em cadastrar.";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		
	}

	public void buscaLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		this.linhasPesquisa = linhaPesquisaDAO.buscaStatusPorPesquisa(linhaPesquisa.getDescricao());
	}

}
