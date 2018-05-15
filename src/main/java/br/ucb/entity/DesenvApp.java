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
public class DesenvApp  extends EntidadeBase{

	private static final long serialVersionUID = -206001031329406206L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_DesenvApp")
	private Integer idDesenvApp;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "finalidade")
	private String finalidade;

	@Column(name = "plataforma")
	private String plataforma;

	@Column(name = "ambiente")
	private String ambiente;

	@Column(name = "registroDreto")
	private Integer registroDreto;

	@Column(name = "autoral")
	private String autoral;

	@Column(name = "disponibilidade")
	private String disponibilidade;

	@Column(name = "instituiFinanc")
	private String instituiFinanc;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdDesenvApp() {
		return this.idDesenvApp;
	}

	public void setIdDesenvApp(Integer idDesenvApp) {
		this.idDesenvApp = idDesenvApp;
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

	public String getPlataforma() {
		return this.plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getAmbiente() {
		return this.ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public Integer getRegistroDreto() {
		return this.registroDreto;
	}

	public void setRegistroDreto(Integer registroDreto) {
		this.registroDreto = registroDreto;
	}

	public String getAutoral() {
		return this.autoral;
	}

	public void setAutoral(String autoral) {
		this.autoral = autoral;
	}

	public String getDisponibilidade() {
		return this.disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public String getInstituiFinanc() {
		return this.instituiFinanc;
	}

	public void setInstituiFinanc(String instituiFinanc) {
		this.instituiFinanc = instituiFinanc;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}
	
}
