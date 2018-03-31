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

import br.ucb.VO.UsuarioVO;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.DocenteDaoImpl;

public class AppUserDetailsService implements UserDetailsService{

	private DocenteDao docenteDao = new DocenteDaoImpl();
	
	@Override
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		UsuarioVO usuario = null;
		UsuarioSistema user = null;
		//buscar docente
		usuario = docenteDao.findByMatricula(matricula);
	    if(usuario == null){
	    	//buscar aluno
	    }
	    if(usuario != null){
	       user = new UsuarioSistema(usuario, getCargo(usuario));
	    }
		return user;
	}

	private Collection<? extends GrantedAuthority> getCargo(UsuarioVO usuario) {
		List<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
		authority.add(new SimpleGrantedAuthority(usuario.getGrupo()));
		return authority;
	}

}
