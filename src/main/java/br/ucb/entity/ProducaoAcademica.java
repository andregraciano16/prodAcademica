package br.ucb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProducaoAcademica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_producaoAcademica;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "nomeRevista")
	private String nomeRevista;

	@Column(name = "paginaInicial")
	private Integer paginaIncial;

	@Column(name = "paginaFinal")
	private Integer paginaFinal;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "ISSN")
	private Integer ISSN;

	@Column(name = "conceitoQualis")
	private String conceitoQualis;

	@Column(name = "arquivo")
	private String arquivo;

	@Column(name = "dataCadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_tipoProducao")
	private TipoProducao tipoProducao;

	@ManyToOne
	@JoinColumn(name = "id_linhaPesquisa")
	private LinhaPesquisa linhaPesquisa;

	@ManyToOne
	@JoinColumn(name = "id_statusProducao")
	private StatusProducao statusProducao;

	

	public Integer getId_producaoAcademica() {
		return id_producaoAcademica;
	}

	public void setId_producaoAcademica(Integer id_producaoAcademica) {
		this.id_producaoAcademica = id_producaoAcademica;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeRevista() {
		return this.nomeRevista;
	}

	public void setNomeRevista(String nomeRevista) {
		this.nomeRevista = nomeRevista;
	}

	public Integer getPaginaIncial() {
		return this.paginaIncial;
	}

	public void setPaginaIncial(Integer paginaIncial) {
		this.paginaIncial = paginaIncial;
	}

	public Integer getPaginaFinal() {
		return this.paginaFinal;
	}

	public void setPaginaFinal(Integer paginaFinal) {
		this.paginaFinal = paginaFinal;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getISSN() {
		return this.ISSN;
	}

	public void setISSN(Integer iSSN) {
		this.ISSN = iSSN;
	}

	public String getConceitoQualis() {
		return this.conceitoQualis;
	}

	public void setConceitoQualis(String conceitoQualis) {
		this.conceitoQualis = conceitoQualis;
	}

	public String getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoProducao getTipoProducao() {
		return this.tipoProducao;
	}

	public void setTipoProducao(TipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return this.linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public StatusProducao getStatusProducao() {
		return this.statusProducao;
	}

	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}

}
