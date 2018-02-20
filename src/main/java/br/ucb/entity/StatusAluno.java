package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class StatusAluno {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStatusAluno;
	
	@Column(name = "descricao")
	private String descricao;
	
	
	
	public int getIdStatusAluno() {
		return idStatusAluno;
	}
	public void setIdStatusAluno(int idStatusAluno) {
		this.idStatusAluno = idStatusAluno;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
