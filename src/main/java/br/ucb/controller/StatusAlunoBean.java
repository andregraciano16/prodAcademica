package br.ucb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.ucb.DAO.StatusAlunoDAO;
import br.ucb.entity.StatusAluno;

@ManagedBean
@ApplicationScoped
public class StatusAlunoBean {

	private StatusAluno statusAluno;
	private List<StatusAluno> variosStatus;
	
	
	

	public StatusAlunoBean() {
		this.statusAluno = new StatusAluno();
		this.setVariosStatus(new ArrayList<StatusAluno>());
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

	
	
	
	
	
	public void cadastrarStatusAluno(StatusAluno statusAluno) {
		StatusAlunoDAO statusAlunoDAO = new StatusAlunoDAO();
		if(variosStatus.contains(statusAluno)){
			statusAlunoDAO.alterar(statusAluno);
		}else{
			statusAlunoDAO.cadastrar(getStatusAluno());
		}
		buscaVariosStatus();
	}

	
	public void buscaVariosStatus(){
		StatusAlunoDAO statusAlunoDAO = new StatusAlunoDAO();
		this.variosStatus = statusAlunoDAO.buscaTodosStatus();
	}
	
	
	public void excluiStatusAluno(StatusAluno statusAluno){
		StatusAlunoDAO statusAlunoDAO = new StatusAlunoDAO();
		statusAlunoDAO.excluir(statusAluno);
		buscaVariosStatus();
	}
	
	public void editaStatusAluno(StatusAluno statusAluno){
		this.statusAluno = statusAluno;
	}

	
	public void buscaStatusPesquisa(StatusAluno statusAluno){
		StatusAlunoDAO statusAlunoDAO = new StatusAlunoDAO();
		this.variosStatus = statusAlunoDAO.buscaStatusPorPesquisa(statusAluno.getDescricao());
	}
}
