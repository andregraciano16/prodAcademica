package br.ucb.MB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.CursoDao;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.entity.Curso;
import br.ucb.util.StringUtil;

@ManagedBean(name = "cursoMB")
@ViewScoped
public class CursoMB extends BaseMB {

	private static final long serialVersionUID = -8416866673394767387L;
	private CursoDao cursoDao;
	private Curso curso;
	private List<Curso> cursos;

	@PostConstruct
	public void init() {
		this.cursoDao = new CursoDaoImpl();
		this.curso    = new Curso();
		this.cursos   = cursoDao.list();
	}

	public void cadastrar() {
		if(StringUtil.isNotNullIsNotEmpty(this.curso.getNome())){
			this.cursoDao.save(this.curso);
			setMessageSuccess("Cadastrado com sucesso!");
			limpar();
		}else{
			setMessageError("Informe o nome do curso");
		}
	}

	public void editar(Curso curso) {
		if(StringUtil.isNotNullIsNotEmpty(curso.getNome())){
			this.cursoDao.update(curso);
			setMessageSuccess("Alterado com sucesso!");
			limpar();
		}else{
		    setMessageError("Informe o nome do curso!");
		}
	}

	public void excluir(Curso curso) {
		this.cursoDao.remove(curso);
		setMessageSuccess("Excluido com sucesso!");
		limpar();
	}

	public void buscar() {
		// this.docentes =
		// this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(),
		// tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
