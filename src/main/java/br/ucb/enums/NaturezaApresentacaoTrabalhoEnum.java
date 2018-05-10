package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaApresentacaoTrabalhoEnum {

	COMUNICAÇAO (1, "Comunicação"), 
	CONFERENCIA (2, "Conferência"), 
	CONGRESSO   (3, "Congresso"), 
	SEMINARIO   (4, "Seminário"),
	SIMPOSIO    (5, "Simposito"), 
	OUTRA       (6, "Outra");
	
	private Integer codigo;
	private String descricao;

	NaturezaApresentacaoTrabalhoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaApresentacaoTrabalhoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaApresentacaoTrabalhoEnum valueOf(Integer valor){
		for (NaturezaApresentacaoTrabalhoEnum naturezaEnum : values()){
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
