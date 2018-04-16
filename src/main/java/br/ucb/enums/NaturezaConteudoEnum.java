package br.ucb.enums;

public enum NaturezaConteudoEnum {
	
	DIDATICA              (1, "Didática"),
	TECNICA_MANUAL        (2, "Técnica/Manual"), 
	ARTISTICA             (3, "Artística"),
	TRADUCAO              (4, "Tradução"),
	RELATORIO             (5, "Relatorio"),
	PROFISSIONAL          (6, "Profissional"), 
	RESULTADO_PROJETO_PESQ(7, "Resultado de Projeto de Pesquisa");

	private Integer codigo;
	private String descricao;

	NaturezaConteudoEnum(Integer codigo, String descricao) {
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
