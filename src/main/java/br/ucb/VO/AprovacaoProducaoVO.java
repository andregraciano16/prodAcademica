package br.ucb.VO;


import br.ucb.entity.StatusAprovacao;

public class AprovacaoProducaoVO {
	
	private Integer id;
	private String titulo;
	private String descricao;
	private StatusAprovacao statusAprovacao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public StatusAprovacao getStatusAprovacao() {
		return statusAprovacao;
	}
	public void setStatusAprovacao(StatusAprovacao statusAprovacao) {
		this.statusAprovacao = statusAprovacao;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AprovacaoProducaoVO other = (AprovacaoProducaoVO) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (this.id != other.id)
			return false;
		return true;
	}
	

}
