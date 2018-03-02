package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusProducao extends EntidadeBase {

	private static final long serialVersionUID = -1329686841106662549L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idStatusProducao;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	public Integer getIdStatusProducao() {
		return this.idStatusProducao;
	}

	public void setIdStatusProducao(Integer idStatusProducao) {
		this.idStatusProducao = idStatusProducao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
