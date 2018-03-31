package br.ucb.MB;

import java.io.IOException;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ucb.util.FacesUtil;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB extends BaseMB {

	private static final long serialVersionUID = -4806125991581457664L;

	private FacesContext        context  = FacesUtil.getFacesContext();
	private HttpServletRequest  request  = FacesUtil.getHttpServletRequest();
	private HttpServletResponse response = FacesUtil.getHttpServletResponse();
	private String matricula;

	public void login() throws IOException{
		RequestDispatcher dispacher = request.getRequestDispatcher("/j_spring_security_check");
		try {
			dispacher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		context.getResponseComplete();
	}

	public void preRender(){
		if("true".equals(request.getParameter("invalid"))){
			setMessageError("Dados inválidos");
		}
	}
	
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
