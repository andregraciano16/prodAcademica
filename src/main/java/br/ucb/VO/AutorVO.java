package br.ucb.VO;

public class AutorVO {

	private Integer id;
	private String tipo;
	private String nome;
	private String matricula;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.matricula == null) ? 0 : this.matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorVO other = (AutorVO) obj;
		if (this.matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!this.matricula.equals(other.matricula))
			return false;
		return true;
	}



}
