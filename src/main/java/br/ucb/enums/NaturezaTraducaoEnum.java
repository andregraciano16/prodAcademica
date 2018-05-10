package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaTraducaoEnum {

	ARTIGO (1, "Artigo"),
	LIVRO  (2, "Livro"),
	OUTRO  (3, "Outro");
	
	private Integer codigo;
	private String descricao;

	NaturezaTraducaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaTraducaoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaTraducaoEnum valueOf(Integer valor){
		for (NaturezaTraducaoEnum naturezaEnum : values()){
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
