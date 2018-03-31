package br.ucb.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacesUtil {

	public static FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	public static  ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	public static HttpServletResponse getHttpServletResponse(){
		return (HttpServletResponse) getExternalContext().getResponse();
	}
	
	public static HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest) getExternalContext().getRequest();
	}
	
}
