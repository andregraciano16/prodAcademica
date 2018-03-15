package br.ucb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Aluno implements Serializable {

	private static final long serialVersionUID = 2290908420484970960L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aluno")
	private Integer idAluno;

	@Column(name = "nome")
	private String nome;

	@Column(name = "telefoneFixo")
	private String telefoneFixo;

	@Column(name = "celular")
	private String celular;

	@Column(name = "sexo")
	private char sexo;

	@Column(name = "dataNascimento")
	private Date dataNascimento;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "dataCadastro")
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "id_statusAluno")
	private StatusAluno statusAluno;

	public Integer getIdAluno() {
		return this.idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public StatusAluno getStatusAluno() {
		return statusAluno;
	}

	public void setStatusAluno(StatusAluno statusAluno) {
		this.statusAluno = statusAluno;
	}

	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Aluno) {
			Aluno outroAluno = (Aluno) obj;
			if (outroAluno.getNome().equals(this.getNome()) 
					&& outroAluno.getTelefoneFixo().equals(this.getTelefoneFixo())
					&& outroAluno.getCelular().equals(this.getCelular())
					&& outroAluno.getSexo() == this.getSexo()
					&& outroAluno.getDataNascimento().equals(this.getDataNascimento())
					&& outroAluno.getMatricula().equals(this.getMatricula())
					&& outroAluno.getDataCadastro().equals(this.getDataCadastro())
					&& outroAluno.getEndereco().equals(this.getEndereco())
					&& outroAluno.getCurso().equals(this.getCurso())
					&& outroAluno.getStatusAluno().equals(this.getStatusAluno())){
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
			if (!(obj instanceof Aluno))
				return false;
			Aluno other = (Aluno) obj;
			if (idAluno == null) {
				if (other.idAluno != null)
					return false;
			} else if (!idAluno.equals(other.idAluno))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getNome().hashCode();
	}
	
}
