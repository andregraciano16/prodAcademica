package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaDesenvolvimentoAppEnum {

	COMPUTACIONAL (1, "Computacional"), 
	MULTIMIDIA    (2, "Multimídia"), 
	OUTRO         (3, "Outro");
	
	private Integer codigo;
	private String descricao;

	NaturezaDesenvolvimentoAppEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaDesenvolvimentoAppEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaDesenvolvimentoAppEnum valueOf(Integer valor){
		for (NaturezaDesenvolvimentoAppEnum naturezaEnum : values()){
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
