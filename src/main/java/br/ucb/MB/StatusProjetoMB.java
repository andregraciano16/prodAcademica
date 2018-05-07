package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.StatusProjetoDao;
import br.ucb.dao.impl.StatusProjetoDaoImpl;
import br.ucb.entity.StatusProjeto;



@ManagedBean(name = "statusProjetoMB")
@ViewScoped
public class StatusProjetoMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<StatusProjeto> variosStatus;
	private StatusProjeto statusProjeto;
	private StatusProjetoDao statusProjetoDao;
	private String msg;
	private StatusProjeto editavel;
	

	@PostConstruct 
	public void init() {
		this.variosStatus = new ArrayList<StatusProjeto>();
		this.statusProjeto = new StatusProjeto();
		this.statusProjeto.setDescricao("");
		this.statusProjetoDao = new StatusProjetoDaoImpl();
		this.editavel = new StatusProjeto();
		buscar();
	}

	public void cadastrar(StatusProjeto statusProjeto) {
		if (this.statusProjeto.getDescricao() != null && !this.statusProjeto.getDescricao().trim().isEmpty()) {
			if (this.variosStatus.contains(this.statusProjeto)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarStatusProjeto();
				this.statusProjetoDao.save(this.statusProjeto);
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

	private void montarStatusProjeto() {
		if (this.statusProjeto == null) {
			this.statusProjeto = new StatusProjeto();
		}
		this.statusProjeto.setIdStatusProjeto(null);
		this.statusProjeto.setDescricao(this.statusProjeto.getDescricao().trim());
	}

	public void excluir(StatusProjeto statusProjeto) {
		this.statusProjetoDao.remove(statusProjeto);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getDescricao() != null && !this.editavel.getDescricao().isEmpty()) {

			statusProjeto = (StatusProjeto) event.getObject();
			statusProjeto.setDescricao(editavel.getDescricao());
			this.statusProjetoDao.update(this.statusProjeto);
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

		if (this.statusProjeto.getDescricao() != null) {
			if (!this.statusProjeto.getDescricao().isEmpty()) {
				this.variosStatus = this.statusProjetoDao.findByDescricao(this.statusProjeto.getDescricao());
			} else if (this.statusProjeto.getDescricao().isEmpty()) {
				this.variosStatus = this.statusProjetoDao.list();
			}
		}

	}

	public void limpar() {
		init();
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

	public StatusProjeto getEditavel() {
		return editavel;
	}

	public void setEditavel(StatusProjeto editavel) {
		this.editavel = editavel;
	}

	public StatusProjeto getStatusProjeto() {
		return this.statusProjeto;
	}

	public void setStatusProjeto(StatusProjeto statusProjeto) {
		this.statusProjeto = statusProjeto;
	}


}
