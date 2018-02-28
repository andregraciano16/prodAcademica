package br.ucb.MB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.StatusAlunoDao;
import br.ucb.entity.StatusAluno;

@ManagedBean
@ViewScoped
public class StatusAlunoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private StatusAluno statusAluno;
	private List<StatusAluno> variosStatus;
	private StatusAluno editavel;

	public StatusAlunoMB() {
		this.statusAluno = new StatusAluno();
		this.setVariosStatus(new ArrayList<StatusAluno>());
		this.editavel = new StatusAluno();
	}

	public StatusAluno getStatusAluno() {
		return statusAluno;
	}

	public void setStatusAluno(StatusAluno statusAluno) {
		this.statusAluno = statusAluno;
	}

	public List<StatusAluno> getVariosStatus() {
		return this.variosStatus;
	}

	public void setVariosStatus(List<StatusAluno> variosStatus) {
		this.variosStatus = variosStatus;
	}

	public StatusAluno getEditavel() {
		return editavel;
	}

	public void setEditavel(StatusAluno editavel) {
		this.editavel = editavel;
	}

	public void cadastrarStatusAluno(StatusAluno statusAluno) {
		String msg;
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();

		msg = statusAlunoDAO.cadastrar(getStatusAluno());

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaVariosStatus();
	}

	public void buscaVariosStatus() {
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		this.variosStatus = statusAlunoDAO.buscaTodosStatus();
	}

	public void excluiStatusAluno(StatusAluno statusAluno) {
		String msg;
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		msg = statusAlunoDAO.excluir(statusAluno);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaVariosStatus();
	}

	public void editaStatusAluno(RowEditEvent event) {
		String msg;
		StatusAluno statusAluno = (StatusAluno) event.getObject();
		statusAluno.setDescricao(editavel.getDescricao());

		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		msg = statusAlunoDAO.alterar(statusAluno);
		buscaVariosStatus();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void cancelaEdit(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	
	public void buscaStatusPesquisa(StatusAluno statusAluno) {
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		this.variosStatus = statusAlunoDAO.buscaStatusPorPesquisa(statusAluno.getDescricao());
	}
}
