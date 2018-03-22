package br.ucb.enums;

public enum TipoUsuarioEnum {

	DIRETOR(1, "Diretor"),
	ALUNO(2, "Aluno"),
	PROFESSOR(3, "Professor");
	
	TipoUsuarioEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	private Integer codigo;
	private String descricao;
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
