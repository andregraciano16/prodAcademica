package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaCartaEnum {

	AEROFOTOGRAMA (1, "Aerofotograma"), 
	CARTA         (2, "Carta"), 
	FOTOGRAMA     (3, "Fotograma"), 
	MAPA          (4, "Mapa"), 
	OUTRO         (5, "Outro"); 
	
	private Integer codigo;
	private String descricao;

	NaturezaCartaEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaCartaEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaCartaEnum valueOf(Integer valor){
		for (NaturezaCartaEnum naturezaEnum : values()){
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
