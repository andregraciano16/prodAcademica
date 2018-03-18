package br.ucb.MB;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class BaseMB implements Serializable{

	private static final long serialVersionUID = 6679596452844233836L;

	public void setMessageSuccess(String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
        		mensagem, mensagem));
	}
	
	public void setMessageError(String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        		mensagem, mensagem));
	}

}
