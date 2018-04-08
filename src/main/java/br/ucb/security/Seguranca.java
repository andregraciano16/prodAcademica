package br.ucb.security;

import java.util.Iterator;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.ucb.VO.UsuarioVO;

@Named
@RequestScoped
public class Seguranca {
	
	public String getNomeUsuario() {
//		String nome = null;
//		
//		UsuarioSistema usuarioLogado = getUsuarioLogado();
//		
//		if (usuarioLogado != null) {
//			nome = usuarioLogado.getUsuario().getMatricula();
//		}
//		
//		return nome;
		return null;
	}

	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		UsuarioVO vo = new UsuarioVO();
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			vo.setMatricula(auth.getName());
			Iterator<GrantedAuthority> iterator = auth.getAuthorities().iterator();
			vo.setGrupo(iterator.next().getAuthority());
			vo.setSenha("");
			usuario = new UsuarioSistema(vo, auth.getAuthorities());
		}
		return usuario;
	}
	

	
}
