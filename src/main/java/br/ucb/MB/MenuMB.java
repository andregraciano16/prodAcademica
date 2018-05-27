package br.ucb.MB;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.GrantedAuthority;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;

@ManagedBean(name = "menuMB")
@ViewScoped
public class MenuMB extends BaseMB{

	private static final long serialVersionUID = -7593823008874659975L;
	
	private UsuarioSistema user;
	private String nomeUsuario;
	private DocenteDao docenteDao;
	private AlunoDao alunoDao;
	
	
	@PostConstruct
	public void init(){
		user = new Seguranca().getUsuarioLogado();
		docenteDao = new DocenteDaoImpl();
		alunoDao = new AlunoDaoImpl();
		if(!isAluno()){
			nomeUsuario = docenteDao.getNomebyMatricula(user.getUsername());
		}else{
			nomeUsuario = alunoDao.getNomebyMatricula(user.getUsername());
		}
	}

	public UsuarioSistema getUser() {
		return this.user;
	}

	public void setUser(UsuarioSistema user) {
		this.user = user;
	}
	
	public boolean isDiretor(){
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_DIRETOR")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isAluno(){
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_ALUNO")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isProfessor(){
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_PROFESSOR")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	
}
