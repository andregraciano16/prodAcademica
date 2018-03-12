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
	@Column(name = "id_tipoProjeto")
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TipoProjeto))
			return false;
		TipoProjeto other = (TipoProjeto) obj;
		if (idTipoProjeto == null) {
			if (other.idTipoProjeto != null)
				return false;
		} else if (!idTipoProjeto.equals(other.idTipoProjeto))
			return false;
		return true;
	}

}
