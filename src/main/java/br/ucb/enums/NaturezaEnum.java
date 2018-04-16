package br.ucb.enums;

public enum NaturezaEnum {
	
	TRABALHO_COMPLETO (1, "Trabalho Completo"),
	RESUMO            (2, "Resumo");
	
	private Integer codigo;
	private String descricao;

	NaturezaEnum(Integer codigo, String descricao) {
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
