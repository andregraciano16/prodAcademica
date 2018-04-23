package br.ucb.enums;

public enum DivulgacaoEnum {
    //livro e artigo
	DIGITAL(1, "Digital"),
	IMPRESSA(2, "Impressa"),
	//artigo	
	MEIO_MAGNETICO(3,"Meio Magnético"),
	FILME(4, "Filme"), 
	HIPERTEXTO(5, "Hipertexto"),
	OUTROS(6, "Outros"),
	VARIOS(7, "Vários");

	private Integer codigo;
	private String descricao;

	DivulgacaoEnum(Integer codigo, String descricao) {
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
