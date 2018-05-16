package br.ucb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Editoria extends EntidadeBase{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_Editora")
	private Integer idEditora;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "idioma")
	private Integer idioma;

	@Column(name = "nroPag")
	private Integer nroPag;
	
	@Column(name = "instituicaoPromotora")
	private String instituicaoPromotora;

	@Column(name = "editora")
	private String editora;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "pais")
	private String pais;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdEditora() {
		return this.idEditora;
	}

	public void setIdEditora(Integer idEditora) {
		this.idEditora = idEditora;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public Integer getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Integer idioma) {
		this.idioma = idioma;
	}

	public Integer getNroPag() {
		return this.nroPag;
	}

	public void setNroPag(Integer nroPag) {
		this.nroPag = nroPag;
	}

	public String getInstituicaoPromotora() {
		return this.instituicaoPromotora;
	}

	public void setInstituicaoPromotora(String instituicaoPromotora) {
		this.instituicaoPromotora = instituicaoPromotora;
	}

	public String getEditora() {
		return this.editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
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
