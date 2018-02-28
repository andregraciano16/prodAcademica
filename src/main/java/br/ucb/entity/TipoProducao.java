package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoProducao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_tipoProducao;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	

	public Integer getId_tipoProducao() {
		return id_tipoProducao;
	}

	public void setId_tipoProducao(Integer id_tipoProducao) {
		this.id_tipoProducao = id_tipoProducao;
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
