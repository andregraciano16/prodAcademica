package br.ucb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Docente extends EntidadeBase implements Comparable<Docente> {

	private static final long serialVersionUID = 3273644027854708411L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Docente")
	private Integer idDocente;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "nome")
	private String nome;

	@Column(name = "situacao")
	private String situacao;

	@Column(name = "sexo")
	private char sexo;

	@Column(name = "celular")
	private String celular;

	@Column(name = "telefoneFixo")
	private String telefoneFixo;

	@Column(name = "email")
	private String email;

	@Column(name = "ativo")
	private boolean ativo;
	
	@Column(name = "senha")
	private String senha;

	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(name = "ramal")
	private String ramal;

	@Column(name = "dataCadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_tipoDocente")
	private TipoDocente tipoDocente;

	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	public Integer getIdDocente() {
		return this.idDocente;
	}

	public void setIdDocente(Integer idDocente) {
		this.idDocente = idDocente;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefoneFixo() {
		return this.telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRamal() {
		return this.ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoDocente getTipoDocente() {
		return this.tipoDocente;
	}

	public void setTipoDocente(TipoDocente tipoDocente) {
		this.tipoDocente = tipoDocente;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Docente) {
			Docente outroDocente = (Docente) obj;
			if (outroDocente.getNome().trim().equals(this.getNome().trim())) {
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
			if (!(obj instanceof Docente))
				return false;
			Docente other = (Docente) obj;
			if (idDocente == null) {
				if (other.idDocente != null)
					return false;
			} else if (!idDocente.equals(other.idDocente))
				return false;
			return true;
		}

	}
	
	@Override
	public int compareTo(Docente outro) {
		return getDataCadastro().compareTo(outro.getDataCadastro());
	}

}
