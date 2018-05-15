package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TrabalhoEmAnais extends EntidadeBase {

	private static final long serialVersionUID = -1561399442320796929L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trabalho_em_anais")
	private Integer idTrabalhoEmAnais;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "edicaoNumero")
	private Integer edicaoNumero;

	@Column(name = "nomeEvvento")
	private String nomeEvento;

	@Column(name = "ISBN")
	private String ISBN;

	@Column(name = "pais")
	private String pais;

	@Column(name = "tituloDosAnais")
	private String tituloDosAnais;

	@Column(name = "volume")
	private String volume;

	@Column(name = "fasciculo")
	private String fasciculo;

	@Column(name = "serie")
	private String serie;

	@Column(name = "nroPagInicial")
	private Integer nroPagInicial;

	@Column(name = "nroPagFinal")
	private Integer nroPagFinal;

	@Column(name = "cidadeEvento")
	private String cidadeEvento;

	@Column(name = "idioma")
	private Integer idioma;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;
	
	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public Integer getIdTrabalhoEmAnais() {
		return this.idTrabalhoEmAnais;
	}

	public void setIdTrabalhoEmAnais(Integer idTrabalhoEmAnais) {
		this.idTrabalhoEmAnais = idTrabalhoEmAnais;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public Integer getEdicaoNumero() {
		return this.edicaoNumero;
	}

	public void setEdicaoNumero(Integer edicaoNumero) {
		this.edicaoNumero = edicaoNumero;
	}

	public String getNomeEvento() {
		return this.nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getISBN() {
		return this.ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTituloDosAnais() {
		return this.tituloDosAnais;
	}

	public void setTituloDosAnais(String tituloDosAnais) {
		this.tituloDosAnais = tituloDosAnais;
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

	public Integer getNroPagInicial() {
		return this.nroPagInicial;
	}

	public void setNroPagInicial(Integer nroPagInicial) {
		this.nroPagInicial = nroPagInicial;
	}

	public Integer getNroPagFinal() {
		return this.nroPagFinal;
	}

	public void setNroPagFinal(Integer nroPagFinal) {
		this.nroPagFinal = nroPagFinal;
	}

	public String getCidadeEvento() {
		return this.cidadeEvento;
	}

	public void setCidadeEvento(String cidadeEvento) {
		this.cidadeEvento = cidadeEvento;
	}

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

}
