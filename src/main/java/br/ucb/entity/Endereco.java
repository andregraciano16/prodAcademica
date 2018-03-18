package br.ucb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco extends EntidadeBase{

	private static final long serialVersionUID = -5144079654456437059L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer idEndereco;

	@Column(name = "estado")
	private String estado;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "rua")
	private String rua;

	@Column(name = "numero")
	private Integer numero;


	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getRua() {
		return this.rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public boolean equals(Object obj) {

		int flag = 0;
		if (obj instanceof Endereco) {
			Endereco outroEndereco = (Endereco) obj;
			if (outroEndereco.getCidade().trim().equals(this.getCidade().trim())) {
				//flag = 1;
			}
		}
		if (flag == 1) {

			return true;

		} else {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Endereco))
				return false;
			Endereco other = (Endereco) obj;
			if (idEndereco == null) {
				if (other.idEndereco != null)
					return false;
			} else if (!idEndereco.equals(other.idEndereco))
				return false;
			return true;
		}

	}

	public int hashCode() {
		return this.getCidade().hashCode();
	}
}
