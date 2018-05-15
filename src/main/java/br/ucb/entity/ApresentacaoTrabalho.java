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
	
}
