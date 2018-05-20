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
public class DesenvProduto extends EntidadeBase {

	private static final long serialVersionUID = -536116017760700024L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_DesenProd")
	private Integer idDesenvProd;

	@Column(name = "tipo")
	private Integer tipo;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "finalidade")
	private String finalidade;

	@Column(name = "registroPatente")
	private Integer registroPatente;

	@Column(name = "disponibilidade")
	private Integer disponibilidade;

	@Column(name = "instituicaoFinac")
	private String instituicaoFinanc;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "pais")
	private String pais;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdDesenvProd() {
		return this.idDesenvProd;
	}

	public void setIdDesenvProd(Integer idDesenvProd) {
		this.idDesenvProd = idDesenvProd;
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

	public Integer getRegistroPatente() {
		return this.registroPatente;
	}

	public void setRegistroPatente(Integer registroPatente) {
		this.registroPatente = registroPatente;
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

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
