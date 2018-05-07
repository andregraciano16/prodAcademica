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
public class Livro extends EntidadeBase{

	private static final long serialVersionUID = -4964118914599297413L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	private Integer idLivro;

	@Column(name = "ISBN")
	private String ISBN;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "nroPagina")
	private Integer nroPagina;

	@Column(name = "divulgacao")
	private Integer divulgacao; // DivulgacaoEnum

	@Column(name = "URL")
	private String URL;

	@Column(name = "idioma")
	private Integer idioma; // Idioma Enum

	@Column(name = "cidadePais")
	private String cidadePais;

	@Column(name = "naturezaConteudo") // Naturaza conteudo enum
	private Integer naturezaConteudo;

	@Column(name = "tipoContribuicaoObra")
	private Integer tipoContribuicaoObra;// tipoContribuicaoObra ENUM

	@Column(name = "tipoEditora")
	private Integer tipoEditora;   //tipoEditora enum
	
	@Column(name = "nomeEditora")
	private String nomeEditora;

	@Column(name = "financiador")
	private String financiador;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;
	
	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public Integer getIdLivro() {
		return this.idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public String getISBN() {
		return this.ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getNroPagina() {
		return this.nroPagina;
	}

	public void setNroPagina(Integer nroPagina) {
		this.nroPagina = nroPagina;
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

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public String getCidadePais() {
		return this.cidadePais;
	}

	public void setCidadePais(String cidadePais) {
		this.cidadePais = cidadePais;
	}

	public Integer getNaturezaConteudo() {
		return this.naturezaConteudo;
	}

	public void setNaturezaConteudo(Integer naturezaConteudo) {
		this.naturezaConteudo = naturezaConteudo;
	}

	public Integer getTipoContribuicaoObra() {
		return this.tipoContribuicaoObra;
	}

	public void setTipoContribuicaoObra(Integer tipoContribuicaoObra) {
		this.tipoContribuicaoObra = tipoContribuicaoObra;
	}

	public Integer getTipoEditora() {
		return this.tipoEditora;
	}

	public void setTipoEditora(Integer tipoEditora) {
		this.tipoEditora = tipoEditora;
	}

	public String getFinanciador() {
		return this.financiador;
	}

	public void setFinanciador(String financiador) {
		this.financiador = financiador;
	}

	public String getNomeEditora() {
		return this.nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
}
