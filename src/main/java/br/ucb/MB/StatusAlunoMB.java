package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.entity.StatusAluno;
import br.ucb.entity.StatusAluno;


public class StatusAlunoMB extends BaseMB{

	private static final long serialVersionUID = 1L;
	
	
	private List<StatusAluno> variosStatus;
	private StatusAluno statusAluno;
	private String descricao;
	private StatusAlunoDao statusAlunoDao;


	@PostConstruct
	public void init() {
		this.variosStatus = new ArrayList<StatusAluno>();
		this.statusAluno = new StatusAluno();
		this.descricao = null;
		this.statusAlunoDao = new StatusAlunoDaoImpl();
	}

	
	public void cadastrar() {
		if(this.descricao != null && !this.descricao.isEmpty()){
			montarStatusAluno();
			this.statusAlunoDao.save(this.statusAluno);
		}else{
			
		}
	}

	private void montarStatusAluno() {
		if(this.statusAluno == null){
			this.statusAluno = new StatusAluno();
		}
		this.statusAluno.setId_statusAluno(null);
		this.statusAluno.setDescricao(this.descricao);
	}

	public void excluir(StatusAluno statusAluno) {
	}

	public void editar(StatusAluno statusAluno) {

	}

	public void buscar(String descricao) {
	}

	public void limpar() {
		init();
	}

	public List<StatusAluno> getTipos() {
		return this.variosStatus;
	}

	public void setTipos(List<StatusAluno> variosStatus) {
		this.variosStatus = variosStatus;
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
