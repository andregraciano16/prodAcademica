package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DisponibilidadeEnum {
	
	RESTRITA   (1, "Restrita"), 
	IRRESTRITA (2, "Irrestrita");
	
	private Integer codigo;
	private String descricao;

	DisponibilidadeEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<DisponibilidadeEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static DisponibilidadeEnum valueOf(Integer valor){
		for (DisponibilidadeEnum naturezaEnum : values()){
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
