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
public class CursoCurtaDuracao extends EntidadeBase {

	private static final long serialVersionUID = -4049967392894645758L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_CursoCurtaDuracao")
	private Integer idCursoCurtaDuracao;

	@Column(name = "nivel")
	private Integer nivel;

	@Column(name = "duracao")
	private Integer duracao;

	@Column(name = "instiPromotoraOuEvento")
	private String instiPromotoraOuEvento;

	@Column(name = "local")
	private String local;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "pais")
	private String pais;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdCursoCurtaDuracao() {
		return this.idCursoCurtaDuracao;
	}

	public void setIdCursoCurtaDuracao(Integer idCursoCurtaDuracao) {
		this.idCursoCurtaDuracao = idCursoCurtaDuracao;
	}

	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getDuracao() {
		return this.duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public String getInstiPromotoraOuEvento() {
		return this.instiPromotoraOuEvento;
	}

	public void setInstiPromotoraOuEvento(String instiPromotoraOuEvento) {
		this.instiPromotoraOuEvento = instiPromotoraOuEvento;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
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
