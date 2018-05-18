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

	@PostConstruct
	public void init() {
		this.linhasPesquisa = new ArrayList<LinhaPesquisa>();
		this.linhaPesquisa = new LinhaPesquisa();
		this.linhaPesquisa.setDescricao("");
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		buscar();
	}

	public void cadastrar(LinhaPesquisa linhaPesquisa) {
		if (this.linhaPesquisa.getDescricao() != null && !this.linhaPesquisa.getDescricao().trim().isEmpty()) {
			if (this.linhasPesquisa.contains(this.linhaPesquisa)) {
				setMessageError(
						"Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.");
			} else {
				montarLinhaPesquisa();
				this.linhaPesquisaDao.save(this.linhaPesquisa);
				setMessageSuccess("Cadastrado com sucesso.");
			}
		} else {
			setMessageError("Preencha os campos corretamente.");
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
		setMessageSuccess("Excluído com sucesso.");
		init();
	}

	public void editar(LinhaPesquisa linhaPesquisa) {

		if (linhaPesquisa.getDescricao() != null && !linhaPesquisa.getDescricao().isEmpty()) {

			this.linhaPesquisaDao.update(linhaPesquisa);
			setMessageSuccess("Atualizado com sucesso.");
		} else {
			setMessageError("Descrição não pode ficar vazia.");
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

	public LinhaPesquisa getLinhaPesquisa() {
		return this.linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

}
