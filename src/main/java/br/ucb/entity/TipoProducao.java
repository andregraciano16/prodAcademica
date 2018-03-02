package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoProducao extends EntidadeBase {

	private static final long serialVersionUID = -6311422799618464302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipoProducao")
	private Integer idTipoProducao;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descricao")
	private String descricao;

	public Integer getIdTipoProducao() {
		return this.idTipoProducao;
	}

	public void setIdTipoProducao(Integer idTipoProducao) {
		this.idTipoProducao = idTipoProducao;
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
