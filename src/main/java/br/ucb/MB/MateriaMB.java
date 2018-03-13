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
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.MateriaDaoImpl;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Materia;

@ManagedBean(name = "materiaMB")
@ViewScoped
public class MateriaMB extends BaseMB{


	private static final long serialVersionUID = 1L;

	private List<Materia> materias;
	private Materia materia;
	private MateriaDao materiaDao;
	private String msg;
	private Materia editavel;
	private List<LinhaPesquisa> linhasPesquisa;
	private LinhaPesquisaDao linhaPesquisaDao;

	@PostConstruct
	public void init() {
		this.materias = new ArrayList<Materia>();
		this.materia = new Materia();
		this.materia.setDescricao("");
		this.materiaDao = new MateriaDaoImpl();
		this.editavel = new Materia();
		this.linhasPesquisa = new ArrayList<LinhaPesquisa>();
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		buscar();
	}

	public void cadastrar(Materia materia) {
		if (this.materia.getDescricao() != null && !this.materia.getDescricao().trim().isEmpty() &&
				this.materia.getLinhaPesquisa() != null) {
			if (this.materias.contains(this.materia)) {
				msg = "Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
			} else {
				montarMateria();
				this.materiaDao.save(this.materia);
				msg = "Cadastrado com sucesso.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
			}
		} else {
			msg = "Preencha os campos corretamente.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
		init();
	}

	private void montarMateria() {
		if (this.materia == null) {
			this.materia = new Materia();
		}
		this.materia.setIdMateria(null);
		this.materia.setDescricao(this.materia.getDescricao().trim());
		this.materia.setLinhaPesquisa(this.materia.getLinhaPesquisa());
	}

	public void excluir(Materia materia) {
		this.materiaDao.remove(materia);
		msg = "Excluído com sucesso.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		init();
	}

	public void editar(RowEditEvent event) {

		if (this.editavel.getDescricao() != null && !this.editavel.getDescricao().isEmpty()) {

			materia = (Materia) event.getObject();
			materia.setDescricao(editavel.getDescricao());
			materia.setLinhaPesquisa(editavel.getLinhaPesquisa());
			this.materiaDao.update(this.materia);
			msg = "Atualizado com sucesso.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));

		} else {
			msg = "Descrição não pode ficar vazia.";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
		init();

	}

	public void cancela(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscar() {

		if (this.materia.getDescricao() != null) {
			if (!this.materia.getDescricao().isEmpty()) {
				this.materias = this.materiaDao.findByDescricao(this.materia.getDescricao());
				this.linhasPesquisa = this.linhaPesquisaDao.list();
			} else if (this.materia.getDescricao().isEmpty()) {
				this.materias = this.materiaDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
			}
		}

	}

	public void limpar() {
		init();
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public MateriaDao getMateriaDao() {
		return materiaDao;
	}

	public void setMateriaDao(MateriaDao materiaDao) {
		this.materiaDao = materiaDao;
	}

	public Materia getEditavel() {
		return editavel;
	}

	public void setEditavel(Materia editavel) {
		this.editavel = editavel;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<LinhaPesquisa> getLinhasPesquisa() {
		return linhasPesquisa;
	}

	public void setLinhasPesquisa(List<LinhaPesquisa> linhasPesquisa) {
		this.linhasPesquisa = linhasPesquisa;
	}

	public LinhaPesquisaDao getLinhaPesquisaDao() {
		return linhaPesquisaDao;
	}

	public void setLinhaPesquisaDao(LinhaPesquisaDao linhaPesquisaDao) {
		this.linhaPesquisaDao = linhaPesquisaDao;
	}


}
