package br.ucb.MB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.CursoDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.EnderecoDao;
import br.ucb.dao.TipoDocenteDao;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.dao.impl.TipoDocenteDaoImpl;
import br.ucb.entity.Curso;
import br.ucb.entity.Docente;
import br.ucb.entity.Endereco;
import br.ucb.entity.TipoDocente;

@ManagedBean(name = "docenteMB")
@ViewScoped
public class DocenteMB extends BaseMB{

	private static final long serialVersionUID = 4035284422141615787L;
	
	private DocenteDao docenteDao;
	private TipoDocenteDao tipoDocenteDao;
	private CursoDao cursoDao;
	private Docente docente;
	private Endereco endereco;
	private List<Docente> docentes;
	private TipoDocente tipoDocente;
	private Curso curso;
	private EnderecoDao enderecoDao;
	
	@PostConstruct
	public void init() {
		this.docentes       = new ArrayList<Docente>();
		this.docenteDao     = new DocenteDaoImpl();
		this.tipoDocenteDao = new TipoDocenteDaoImpl();
		this.docente        = new Docente();
		this.tipoDocente    = new TipoDocente(); 
		this.cursoDao       = new CursoDaoImpl();
		this.curso          = new Curso();
		this.endereco       = new Endereco();
		this.enderecoDao    = new EnderecoDaoImpl();
	}

	public void cadastrar() {
		this.docente.setDataCadastro(new Date());
		this.tipoDocente = this.tipoDocenteDao.findByKey(TipoDocente.class, tipoDocente.getIdTipoDocente());
		this.curso       = this.cursoDao.findById(curso.getIdCurso());
		this.enderecoDao.save(this.endereco);
		this.endereco = this.enderecoDao.find(this.endereco);
		this.docente.setEndereco(endereco);
		this.docente.setCurso(this.curso);
		this.docente.setDataNascimento(new Date());
		this.docente.setTipoDocente(this.tipoDocente);
		this.docenteDao.save(this.docente);
	}

	public void editar(Docente tipo) {
		this.docenteDao.update(tipo);
	}

	public void excluir(Docente tipo) {
		this.docenteDao.remove(tipo);
	}
	
	public void buscar(){
		//this.docentes = this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(), tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}
	
	public List<TipoDocente> getTipoDocentes(){
		return tipoDocenteDao.list();
	}
	
	public List<Curso> getCursos(){
		return cursoDao.list();
	}

	public Docente getDocente() {
		return this.docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public List<Docente> getDocentes() {
		return this.docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public TipoDocente getTipoDocente() {
		return this.tipoDocente;
	}

	public void setTipoDocente(TipoDocente tipoDocente) {
		this.tipoDocente = tipoDocente;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
	
}
