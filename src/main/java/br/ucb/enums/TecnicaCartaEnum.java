package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TecnicaCartaEnum {

	TECNICA_DE_PROSPECCAO   (1, "Técnica de Prospecção"),
	REPRESENTAÇAO_EMPREGADA (2, "Representação Empregada");
	
	private Integer codigo;
	private String descricao;

	TecnicaCartaEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<TecnicaCartaEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static TecnicaCartaEnum valueOf(Integer valor){
		for (TecnicaCartaEnum naturezaEnum : values()){
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
