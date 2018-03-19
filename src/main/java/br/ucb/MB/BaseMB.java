package br.ucb.MB;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.ucb.enums.TipoUsuarioEnum;


public abstract class BaseMB implements Serializable{

	private static final long serialVersionUID = 6679596452844233836L;
    private TipoUsuarioEnum tipoUsuarioEnum;
	
	public void setMessageSuccess(String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
        		mensagem, mensagem));
	}
	
	public void setMessageError(String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        		mensagem, mensagem));
	}

	public TipoUsuarioEnum getTipoUsuarioEnum() {
		return this.tipoUsuarioEnum;
	}

	public void setTipoUsuarioEnum(TipoUsuarioEnum tipoUsuarioEnum) {
		this.tipoUsuarioEnum = tipoUsuarioEnum;
	}

}
