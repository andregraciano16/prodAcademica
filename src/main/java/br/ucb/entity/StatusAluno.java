package br.ucb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class StatusAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_statusAluno;

	@Column(name = "descricao")
	private String descricao;


	public int getId_statusAluno() {
		return id_statusAluno;
	}

	public void setId_statusAluno(int id_statusAluno) {
		this.id_statusAluno = id_statusAluno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
