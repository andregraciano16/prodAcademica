package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Autor extends EntidadeBase {

	private static final long serialVersionUID = -2584965643827235048L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAutor")
	private Integer idAutor;

	@Column(name = "tipoAutor")
	private String tipoAutor;

	@Column(name = "tipoAcao")
	private String tipoAcao;

	@Column(name = "codigoAutor")
	private Integer codAutor;

	@ManyToOne
	@JoinColumn(name = "id_producaoAcademica", insertable = true, updatable = true)
	private ProducaoAcademica producaoAcademica;
	
	public Integer getIdAutor() {
		return this.idAutor;
	}

	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}

	public String getTipoAutor() {
		return this.tipoAutor;
	}

	public void setTipoAutor(String tipoAutor) {
		this.tipoAutor = tipoAutor;
	}

	public String getTipoAcao() {
		return this.tipoAcao;
	}

	public void setTipoAcao(String tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public Integer getCodAutor() {
		return this.codAutor;
	}

	public void setCodAutor(Integer codAutor) {
		this.codAutor = codAutor;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

}
