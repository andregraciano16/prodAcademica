package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaServicoTecnicosEnum {

	ASSESSORIA                 (1, "Assessoria"), 
	CONSULTORIA                (2, "Consultoria"), 
	PARECER                    (3, "Parecer") , 
	ELABORACAO_DE_PROJETO      (4, "Elaboração de Projeto"),
	RELATORIO_TECNICO          (5, "Relatório Técnico"),
	SERVICOS_NA_AREA_DA_SAUDE  (6, "Serrviços na Área da Saúde"), 
	OUTRA                      (7, "Outra"); 
	
	private Integer codigo;
	private String descricao;

	NaturezaServicoTecnicosEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaServicoTecnicosEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaServicoTecnicosEnum valueOf(Integer valor){
		for (NaturezaServicoTecnicosEnum naturezaEnum : values()){
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
