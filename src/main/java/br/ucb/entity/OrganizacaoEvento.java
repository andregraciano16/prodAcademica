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
public class OrganizacaoEvento extends EntidadeBase {

	private static final long serialVersionUID = -843657141940244555L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_OrganizacaoEvento")
	private Integer idOganizacaoEvento;

	@Column(name = "tipo")
	private Integer tipo;

	@Column(name = "natureza")
	private Integer natureza;

	@Column(name = "instituicaoPromotora")
	private String instituicaoPromotora;

	@Column(name = "duracao")
	private Integer duracao;

	@Column(name = "itinerante")
	private Integer itinerante;

	@Column(name = "catalogo")
	private Integer catalogo;

	@Column(name = "local")
	private String local;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "pais")
	private String pais;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;

	public Integer getIdOganizacaoEvento() {
		return this.idOganizacaoEvento;
	}

	public void setIdOganizacaoEvento(Integer idOganizacaoEvento) {
		this.idOganizacaoEvento = idOganizacaoEvento;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getNatureza() {
		return this.natureza;
	}

	public void setNatureza(Integer natureza) {
		this.natureza = natureza;
	}

	public String getInstituicaoPromotora() {
		return this.instituicaoPromotora;
	}

	public void setInstituicaoPromotora(String instituicaoPromotora) {
		this.instituicaoPromotora = instituicaoPromotora;
	}

	public Integer getDuracao() {
		return this.duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Integer getItinerante() {
		return this.itinerante;
	}

	public void setItinerante(Integer itinerante) {
		this.itinerante = itinerante;
	}

	public Integer getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Integer catalogo) {
		this.catalogo = catalogo;
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
