package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaOrganizacaoEventoEnum {

	CURADORIA    (1, "Curadoria"), 
	MONTAGEM     (2, "Montagem"), 
	ORGANIZACAO  (3, "Organiação"), 
	MUSEOGRAFIA  (4, "Museografia"), 
	OUTRA        (5, "Outra");
	
	private Integer codigo;
	private String descricao;

	NaturezaOrganizacaoEventoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaOrganizacaoEventoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaOrganizacaoEventoEnum valueOf(Integer valor){
		for (NaturezaOrganizacaoEventoEnum naturezaEnum : values()){
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
