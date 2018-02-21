package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.StatusAlunoDao;
import br.ucb.entity.StatusAluno;

@ManagedBean
@ViewScoped
public class StatusAlunoMB {

	private StatusAluno statusAluno;
	private List<StatusAluno> variosStatus;
	
	
	

	public StatusAlunoMB() {
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
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		if(variosStatus.contains(statusAluno)){
			statusAlunoDAO.alterar(statusAluno);
		}else{
			statusAlunoDAO.cadastrar(getStatusAluno());
		}
		buscaVariosStatus();
	}

	
	public void buscaVariosStatus(){
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		this.variosStatus = statusAlunoDAO.buscaTodosStatus();
	}
	
	
	public void excluiStatusAluno(StatusAluno statusAluno){
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		statusAlunoDAO.excluir(statusAluno);
		buscaVariosStatus();
	}
	
	public void editaStatusAluno(StatusAluno statusAluno){
		this.statusAluno = statusAluno;
	}

	
	public void buscaStatusPesquisa(StatusAluno statusAluno){
		StatusAlunoDao statusAlunoDAO = new StatusAlunoDao();
		this.variosStatus = statusAlunoDAO.buscaStatusPorPesquisa(statusAluno.getDescricao());
	}
}
