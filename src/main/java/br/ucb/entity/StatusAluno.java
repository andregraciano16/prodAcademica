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
	@Column(name = "id_statusAluno")
	private Integer idStatusAluno;

	@Column(name = "descricao")
	private String descricao;

	public Integer getIdStatusAluno() {
		return idStatusAluno;
	}

	public void setIdStatusAluno(Integer idStatusAluno) {
		this.idStatusAluno = idStatusAluno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof StatusAluno) {
			StatusAluno outroStatusAluno = (StatusAluno) obj;
			if (outroStatusAluno.getDescricao().trim().equals(this.getDescricao().trim())) {
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
			if (!(obj instanceof StatusAluno))
				return false;
			StatusAluno other = (StatusAluno) obj;
			if (idStatusAluno == null) {
				if (other.idStatusAluno != null)
					return false;
			} else if (!idStatusAluno.equals(other.idStatusAluno))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}
}
