package br.ucb.enums;

public enum AcaoEnum {

	VISUALIZAR(0),
	CADASTRAR(1),
	EDITAR(2),
	LISTAR(3);
	
	AcaoEnum(Integer codigo){
	   this.codigo = codigo;
	}
	
	private Integer codigo;

	public Integer getCodigo() {
		return this.codigo;
	}
	
}
