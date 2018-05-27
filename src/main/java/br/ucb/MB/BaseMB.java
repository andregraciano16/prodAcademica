package br.ucb.MB;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.core.GrantedAuthority;

import br.ucb.VO.UsuarioVO;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;


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
	
	public UsuarioVO getUsuario(){
		UsuarioSistema user = new Seguranca().getUsuarioLogado();
		return user.getUsuario();
	}
	
	public UsuarioSistema getUsuarioSistema(){
		return new Seguranca().getUsuarioLogado();
	}
	
	public boolean isDiretor(){
		Iterator<GrantedAuthority> iterator = getUsuarioSistema().getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_DIRETOR")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isAluno(){
		Iterator<GrantedAuthority> iterator = getUsuarioSistema().getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_ALUNO")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isProfessor(){
		Iterator<GrantedAuthority> iterator = getUsuarioSistema().getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_PROFESSOR")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
}
