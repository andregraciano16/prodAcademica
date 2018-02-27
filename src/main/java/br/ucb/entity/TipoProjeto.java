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
	private Integer id_tipoProjeto;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	

	public Integer getId_tipoProjeto() {
		return id_tipoProjeto;
	}

	public void setId_tipoProjeto(Integer id_tipoProjeto) {
		this.id_tipoProjeto = id_tipoProjeto;
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
		if (id_tipoProjeto == null){
			if (other.id_tipoProjeto != null)
				return false;
		} else if (!id_tipoProjeto.equals(other.id_tipoProjeto))
			return false;
		return true;
	}

	

}
