package br.ucb.MB;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.TipoProjetoDao;
import br.ucb.dao.impl.TipoProjetoDaoImpl;
import br.ucb.entity.TipoProjeto;


@ManagedBean(name = "tipoProjetoMB")
@ViewScoped
public class TipoProjetoMB extends BaseMB{
	
	private static final long serialVersionUID = 1L;

	private List<TipoProjeto> tiposProjeto;
	private TipoProjeto tipoProjeto;
	private TipoProjetoDao tipoProjetoDao;
	private String msg;
	private TipoProjeto editavel;

	@PostConstruct
	public void init() {
		this.tiposProjeto = new ArrayList<TipoProjeto>();
		this.tipoProjeto = new TipoProjeto();
		this.tipoProjeto.setTipo("");
		this.tipoProjeto.setDescricao("");
		this.tipoProjetoDao = new TipoProjetoDaoImpl();
		this.editavel = new TipoProjeto();
		buscar();
	}

	public void cadastrar(TipoProjeto tipoProjeto) {
		if (this.tipoProjeto.getDescricao() != null && !this.tipoProjeto.getDescricao().trim().isEmpty() &&
				this.tipoProjeto.getTipo() != null && !this.tipoProjeto.getTipo().trim().isEmpty()) {
			if (this.tiposProjeto.contains(this.tipoProjeto)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarTipoProjeto();
				this.tipoProjetoDao.save(this.tipoProjeto);
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

	private void montarTipoProjeto() {
		if (this.tipoProjeto == null) {
			this.tipoProjeto = new TipoProjeto();
		}
		this.tipoProjeto.setIdTipoProjeto(null);
		this.tipoProjeto.setTipo(this.tipoProjeto.getTipo().trim());
		this.tipoProjeto.setDescricao(this.tipoProjeto.getDescricao().trim());
	}

	public void excluir(TipoProjeto tipoProjeto) {
		this.tipoProjetoDao.remove(tipoProjeto);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getDescricao() != null && !this.editavel.getDescricao().isEmpty()) {

			tipoProjeto = (TipoProjeto) event.getObject();
			tipoProjeto.setTipo(editavel.getTipo());
			tipoProjeto.setDescricao(editavel.getDescricao());
			this.tipoProjetoDao.update(this.tipoProjeto);
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

		if (this.tipoProjeto.getDescricao() != null) {
			if (this.tipoProjeto.getDescricao().isEmpty() && this.tipoProjeto.getTipo().isEmpty()) {
				this.tiposProjeto = this.tipoProjetoDao.list();
			}else{
				this.tiposProjeto = this.tipoProjetoDao.findBySearch(this.tipoProjeto);
			}
		}

	}

	public void limpar() {
		init();
	}

	public List<TipoProjeto> getTiposProjeto() {
		return tiposProjeto;
	}

	public void setTiposProjeto(List<TipoProjeto> tiposProjeto) {
		this.tiposProjeto = tiposProjeto;
	}

	public TipoProjetoDao getTipoProjetoDao() {
		return tipoProjetoDao;
	}

	public void setTipoProjetoDao(TipoProjetoDao tipoProjetoDao) {
		this.tipoProjetoDao = tipoProjetoDao;
	}

	public TipoProjeto getEditavel() {
		return editavel;
	}

	public void setEditavel(TipoProjeto editavel) {
		this.editavel = editavel;
	}

	public TipoProjeto getTipoProjeto() {
		return this.tipoProjeto;
	}

	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}
}
