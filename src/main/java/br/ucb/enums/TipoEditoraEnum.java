package br.ucb.enums;

public enum TipoEditoraEnum {

	PROGRAMA               (1, "Programa"), 
	IES_PROGRAMA           (2, "IES do Programa"), 
	EDITORA_BRASILEIRA     (3, "Editora Brasileira Comercial"),
	EDITORA_UNIVERSITARIO  (4, "Editora Universitária"), 
	INSTIRUICAO_CIENTIFICA (5, "Instituição Científica"), 
	OUTRA                  (6, "Outra");
	
	private Integer codigo;
	private String descricao;

	TipoEditoraEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
}
