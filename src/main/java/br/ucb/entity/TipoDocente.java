package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoDocente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoDocente;

	@Column(name = "tipo")
	private String tipo;

	public Integer getIdTipoDocente() {
		return this.idTipoDocente;
	}

	public void setIdTipoDocente(Integer tipoDocente) {
		this.idTipoDocente = tipoDocente;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}