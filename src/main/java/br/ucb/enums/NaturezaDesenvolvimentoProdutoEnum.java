package br.ucb.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NaturezaDesenvolvimentoProdutoEnum {

	APARELHO            (1, "Aparelho"), 
	INSTRUMENTO         (2, "Instrumento"), 
	EQUIPAMENTO         (3, "Equipamento"),    
	FARMACOS_SIMILARES  (4, "Farmacos e Similares"), 
	OUTRO               (5, "Outro");
	
	private Integer codigo;
	private String descricao;

	NaturezaDesenvolvimentoProdutoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<NaturezaDesenvolvimentoProdutoEnum> list(){
		return new ArrayList<>(Arrays.asList(values()));
	}

	public static NaturezaDesenvolvimentoProdutoEnum valueOf(Integer valor){
		for (NaturezaDesenvolvimentoProdutoEnum naturezaEnum : values()){
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
