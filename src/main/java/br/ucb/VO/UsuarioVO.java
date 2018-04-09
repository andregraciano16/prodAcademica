package br.ucb.VO;

import java.io.Serializable;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private String senha;
	private String grupo;

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.matricula == null) ? 0 : this.matricula.hashCode());
		result = prime * result + ((this.senha == null) ? 0 : this.senha.hashCode());
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
		UsuarioVO other = (UsuarioVO) obj;
		if (this.matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!this.matricula.equals(other.matricula))
			return false;
		if (this.senha == null) {
			if (other.senha != null)
				return false;
		} else if (!this.senha.equals(other.senha))
			return false;
		return true;
	}

}
