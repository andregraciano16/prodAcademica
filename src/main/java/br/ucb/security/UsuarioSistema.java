package br.ucb.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.ucb.VO.UsuarioVO;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	private UsuarioVO usuario;

	public UsuarioSistema(UsuarioVO usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getMatricula(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public UsuarioVO getUsuario() {
		return this.usuario;
	}

}
