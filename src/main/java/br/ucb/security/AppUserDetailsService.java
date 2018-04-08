package br.ucb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.ucb.VO.UsuarioVO;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.DocenteDaoImpl;

public class AppUserDetailsService implements UserDetailsService{

	private DocenteDao docenteDao = new DocenteDaoImpl();
	
	@Override
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		UsuarioVO usuario = null;
		UsuarioSistema user = null;
		UserDetails details = null;
		//buscar docente
		PasswordEncoder encoder =  PasswordEncoderFactories.createDelegatingPasswordEncoder();

		usuario = docenteDao.findByMatricula(matricula);
	    if(usuario == null){
	    	//buscar aluno
	    }
	    if(usuario != null){
	       user = new UsuarioSistema(usuario, getCargo(usuario));
	       details = User.withUsername(usuario.getMatricula())
	    		   .password(encoder.encode(usuario.getSenha()))
	    		   .roles(usuario.getGrupo()).build();
	    }
		return details;
	}

	private Collection<? extends GrantedAuthority> getCargo(UsuarioVO usuario) {
		List<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(usuario.getGrupo()));
		return authority;
	}

}
