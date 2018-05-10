package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaDesenvolvimentoTecnicaEnum {
	
	ANALITICA     (1, "Analitica"), 
	INSTRUMENTAL  (2, "Instrumental"), 
	PEDAGOGICA    (3, "Pedagocica"), 
	PROCESSUAL    (4, "Processual"),
	TERAPEUTICA   (5, "Terapeutica"), 
	OUTRA         (6, "Outra");
	
	private Integer codigo;
	private String descricao;

	NaturezaDesenvolvimentoTecnicaEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaDesenvolvimentoTecnicaEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaDesenvolvimentoTecnicaEnum valueOf(Integer valor){
		for (NaturezaDesenvolvimentoTecnicaEnum naturezaEnum : values()){
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
