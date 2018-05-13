package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DesenvTecnica extends EntidadeBase {

	private static final long serialVersionUID = -5330955282171692076L;

	@Id
	@Column(name = "id_DesenTecnica")
	private Integer idDesenvTecnica;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "finalidade")
	private String finalidade;

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

	public Integer getIdDesenvTecnica() {
		return this.idDesenvTecnica;
	}

	public void setIdDesenvTecnica(Integer idDesenvTecnica) {
		this.idDesenvTecnica = idDesenvTecnica;
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

}
