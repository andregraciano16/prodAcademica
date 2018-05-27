package br.ucb.filtro;

import java.util.Date;

public class ProdAcFiltro {

	private String titulo;
	private String descricao;
	private Date dataCadastro;
	private Integer codigo;
	private Integer codigoParticipante;
	private String tipoAutor;
	
	public String getTipoAutor() {
		return this.tipoAutor;
	}

	public void setTipoAutor(String tipoAutor) {
		this.tipoAutor = tipoAutor;
	}

	public Integer getCodigoParticipante() {
		return this.codigoParticipante;
	}
	
	public void setCodigoParticipante(Integer codigoParticipante) {
		this.codigoParticipante = codigoParticipante;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
