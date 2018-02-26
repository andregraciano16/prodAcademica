package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.MateriaDao;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Materia;

@ManagedBean
@ViewScoped
public class MateriaMB {

	private Materia materia;
	private List<Materia> materias;
	private Materia editavel;
	private List<LinhaPesquisa> linhasPesquisa;

	public MateriaMB() {
		this.materia = new Materia();
		this.setMaterias(new ArrayList<Materia>());
		this.editavel = new Materia();
		this.setLinhasPesquisa(new ArrayList<LinhaPesquisa>());
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Materia getEditavel() {
		return editavel;
	}

	public void setEditavel(Materia editavel) {
		this.editavel = editavel;
	}

	public List<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}

	@PostConstruct
	public void init() {
		LinhaPesquisaDao linhaPesquisaDao = new LinhaPesquisaDao();
		this.linhasPesquisa = linhaPesquisaDao.buscaTodos();
	}

	public void cadastrarMateria(Materia materia) {
		String msg;
		MateriaDao materiaDAO = new MateriaDao();
		if (materias.contains(materia)) {
			msg = materiaDAO.alterar(materia);
		} else {
			msg = materiaDAO.cadastrar(getMateria());
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaMaterias();
	}

	public void buscaMaterias() {
		MateriaDao materiaDAO = new MateriaDao();
		this.materias = materiaDAO.buscaTodos();
	}

	public void excluiMateria(Materia materia) {
		String msg;
		MateriaDao materiaDAO = new MateriaDao();
		msg = materiaDAO.excluir(materia);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		buscaMaterias();
	}

	public void editaMateria(RowEditEvent event) {
		String msg;
		Materia materia = (Materia) event.getObject();
		materia.setDescricao(editavel.getDescricao());
		materia.setLinhaPesquisa(editavel.getLinhaPesquisa());
		MateriaDao materiaDAO = new MateriaDao();
		msg = materiaDAO.alterar(materia);
		buscaMaterias();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void cancelaEdit(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscaMateria(Materia materia) {
		MateriaDao materiaDAO = new MateriaDao();
		this.materias = materiaDAO.buscaMateriaPorPesquisa(materia.getDescricao());
	}

}
