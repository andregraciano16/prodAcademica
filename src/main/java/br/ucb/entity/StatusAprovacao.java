package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusAprovacao extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_statusAprovacao")
	private Integer idStatusAprovacao;

	@Column(name = "descricao")
	private String descricao;

	public Integer getIdStatusAprovacao() {
		return idStatusAprovacao;
	}

	public void setIdStatusAprovacao(Integer idStatusAprovacao) {
		this.idStatusAprovacao = idStatusAprovacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	@Override
	public boolean equals(Object obj) {

	
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof StatusAprovacao))
				return false;
			StatusAprovacao other = (StatusAprovacao) obj;
			if (idStatusAprovacao == null) {
				if (other.idStatusAprovacao != null)
					return false;
			} else if (!idStatusAprovacao.equals(other.idStatusAprovacao))
				return false;
			return true;
		}

	

	public int hashCode() {
		return this.getDescricao().hashCode();
	}
	
	
}
