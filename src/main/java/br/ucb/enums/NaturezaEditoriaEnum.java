package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaEditoriaEnum {

	ANAIS        (1, "Anais"), 
	CATALOGO     (2, "Catálogo"), 
	COLETANEA    (3, "Coletânea"), 
	ENCICLOPEDIA (4, "Enciclopédia"), 
	LIVRO        (5, "Livro"),  
	PERIODICO    (6, "Periódico"),
	OUTRO        (7, "Outro");
	
	private Integer codigo;
	private String descricao;

	NaturezaEditoriaEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaEditoriaEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaEditoriaEnum valueOf(Integer valor){
		for (NaturezaEditoriaEnum naturezaEnum : values()){
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
