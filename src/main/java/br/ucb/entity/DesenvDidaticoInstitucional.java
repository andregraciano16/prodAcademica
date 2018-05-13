package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DesenvDidaticoInstitucional extends EntidadeBase {

	private static final long serialVersionUID = -7081465478910283287L;

	@Id
	@Column(name = "id_DesenvApp")
	private Integer idDesenvApp;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "finalidade")
	private String finalidade;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdDesenvApp() {
		return this.idDesenvApp;
	}

	public void setIdDesenvApp(Integer idDesenvApp) {
		this.idDesenvApp = idDesenvApp;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public String getFinalidade() {
		return this.finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

}
