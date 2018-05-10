package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NivelCursoCurtaDuaracaoEnum {
	
	EXTENSAO        (1, "Extensão"), 
	APERFEICOAMENTO (2, "Aperfeiçoamento"), 
	ESPECIALIZACAO  (3, "Especialização");
	
	private Integer codigo;
	private String descricao;

	NivelCursoCurtaDuaracaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NivelCursoCurtaDuaracaoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NivelCursoCurtaDuaracaoEnum valueOf(Integer valor){
		for (NivelCursoCurtaDuaracaoEnum naturezaEnum : values()){
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
