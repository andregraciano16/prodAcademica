package br.ucb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idComentario;
	
	@Column(name = "dataCadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "descricao")
	private String decricao;
	
	@Column(name = "titulo")
	private String titulo;
	
    @ManyToMany
    @JoinColumn(name = "idAluno")
	private Aluno aluno;
    
    @ManyToMany
    @JoinColumn(name = "idDocente")
	private Docente docente;
	
}
