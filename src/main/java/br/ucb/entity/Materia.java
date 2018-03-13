package br.ucb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Materia implements Serializable {

	private static final long serialVersionUID = 7191366208075701262L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia")
	private Integer idMateria;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_linhaPesquisa")
	private LinhaPesquisa linhaPesquisa;

	public Integer getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Materia) {
			Materia outroMateria = (Materia) obj;
			if (outroMateria.getDescricao().trim().equals(this.getDescricao().trim()) 
					&& outroMateria.getLinhaPesquisa().equals(this.getLinhaPesquisa())) {
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
			if (!(obj instanceof Materia))
				return false;
			Materia other = (Materia) obj;
			if (idMateria == null) {
				if (other.idMateria != null)
					return false;
			} else if (!idMateria.equals(other.idMateria))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}
}
