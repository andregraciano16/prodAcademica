package br.ucb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Historico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_historico;
	
	@Column(name = "data_alteracao")
	private Date data_alteracao;
	
	@ManyToOne
	@JoinColumn(name = "idAluno")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name = "idDocente")
	private Docente docente;
	
	@ManyToOne
	@JoinColumn(name = "idProducaoAcademica")
	private ProducaoAcademica producaoAcademica;
	
	@ManyToOne
	@JoinColumn(name = "idProjeto")
	private Projeto projeto;
	
	
	
	
	public int getId_historico() {
		return id_historico;
	}
	public void setId_historico(int id_historico) {
		this.id_historico = id_historico;
	}
	public Date getData_alteracao() {
		return data_alteracao;
	}
	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public ProducaoAcademica getProducaoAcademica() {
		return producaoAcademica;
	}
	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
	
	

}
