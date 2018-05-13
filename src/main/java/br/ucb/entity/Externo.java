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
public class Externo  extends EntidadeBase{

	private static final long serialVersionUID = 695112666397285760L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_externo")
	private Integer idExterno;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = " tipoParticipacao")
	private String tipoParticipacao;
		
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_producaoAcademica")
	private ProducaoAcademica producaoAcademica;
	
	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public Integer getIdExterno() {
		return this.idExterno;
	}

	public void setIdExterno(Integer idExterno) {
		this.idExterno = idExterno;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoParticipacao() {
		return this.tipoParticipacao;
	}

	public void setTipoParticipacao(String tipoParticipacao) {
		this.tipoParticipacao = tipoParticipacao;
	}
	
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Externo) {
			Externo outroExterno = (Externo) obj;
			if (outroExterno.getNome().trim().equals(this.getNome().trim())) {
				// flag = 1;
			}
		}
		if (flag == 1) {

			return true;

		} else {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Externo))
				return false;
			Externo other = (Externo) obj;
			if (idExterno == null) {
				if (other.idExterno != null)
					return false;
			} else if (!idExterno.equals(other.idExterno))
				return false;
			return true;
		}

	}
	
}
