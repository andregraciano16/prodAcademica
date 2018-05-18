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
	private boolean resultado;

	@PostConstruct
	public void init() {
		this.variosStatus = new ArrayList<StatusProjeto>();
		this.statusProjeto = new StatusProjeto();
		this.statusProjeto.setDescricao("");
		this.statusProjetoDao = new StatusProjetoDaoImpl();
		buscar();
	}

	public void cadastrar(StatusProjeto statusProjeto) {
		if (this.statusProjeto.getDescricao() != null && !this.statusProjeto.getDescricao().trim().isEmpty()) {
			if (this.variosStatus.contains(this.statusProjeto)) {
				setMessageError("Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.");
			} else {
				montarStatusProjeto();
				this.resultado = this.statusProjetoDao.saveM(this.statusProjeto);
				if (this.resultado) {
					setMessageSuccess("Cadastrado com sucesso.");
				} else {
					setMessageError("Houve um erro ao salvar no sistema.");
				}
			}
		} else {
			setMessageError( "Preencha o campo corretamente.");
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
		this.resultado = this.statusProjetoDao.removeM(statusProjeto);
		if (this.resultado) {
			setMessageSuccess("Excluído com sucesso.");
		}else{
			setMessageError("Houve um erro ao deletar no sistema. Por favor, apague o histórico e qualquer relação com este registro.");
		}
		
		init();
	}

	public void editar(StatusProjeto statusProjeto) {

		if (statusProjeto.getDescricao() != null && !statusProjeto.getDescricao().trim().isEmpty()) {
			this.resultado = this.statusProjetoDao.updateM(statusProjeto);
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

	public StatusProjeto getStatusProjeto() {
		return this.statusProjeto;
	}

	public void setStatusProjeto(StatusProjeto statusProjeto) {
		this.statusProjeto = statusProjeto;
	}

}
