package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusProjeto extends EntidadeBase{

	private static final long serialVersionUID = -7830058223256345949L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_statusProjeto")
	private Integer idStatusProjeto;

	@Column(name = "descricao")
	private String descricao;
	
	
	
	public Integer getIdStatusProjeto() {
		return idStatusProjeto;
	}

	public void setIdStatusProjeto(Integer idStatusProjeto) {
		this.idStatusProjeto = idStatusProjeto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof StatusProjeto) {
			StatusProjeto outroStatusProjeto = (StatusProjeto) obj;
			if (outroStatusProjeto.getDescricao().trim().equals(this.getDescricao().trim())) {
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
			if (!(obj instanceof StatusProjeto))
				return false;
			StatusProjeto other = (StatusProjeto) obj;
			if (idStatusProjeto == null) {
				if (other.idStatusProjeto != null)
					return false;
			} else if (!idStatusProjeto.equals(other.idStatusProjeto))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getDescricao().hashCode();
	}

	
}
