package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Curso extends EntidadeBase{

	private static final long serialVersionUID = -8630276346310370218L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurso;

	@Column(name = "nome")
	private String nome;

	
	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer id_curso) {
		this.idCurso = id_curso;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
