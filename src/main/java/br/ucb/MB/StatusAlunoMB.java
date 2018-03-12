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
import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.StatusAluno;


@ManagedBean(name = "statusAlunoMB")
@ViewScoped
public class StatusAlunoMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<StatusAluno> variosStatus;
	private StatusAluno statusAluno;
	private String descricao;
	private StatusAlunoDao statusAlunoDao;
	private String msg;
	private StatusAluno editavel;
	

	@PostConstruct public void init() {
		this.variosStatus = new ArrayList<StatusAluno>();
		this.statusAluno = new StatusAluno();
		this.descricao = null;
	//	this.statusAlunoDao = new StatusAlunoDaoImpl();
		this.editavel = new StatusAluno();
	}

	public void cadastrar() {
		if (this.descricao != null && !this.descricao.isEmpty()) {
			montarStatusAluno();
			this.statusAlunoDao.save(this.statusAluno);
		} else {
			msg = "Descrição não pode ficar vazia.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
		buscar();
	}

	private void montarStatusAluno() {
		if (this.statusAluno == null) {
			this.statusAluno = new StatusAluno();
		}
		this.statusAluno.setIdStatusAluno(null);
		this.statusAluno.setDescricao(this.descricao);
	}

	public void excluir(StatusAluno statusAluno) {
	}

	public void editar(StatusAluno statusAluno, RowEditEvent event) {
		
		LinhaPesquisa linhaPesquisa = (LinhaPesquisa) event.getObject();
		linhaPesquisa.setDescricao(editavel.getDescricao());
		
		LinhaPesquisaDao linhaPesquisaDAO = new LinhaPesquisaDao();
		msg = linhaPesquisaDAO.alterar(linhaPesquisa);
		//buscaLinhasPesquisa();
		
		if (this.descricao != null && !this.descricao.isEmpty()) {
			montarStatusAluno();
			this.statusAlunoDao.save(this.statusAluno);
		} else {
			msg = "Descrição não pode ficar vazia.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}

	public void buscar() {
		
		if (this.descricao != null) {
			if (!this.descricao.isEmpty()) {
				//this.descricao = this.tipoDocenteDao.findByTipo(this.descricao);
			} else if (this.descricao.isEmpty()) {
				this.variosStatus = this.statusAlunoDao.list();
			}
		}
		this.variosStatus = this.statusAlunoDao.list();
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

	public StatusAluno getEditavel() {
		return editavel;
	}

	public void setEditavel(StatusAluno editavel) {
		this.editavel = editavel;
	}

	public StatusAluno getStatusAluno() {
		return this.statusAluno;
	}

	public void setStatusAluno(StatusAluno statusAluno) {
		this.statusAluno = statusAluno;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
