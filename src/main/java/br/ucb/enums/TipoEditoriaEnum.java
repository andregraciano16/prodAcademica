package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum TipoEditoriaEnum {

	EDICAO     (1, "Edição"), 
	EDITORACAO (2, "Editoração") , 
	OUTRO      (3, "Outro");
	
	private Integer codigo;
	private String descricao;

	TipoEditoriaEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<TipoEditoriaEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static TipoEditoriaEnum valueOf(Integer valor){
		for (TipoEditoriaEnum naturezaEnum : values()){
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
