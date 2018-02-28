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
	private Integer id_linhaPesquisa;
	
	@Column(name = "descricao")
	private String descricao;

	
	public Integer getId_linhaPesquisa() {
		return id_linhaPesquisa;
	}

	public void setId_linhaPesquisa(Integer id_linhaPesquisa) {
		this.id_linhaPesquisa = id_linhaPesquisa;
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
		if (id_linhaPesquisa == null){
			if (other.id_linhaPesquisa != null)
				return false;
		} else if (!id_linhaPesquisa.equals(other.id_linhaPesquisa))
			return false;
		return true;
	}

}
