package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoDocente extends EntidadeBase {

	private static final long serialVersionUID = -398265338503322519L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoDocente;

	@Column(name = "tipo")
	private String tipo;

	public Integer getIdTipoDocente() {
		return idTipoDocente;
	}

	public void setIdTipoDocente(Integer idTipoDocente) {
		this.idTipoDocente = idTipoDocente;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
