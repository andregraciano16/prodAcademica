package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public static List<NaturezaConteudoEnum> list(){
		return new ArrayList<NaturezaConteudoEnum>(Arrays.asList(NaturezaConteudoEnum.values()));
	}

}
