package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Integer tipoParticipacao;
	
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

	public Integer getTipoParticipacao() {
		return this.tipoParticipacao;
	}

	public void setTipoParticipacao(Integer tipoParticipacao) {
		this.tipoParticipacao = tipoParticipacao;
	}
	
}
