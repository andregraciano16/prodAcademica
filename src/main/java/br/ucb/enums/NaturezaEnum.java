package br.ucb.enums;

import java.util.ArrayList;
import java.util.List;

public enum NaturezaEnum {
	
	TRABALHO_COMPLETO (1, "Trabalho Completo"),
	RESUMO            (2, "Resumo");
	
	private Integer codigo;
	private String descricao;

	NaturezaEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaEnum> list(){
		List<NaturezaEnum> enums = new ArrayList<>();
		NaturezaEnum[] arrays =  NaturezaEnum.values();
		for (NaturezaEnum naturezaEnum : arrays) {
			enums.add(naturezaEnum);
		}
		return enums;
	}

	public static NaturezaEnum valueOf(Integer valor){
		for (NaturezaEnum naturezaEnum : values()){
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
