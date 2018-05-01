package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArtigoPeriodico extends EntidadeBase {

	private static final long serialVersionUID = -4166932097562451516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ArtigoPeriodico")
	private Integer idArtigo;

	@Column(name = "natureza")
	private Integer natureza;  // Natureza Enum

	@Column(name = "ISSN")
	private String ISSN;

	@Column(name = "nomeEditora")
	private String nomeEditora;

	@Column(name = "cidadeEditora")
	private String cidadeEditora;

	@Column(name = "volume")
	private String volume;

	@Column(name = "faciculo")
	private String fasciculo;

	@Column(name = "serie")
	private String serie;

	@Column(name = "numPagInicial")
	private Integer numPagInicial;

	@Column(name = "numPagFim")
	private Integer numPagFim;

	@Column(name = "divulgacao")
	private Integer divulgacao;

	@Column(name = "URL")
	private String URL;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "DOI")
	private String DOI; // Digital Object Identify

	public Integer getIdArtigo() {
		return this.idArtigo;
	}

	public void setIdArtigo(Integer idArtigo) {
		this.idArtigo = idArtigo;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public String getISSN() {
		return this.ISSN;
	}

	public void setISSN(String iSSN) {
		this.ISSN = iSSN;
	}

	public String getNomeEditora() {
		return this.nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getCidadeEditora() {
		return this.cidadeEditora;
	}

	public void setCidadeEditora(String cidadeEditora) {
		this.cidadeEditora = cidadeEditora;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getFasciculo() {
		return this.fasciculo;
	}

	public void setFasciculo(String fasciculo) {
		this.fasciculo = fasciculo;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Integer getNumPagInicial() {
		return this.numPagInicial;
	}

	public void setNumPagInicial(Integer numPagInicial) {
		this.numPagInicial = numPagInicial;
	}

	public Integer getNumPagFim() {
		return this.numPagFim;
	}

	public void setNumPagFim(Integer numPagFim) {
		this.numPagFim = numPagFim;
	}

	public Integer getDivulgacao() {
		return this.divulgacao;
	}

	public void setDivulgacao(Integer divulgacao) {
		this.divulgacao = divulgacao;
	}

	public String getURL() {
		return this.URL;
	}

	public void setURL(String uRL) {
		this.URL = uRL;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getDOI() {
		return this.DOI;
	}

	public void setDOI(String dOI) {
		this.DOI = dOI;
	}

}
