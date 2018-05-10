package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoDesenvolvimentoProdutoEnum {

	PILOTO      (1, "Piloto "), 
	PROJETO     (2, "Projeto"),  
	PROTOTIPO   (3, "Prototipo"), 
	OUTRO       (4, "Outro");
	
	private Integer codigo;
	private String descricao;

	TipoDesenvolvimentoProdutoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<TipoDesenvolvimentoProdutoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static TipoDesenvolvimentoProdutoEnum valueOf(Integer valor){
		for (TipoDesenvolvimentoProdutoEnum naturezaEnum : values()){
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
