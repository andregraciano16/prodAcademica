package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RelatorioPesquisa extends EntidadeBase{

	private static final long serialVersionUID = -6977617844004920218L;
	
	@Id
	@Column(name = "id_RelatorioPesquisa")
	private Integer idRelatorioPesquisa;

	@Column(name = "projetoPesquisa")
	private String projetoPesquisa;

	@Column(name = "nroPag")
	private Integer nroPag;

	@Column(name = "idioma")
	private Integer idioma;

	@Column(name = "disponibilidade")
	private Integer disponibilidade;

	@Column(name = "instituicaoFinac")
	private String instituicaoFinanc;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdRelatorioPesquisa() {
		return this.idRelatorioPesquisa;
	}

	public void setIdRelatorioPesquisa(Integer idRelatorioPesquisa) {
		this.idRelatorioPesquisa = idRelatorioPesquisa;
	}

	public String getProjetoPesquisa() {
		return this.projetoPesquisa;
	}

	public void setProjetoPesquisa(String projetoPesquisa) {
		this.projetoPesquisa = projetoPesquisa;
	}

	public Integer getNroPag() {
		return this.nroPag;
	}

	public void setNroPag(Integer nroPag) {
		this.nroPag = nroPag;
	}

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public Integer getDisponibilidade() {
		return this.disponibilidade;
	}

	public void setDisponibilidade(Integer disponibilidade) {
		this.disponibilidade = disponibilidade;
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
