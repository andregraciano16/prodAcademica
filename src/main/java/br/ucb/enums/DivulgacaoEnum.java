package br.ucb.enums;

import java.util.ArrayList;
import java.util.List;

public enum DivulgacaoEnum {
    //livro 
	DIGITAL(1, "Digital"),
	IMPRESSA(2, "Impressa"),
	//artigo	
	MEIO_MAGNETICO(3,"Meio Magnético"),
	FILME(4, "Filme"), 
	HIPERTEXTO(5, "Hipertexto"),
	OUTROS(6, "Outros"),
	VARIOS(7, "Vários");

	private Integer codigo;
	private String descricao;

	DivulgacaoEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}
	
	public static List<DivulgacaoEnum> listLivro(){
		List<DivulgacaoEnum> enums = new ArrayList<>();
		DivulgacaoEnum[] arrays =  DivulgacaoEnum.values();
		for (DivulgacaoEnum divulg : arrays) {
			if(divulg.getCodigo() > 2) return enums;
			enums.add(divulg);
		}
		return enums;
	} 
	
	public static DivulgacaoEnum valueOfByCod(Integer codigo){
		for (DivulgacaoEnum e : DivulgacaoEnum.values()) {
			if(e.codigo.equals(codigo)){
				return e;
			}
		}
		return null;
	} 
	
	public static List<DivulgacaoEnum> listLivroo(){
		List<DivulgacaoEnum> enums = new ArrayList<>();
		DivulgacaoEnum[] arrays =  DivulgacaoEnum.values();
		for (DivulgacaoEnum divulg : arrays) {
			enums.add(divulg);
		}
		return enums;
	} 

	public static List<DivulgacaoEnum> list(){
		List<DivulgacaoEnum> enums = new ArrayList<>();
		DivulgacaoEnum[] arrays =  DivulgacaoEnum.values();
		for (DivulgacaoEnum divulg : arrays) {
			enums.add(divulg);
		}
		return enums;
	} 
	
	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
