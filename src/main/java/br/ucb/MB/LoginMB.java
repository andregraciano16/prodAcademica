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

	private FacesContext context = FacesUtil.getFacesContext();
	private HttpServletRequest request = FacesUtil.getHttpServletRequest();
	private HttpServletResponse response = FacesUtil.getHttpServletResponse();
	private String matricula;
	private String password;

	public void login() throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
		dispatcher.forward(request, response);
		
		context.responseComplete();
	}

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			setMessageError("Dados inválidos");
		}
	}

	public void limpar(){
		this.matricula = null;
		this.password = null;
	}
	
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
