package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.ExternoDao;
import br.ucb.dao.impl.ExternoDaoImpl;
import br.ucb.entity.Externo;

@ManagedBean(name = "externoMB")
@ViewScoped
public class ExternoMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<Externo> externos;
	private Externo externo;
	private ExternoDao externoDao;
	private boolean resultado;

	@PostConstruct
	public void init() {
		this.externos = new ArrayList<Externo>();
		this.externo = new Externo();
		this.externo.setNome("");
		this.externo.setTipoParticipacao("");
		this.externoDao = new ExternoDaoImpl();
		buscar();
	}

	public void cadastrar(Externo externo) {
		if (this.externo.getNome() != null && !this.externo.getNome().trim().isEmpty()) {
			if (this.externos.contains(this.externo)) {
				setMessageError(
						"Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.");
			} else {
				montarExterno();
				this.resultado = this.externoDao.saveM(this.externo);
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

	private void montarExterno() {
		if (this.externo == null) {
			this.externo = new Externo();
		}
		this.externo.setIdExterno(null);
		this.externo.setNome(this.externo.getNome().trim());
	}

	public void excluir(Externo externo) {
		this.resultado = this.externoDao.removeM(externo);
		if (this.resultado) {
			setMessageSuccess("Excluído com sucesso.");
		} else {
			setMessageError(
					"Houve um erro ao deletar no sistema. Por favor, apague o histórico e qualquer relação com este registro.");
		}

		init();
	}

	public void editar(Externo externo) {

		if (externo.getNome() != null && !externo.getNome().isEmpty()) {
			this.resultado = this.externoDao.updateM(externo);
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

		if (this.externo.getNome() != null) {
			if (!this.externo.getNome().isEmpty()) {
				this.externos = this.externoDao.findByNome(this.externo.getNome());
			} else if (this.externo.getNome().isEmpty()) {
				this.externos = this.externoDao.listDistinct();
			}
		}

	}

	public void limpar() {
		init();
	}

	public List<Externo> getExternos() {
		return externos;
	}

	public void setExternos(List<Externo> externos) {
		this.externos = externos;
	}

	public ExternoDao getExternoDao() {
		return externoDao;
	}

	public void setExternoDao(ExternoDao externoDao) {
		this.externoDao = externoDao;
	}

	public Externo getExterno() {
		return this.externo;
	}

	public void setExterno(Externo externo) {
		this.externo = externo;
	}

}
