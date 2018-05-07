package br.ucb.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ArtigoJornalRevista extends EntidadeBase{

	private static final long serialVersionUID = 5923645802406787681L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_artigoJornalRevista")
	private Integer idArtigoJornalRevista;
	
	@Column(name = "dataPublicacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPublicacao;

	@Column(name = "numPagInicial")
	private Integer numPagInicial;

	@Column(name = "numPagFim")
	private Integer numPagFim;

	@Column(name = "idioma")
	private Integer idioma; // Idioma Enum

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "pais")
	private String pais;

	@Column(name = "ISSN")
	private String ISSN;

	@Column(name = "divulgacao")
	private Integer divulgacao; // DivulgacaoEnum

	@Column(name = "URL")
	private String URL;

	@Column(name = "observacao")
	private String observacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;
	
	public Integer getIdArtigoJornalRevista() {
		return this.idArtigoJornalRevista;
	}

	public void setIdArtigoJornalRevista(Integer idArtigoJornalRevista) {
		this.idArtigoJornalRevista = idArtigoJornalRevista;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public Date getDataPublicacao() {
		return this.dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
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

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getISSN() {
		return this.ISSN;
	}

	public void setISSN(String iSSN) {
		this.ISSN = iSSN;
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

}
