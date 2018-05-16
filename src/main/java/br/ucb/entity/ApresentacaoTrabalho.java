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
public class ApresentacaoTrabalho extends EntidadeBase {

	private static final long serialVersionUID = -556997155897130516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ApresentacaoTrabalho")
	private Integer idApresentacaoTrabalho;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "evento")
	private Integer evento;
	
	@Column(name = "instituicaoPromotora")
	private String instituicaoPromotora;
	
	@Column(name = "idioma")
	private Integer idioma;

	@Column(name = "local")
	private Integer local;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "pais")
	private String pais;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdApresentacaoTrabalho() {
		return this.idApresentacaoTrabalho;
	}

	public void setIdApresentacaoTrabalho(Integer idApresentacaoTrabalho) {
		this.idApresentacaoTrabalho = idApresentacaoTrabalho;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public Integer getEvento() {
		return this.evento;
	}

	public void setEvento(Integer evento) {
		this.evento = evento;
	}

	public String getInstituicaoPromotora() {
		return this.instituicaoPromotora;
	}

	public void setInstituicaoPromotora(String instituicaoPromotora) {
		this.instituicaoPromotora = instituicaoPromotora;
	}

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public Integer getLocal() {
		return this.local;
	}

	public void setLocal(Integer local) {
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
