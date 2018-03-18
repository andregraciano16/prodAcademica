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
	@Column(name = "id_Curso")
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

	@Override
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Curso) {
			Curso outroCurso = (Curso) obj;
			if (outroCurso.getNome().trim().equals(this.getNome().trim())) {
				flag = 1;
			}
		}
		if (flag == 1) {

			return true;

		} else {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Curso))
				return false;
			Curso other = (Curso) obj;
			if (idCurso == null) {
				if (other.idCurso != null)
					return false;
			} else if (!idCurso.equals(other.idCurso))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getNome().hashCode();
	}
}
