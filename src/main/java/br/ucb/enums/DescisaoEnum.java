package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DescisaoEnum {

	SIM(1, "Sim"),
	NAO(2, "Não");

	private Integer codigo;
	private String descricao;

	DescisaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<DescisaoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
}
