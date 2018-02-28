package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_projeto;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "orgaoFinanciador")
	private String orgaoFinanciador;

	@Column(name = "dadosOficiais")
	private String dadosOficiais;

	@ManyToOne
	@JoinColumn(name = "id_tipoProjeto")
	private TipoProjeto tipoProjeto;

	@ManyToOne
	@JoinColumn(name = "id_linhaPesquisa")
	private LinhaPesquisa linhaPesquisa;

	

	public Integer getId_projeto() {
		return id_projeto;
	}

	public void setId_projeto(Integer id_projeto) {
		this.id_projeto = id_projeto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOrgaoFinanciador() {
		return orgaoFinanciador;
	}

	public void setOrgaoFinanciador(String orgaoFinanciador) {
		this.orgaoFinanciador = orgaoFinanciador;
	}

	public String getDadosOficiais() {
		return dadosOficiais;
	}

	public void setDadosOficiais(String dadosOficiais) {
		this.dadosOficiais = dadosOficiais;
	}

	public TipoProjeto getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

}
