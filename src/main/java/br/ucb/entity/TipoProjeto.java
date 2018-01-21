package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoProjeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoProjeto;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	public Integer getIdTipoProjeto() {
		return idTipoProjeto;
	}

	public void setIdTipoProjeto(Integer idTipoProjeto) {
		this.idTipoProjeto = idTipoProjeto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
