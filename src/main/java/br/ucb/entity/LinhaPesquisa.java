package br.ucb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LinhaPesquisa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_linhaPesquisa")
	private Integer idLinhaPesquisa;

	@Column(name = "descricao")
	private String descricao;

	public Integer getIdLinhaPesquisa() {
		return this.idLinhaPesquisa;
	}

	public void setIdLinhaPesquisa(Integer idLinhaPesquisa) {
		this.idLinhaPesquisa = idLinhaPesquisa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LinhaPesquisa))
			return false;
		LinhaPesquisa other = (LinhaPesquisa) obj;
		if (idLinhaPesquisa == null) {
			if (other.idLinhaPesquisa != null)
				return false;
		} else if (!idLinhaPesquisa.equals(other.idLinhaPesquisa))
			return false;
		return true;
	}

}
