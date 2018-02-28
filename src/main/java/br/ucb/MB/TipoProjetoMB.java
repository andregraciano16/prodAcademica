package br.ucb.MB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.TipoProjetoDao;
import br.ucb.entity.TipoProjeto;

@ManagedBean
@ViewScoped
public class TipoProjetoMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private TipoProjeto tipoProjeto;
	private List<TipoProjeto> tiposProjeto;
	private TipoProjeto editavel;
	


	public TipoProjetoMB() {
		this.tipoProjeto = new TipoProjeto();
		this.setTiposProjeto(new ArrayList<TipoProjeto>());
		this.editavel = new TipoProjeto();
	}

	public TipoProjeto getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	public List<TipoProjeto> getTiposProjeto() {
		return this.tiposProjeto;
	}

	public void setTiposProjeto(List<TipoProjeto> tiposProjeto) {
		this.tiposProjeto = tiposProjeto;
	}
	
	public TipoProjeto getEditavel() {
		return editavel;
	}

	public void setEditavel(TipoProjeto editavel) {
		this.editavel = editavel;
	}
	
	
	
	public void cadastrarTipoProjeto(TipoProjeto tipoProjeto) {
		String msg;
		TipoProjetoDao tipoProjetoDAO = new TipoProjetoDao();
		if (tiposProjeto.contains(tipoProjeto)) {
			msg = tipoProjetoDAO.alterar(tipoProjeto);
		} else {
			msg = tipoProjetoDAO.cadastrar(getTipoProjeto());
		}
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		
		buscaTiposProjeto();
	}

	public void buscaTiposProjeto() {
		TipoProjetoDao tipoProjetoDAO = new TipoProjetoDao();
		this.tiposProjeto = tipoProjetoDAO.buscaTodos();
	}

	public void excluiTipoProjeto(TipoProjeto tipoProjeto) {
		String msg;
		TipoProjetoDao tipoProjetoDAO = new TipoProjetoDao();
		msg = tipoProjetoDAO.excluir(tipoProjeto);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		
		buscaTiposProjeto();
	}


	public void editaTipoProjeto(RowEditEvent event){
		String msg;
		TipoProjeto tipoProjeto = (TipoProjeto) event.getObject();
		tipoProjeto.setTipo(editavel.getTipo());
		tipoProjeto.setDescricao(editavel.getDescricao());
		
		TipoProjetoDao tipoProjetoDAO = new TipoProjetoDao();
		msg = tipoProjetoDAO.alterar(tipoProjeto);
		buscaTiposProjeto();
		
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	
	public void cancelaEdit(RowEditEvent event){
		
		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
	

	public void buscaTipoProjeto(TipoProjeto tipoProjeto) {
		TipoProjetoDao tipoProjetoDAO = new TipoProjetoDao();
		this.tiposProjeto = tipoProjetoDAO.buscaPorPesquisa(tipoProjeto);
	}
	

}
