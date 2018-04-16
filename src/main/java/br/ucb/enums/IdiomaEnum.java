package br.ucb.enums;

public enum IdiomaEnum {

	PORTUGUES (1, "Português"),
	INGLES    (2, "Inglês"),
	FRANCES   (3, "Francês"),
	ESPANHOL  (4, "Espanhol"),
	CHINES    (5, "Chinês"), 
	JAPONES   (6, "Japonês"),
	ITALIANO  (7, "Italiano");

	private Integer codigo;
	private String descricao;
	
	IdiomaEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
