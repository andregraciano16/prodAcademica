package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaTrabalhoAnaisEnum {

	TRABALHO_COMPLETO (1, "Trabalho Completo"),
	RESUMO            (2, "Resumo"),
	RESUMO_EXPANDIDO  (3, "Resumo Expandido");
	
	private Integer codigo;
	private String descricao;

	NaturezaTrabalhoAnaisEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaTrabalhoAnaisEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaTrabalhoAnaisEnum valueOf(Integer valor){
		for (NaturezaTrabalhoAnaisEnum naturezaEnum : values()){
             if(valor.equals(naturezaEnum.getCodigo())){
            	 return naturezaEnum;
             }
		}
		return null;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
}
