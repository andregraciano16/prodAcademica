package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CartaMapaSimilares extends EntidadeBase {

	private static final long serialVersionUID = -8528479680843875588L;

	@Id
	@Column(name = "id_CartMapSim")
	private Integer idCartMapSim;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "tema")
	private String tema;

	@Column(name = "tecnica")
	private Integer tecnica;

	@Column(name = "finalidade")
	private Integer finalidade;

	@Column(name = "areaRepresentada")
	private String areaRepresentada;

	@Column(name = "instituicaoFinanc")
	private String instituicaoFinanc;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdCartMapSim() {
		return this.idCartMapSim;
	}

	public void setIdCartMapSim(Integer idCartMapSim) {
		this.idCartMapSim = idCartMapSim;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Integer getTecnica() {
		return this.tecnica;
	}

	public void setTecnica(Integer tecnica) {
		this.tecnica = tecnica;
	}

	public Integer getFinalidade() {
		return this.finalidade;
	}

	public void setFinalidade(Integer finalidade) {
		this.finalidade = finalidade;
	}

	public String getAreaRepresentada() {
		return this.areaRepresentada;
	}

	public void setAreaRepresentada(String areaRepresentada) {
		this.areaRepresentada = areaRepresentada;
	}

	public String getInstituicaoFinanc() {
		return this.instituicaoFinanc;
	}

	public void setInstituicaoFinanc(String instituicaoFinanc) {
		this.instituicaoFinanc = instituicaoFinanc;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

}
