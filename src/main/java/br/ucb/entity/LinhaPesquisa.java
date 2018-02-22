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
	private Integer idLinhaPesquisa;
	
	@Column(name = "descricao")
	private String descricao;

	public Integer getIdLinhaPesquisa() {
		return idLinhaPesquisa;
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

}
