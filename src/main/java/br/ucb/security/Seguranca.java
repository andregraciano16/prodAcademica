package br.ucb.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Seguranca {

//	@Inject
//	private ExternalContext externalContext;
//	
//	public String getNomeUsuario() {
//		String nome = null;
//		
//		UsuarioSistema usuarioLogado = getUsuarioLogado();
//		
//		if (usuarioLogado != null) {
//			nome = usuarioLogado.getUsuario().getMatricula();
//		}
//		
//		return nome;
//	}
//
//	@Produces
//	@UsuarioLogado
//	public UsuarioSistema getUsuarioLogado() {
//		UsuarioSistema usuario = null;
//		
//		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
//				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
//		
//		if (auth != null && auth.getPrincipal() != null) {
//			usuario = (UsuarioSistema) auth.getPrincipal();
//		}
//		
//		return usuario;
//	}
//	
//	public boolean isEmitirPedidoPermitido() {
//		return externalContext.isUserInRole("ADMINISTRADORES") 
//				|| externalContext.isUserInRole("VENDEDORES");
//	}
//	
//	public boolean isCancelarPedidoPermitido() {
//		return externalContext.isUserInRole("ADMINISTRADORES") 
//				|| externalContext.isUserInRole("VENDEDORES");
//	}
	
}
