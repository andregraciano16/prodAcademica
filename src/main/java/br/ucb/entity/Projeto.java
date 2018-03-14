package br.ucb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Projeto implements Serializable{

	private static final long serialVersionUID = -5861079694379582651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_projeto")
	private Integer idProjeto;

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

	public Integer getIdProjeto() {
		return this.idProjeto;
	}

	public void setIdProjeto(Integer idProjeto) {
		this.idProjeto = idProjeto;
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
	
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Projeto) {
			Projeto outroProjeto = (Projeto) obj;
			if (outroProjeto.getDescricao().trim().equals(this.getDescricao().trim()) 
					&& outroProjeto.getNome().trim().equals(this.getNome().trim())
					&& outroProjeto.getDadosOficiais().trim().equals(this.getDadosOficiais().trim())
					&& outroProjeto.getOrgaoFinanciador().trim().equals(this.getOrgaoFinanciador().trim())
					&& outroProjeto.getLinhaPesquisa().equals(this.getLinhaPesquisa()) 
					&& outroProjeto.getTipoProjeto().equals(this.getTipoProjeto())) {
				flag = 1;
			}
		}
		if (flag == 1) {

			return true;

		} else {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Projeto))
				return false;
			Projeto other = (Projeto) obj;
			if (idProjeto == null) {
				if (other.idProjeto != null)
					return false;
			} else if (!idProjeto.equals(other.idProjeto))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}

}
