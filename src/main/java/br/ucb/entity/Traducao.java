package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Traducao extends EntidadeBase {

	private static final long serialVersionUID = -8144872626771244520L;

	@Id
	@Column(name = "id_traducao")
	private Integer idTraducao;

	@Column(name = "natureza")
	private Integer natureza;
	
	@Column(name = "autorTraduzido")
	private String autorTraduzido;

	@Column(name = "tituloObraOriginal")
	private String tituloObraOriginal;

	@Column(name = "idiomaObraOriginal")
	private Integer idiomaObraOriginal;

	@Column(name = "idiomaTraducao")
	private Integer idiomaTraducao;

	@Column(name = "nomeEditoraTraducao")
	private String nomeEditoraTraducao;

	@Column(name = "cidadeEditora")
	private String cidadeEditora;
	
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "nroEdicaoRevisao")
	private Integer nroEdicaoRevisao;
	
	@Column(name = "nroPaginas")
	private Integer nroPaginas;

	@Column(name = "volume")
	private String volume;

	@Column(name = "fasciculo")
	private String fasciculo;

	@Column(name = "serie")
	private String serie;

	@Column(name = "ISBN")
	private String ISBN;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;
	
	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public Integer getIdTraducao() {
		return this.idTraducao;
	}

	public void setIdTraducao(Integer idTraducao) {
		this.idTraducao = idTraducao;
	}

	public String getAutorTraduzido() {
		return this.autorTraduzido;
	}

	public void setAutorTraduzido(String autorTraduzido) {
		this.autorTraduzido = autorTraduzido;
	}

	public String getTituloObraOriginal() {
		return this.tituloObraOriginal;
	}

	public void setTituloObraOriginal(String tituloObraOriginal) {
		this.tituloObraOriginal = tituloObraOriginal;
	}

	public Integer getIdiomaObraOriginal() {
		return this.idiomaObraOriginal;
	}

	public void setIdiomaObraOriginal(Integer idiomaObraOriginal) {
		this.idiomaObraOriginal = idiomaObraOriginal;
	}

	public Integer getIdiomaTraducao() {
		return this.idiomaTraducao;
	}

	public void setIdiomaTraducao(Integer idiomaTraducao) {
		this.idiomaTraducao = idiomaTraducao;
	}

	public String getNomeEditoraTraducao() {
		return this.nomeEditoraTraducao;
	}

	public void setNomeEditoraTraducao(String nomeEditoraTraducao) {
		this.nomeEditoraTraducao = nomeEditoraTraducao;
	}

	public String getCidadeEditora() {
		return this.cidadeEditora;
	}

	public void setCidadeEditora(String cidadeEditora) {
		this.cidadeEditora = cidadeEditora;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getNroEdicaoRevisao() {
		return this.nroEdicaoRevisao;
	}

	public void setNroEdicaoRevisao(Integer nroEdicaoRevisao) {
		this.nroEdicaoRevisao = nroEdicaoRevisao;
	}

	public Integer getNroPaginas() {
		return this.nroPaginas;
	}

	public void setNroPaginas(Integer nroPaginas) {
		this.nroPaginas = nroPaginas;
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

	public String getISBN() {
		return this.ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}
	
}
