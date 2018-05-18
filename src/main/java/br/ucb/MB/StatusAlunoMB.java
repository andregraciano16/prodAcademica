package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.entity.StatusAluno;

@ManagedBean(name = "statusAlunoMB")
@ViewScoped
public class StatusAlunoMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<StatusAluno> variosStatus;
	private StatusAluno statusAluno;
	private StatusAlunoDao statusAlunoDao;
	private boolean resultado;

	@PostConstruct
	public void init() {
		this.variosStatus = new ArrayList<StatusAluno>();
		this.statusAluno = new StatusAluno();
		this.statusAluno.setDescricao("");
		this.statusAlunoDao = new StatusAlunoDaoImpl();
		buscar();
	}

	public void cadastrar(StatusAluno statusAluno) {
		if (this.statusAluno.getDescricao() != null && !this.statusAluno.getDescricao().trim().isEmpty()) {
			if (this.variosStatus.contains(this.statusAluno)) {
				setMessageError(
						"Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.");
			} else {
				montarStatusAluno();
				this.resultado = this.statusAlunoDao.saveM(this.statusAluno);
				if (this.resultado) {
					setMessageSuccess("Cadastrado com sucesso.");
				} else {
					setMessageError("Houve um erro ao salvar no sistema.");
				}

			}
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();
	}

	private void montarStatusAluno() {
		if (this.statusAluno == null) {
			this.statusAluno = new StatusAluno();
		}
		this.statusAluno.setIdStatusAluno(null);
		this.statusAluno.setDescricao(this.statusAluno.getDescricao().trim());
	}

	public void excluir(StatusAluno statusAluno) {
		this.resultado = this.statusAlunoDao.removeM(statusAluno);
		if (this.resultado) {
			setMessageSuccess("Excluído com sucesso.");
		} else {
			setMessageError(
					"Houve um erro ao deletar no sistema. Por favor, apague o histórico e qualquer relação com este registro.");
		}

		init();
	}

	public void editar(StatusAluno statusAluno) {

		if (statusAluno.getDescricao() != null && !statusAluno.getDescricao().isEmpty()) {
			this.resultado = this.statusAlunoDao.updateM(statusAluno);
			if (this.resultado) {
				setMessageSuccess("Atualizado com sucesso.");
			} else {
				setMessageError("Houve um erro ao salvar no sistema.");
			}

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

		if (this.statusAluno.getDescricao() != null) {
			if (!this.statusAluno.getDescricao().isEmpty()) {
				this.variosStatus = this.statusAlunoDao.findByDescricao(this.statusAluno.getDescricao());
			} else if (this.statusAluno.getDescricao().isEmpty()) {
				this.variosStatus = this.statusAlunoDao.list();
			}
		}

	}

	public void limpar() {
		init();
	}

	public List<StatusAluno> getVariosStatus() {
		return variosStatus;
	}

	public void setVariosStatus(List<StatusAluno> variosStatus) {
		this.variosStatus = variosStatus;
	}

	public StatusAlunoDao getStatusAlunoDao() {
		return statusAlunoDao;
	}

	public void setStatusAlunoDao(StatusAlunoDao statusAlunoDao) {
		this.statusAlunoDao = statusAlunoDao;
	}

	public StatusAluno getStatusAluno() {
		return this.statusAluno;
	}

	public void setStatusAluno(StatusAluno statusAluno) {
		this.statusAluno = statusAluno;
	}

}
