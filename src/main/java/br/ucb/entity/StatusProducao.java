package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusProducao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_statusPRoducao;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	

	public Integer getId_statusPRoducao() {
		return id_statusPRoducao;
	}

	public void setId_statusPRoducao(Integer id_statusPRoducao) {
		this.id_statusPRoducao = id_statusPRoducao;
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
