package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.StatusAprovacaoDao;
import br.ucb.dao.impl.StatusAprovacaoDaoImpl;
import br.ucb.entity.StatusAprovacao;

@ManagedBean(name = "statusAprovacaoMB")
@ViewScoped
public class StatusAprovacaoMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<StatusAprovacao> variosStatus;
	private StatusAprovacao statusAprovacao;
	private StatusAprovacaoDao statusAprovacaoDao;
	private boolean resultado;

	@PostConstruct
	public void init() {
		this.variosStatus = new ArrayList<StatusAprovacao>();
		this.statusAprovacao = new StatusAprovacao();
		this.statusAprovacao.setDescricao("");
		this.statusAprovacaoDao = new StatusAprovacaoDaoImpl();
		buscar();
	}

	public void cadastrar(StatusAprovacao statusAprovacao) {
		if (this.statusAprovacao.getDescricao() != null && !this.statusAprovacao.getDescricao().trim().isEmpty()) {
			if (this.variosStatus.contains(this.statusAprovacao)) {
				setMessageError(
						"Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.");
			} else {
				montarStatusAprovacao();
				this.resultado = this.statusAprovacaoDao.saveM(this.statusAprovacao);
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

	private void montarStatusAprovacao() {
		if (this.statusAprovacao == null) {
			this.statusAprovacao = new StatusAprovacao();
		}
		this.statusAprovacao.setIdStatusAprovacao(null);
		this.statusAprovacao.setDescricao(this.statusAprovacao.getDescricao().trim());
	}

	public void excluir(StatusAprovacao statusAprovacao) {
		this.resultado = this.statusAprovacaoDao.removeM(statusAprovacao);
		if (this.resultado) {
			setMessageSuccess("Excluído com sucesso.");
		} else {
			setMessageError(
					"Houve um erro ao deletar no sistema. Por favor, apague o histórico e qualquer relação com este registro.");
		}

		init();
	}

	public void editar(StatusAprovacao statusAprovacao) {

		if (statusAprovacao.getDescricao() != null && !statusAprovacao.getDescricao().isEmpty()) {

			this.resultado = this.statusAprovacaoDao.updateM(statusAprovacao);
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

		if (this.statusAprovacao.getDescricao() != null) {
			if (!this.statusAprovacao.getDescricao().isEmpty()) {
				this.variosStatus = this.statusAprovacaoDao.findByDescricao(this.statusAprovacao.getDescricao());
			} else if (this.statusAprovacao.getDescricao().isEmpty()) {
				this.variosStatus = this.statusAprovacaoDao.list();
			}
		}

	}

	public void limpar() {
		init();
	}

	public List<StatusAprovacao> getVariosStatus() {
		return variosStatus;
	}

	public void setVariosStatus(List<StatusAprovacao> variosStatus) {
		this.variosStatus = variosStatus;
	}

	public StatusAprovacaoDao getStatusAprovacaoDao() {
		return statusAprovacaoDao;
	}

	public void setStatusAprovacaoDao(StatusAprovacaoDao statusAprovacaoDao) {
		this.statusAprovacaoDao = statusAprovacaoDao;
	}

	public StatusAprovacao getStatusAprovacao() {
		return this.statusAprovacao;
	}

	public void setStatusAprovacao(StatusAprovacao statusAprovacao) {
		this.statusAprovacao = statusAprovacao;
	}

}
