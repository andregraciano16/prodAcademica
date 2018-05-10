package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoOrganizacaoEventoEnum {

	CONCERTO  (1, "Concerto"), 
	CONCURSO  (2, "Concurso"), 
	CONGRESSO (3, "Congresso"), 
	EXPOSICAO (4, "Exposição"), 
	FESTIVAL  (5, "Festival"), 
	OUTRO     (6, "Outro");
	
	private Integer codigo;
	private String descricao;

	TipoOrganizacaoEventoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<TipoOrganizacaoEventoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static TipoOrganizacaoEventoEnum valueOf(Integer valor){
		for (TipoOrganizacaoEventoEnum naturezaEnum : values()){
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
