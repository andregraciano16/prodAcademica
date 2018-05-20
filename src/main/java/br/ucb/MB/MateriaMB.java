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
public class MateriaMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	private List<Materia> materias;
	private Materia materia;
	private MateriaDao materiaDao;
	private List<LinhaPesquisa> linhasPesquisa;
	private LinhaPesquisaDao linhaPesquisaDao;
	private boolean resultado;

	@PostConstruct
	public void init() {
		this.materias = new ArrayList<Materia>();
		this.materia = new Materia();
		this.materia.setDescricao("");
		this.materiaDao = new MateriaDaoImpl();
		this.linhasPesquisa = new ArrayList<LinhaPesquisa>();
		this.linhaPesquisaDao = new LinhaPesquisaDaoImpl();
		buscar();
	}

	public void cadastrar(Materia materia) {
		if (this.materia.getDescricao() != null && !this.materia.getDescricao().trim().isEmpty()
				&& this.materia.getLinhaPesquisa() != null) {
			if (this.materias.contains(this.materia)) {
				setMessageError(
						"Já existe um cadastro com estes dados. Por favor altere o respectivo ou insira um novo dado.");
			} else {
				montarMateria();
				this.resultado = this.materiaDao.saveM(this.materia);
				if (this.resultado) {
					setMessageSuccess("Cadastrado com sucesso.");
				}else{
					setMessageError("Houve um erro ao salvar no sistema.");
				}
			}
		} else {
			setMessageError("Preencha os campos corretamente.");
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
		this.resultado = this.materiaDao.removeM(materia);
		if (this.resultado) {
			setMessageSuccess("Excluído com sucesso.");
		}else{
			setMessageError("Houve um erro ao deletar no sistema. Por favor, apague o histórico e qualquer relação com este registro.");
		}
		
		init();
	}

	public void editar(Materia materia) {

		if (materia.getDescricao() != null && !materia.getDescricao().isEmpty()) {
			this.resultado = this.materiaDao.updateM(materia);
			if (this.resultado) {
				setMessageSuccess("Atualizado com sucesso.");
			}else{
				setMessageError("Houve um erro ao salvar no sistema.");
			}	
		} else {
			setMessageError("Descrição não pode ficar vazia.");
		}
		init();

	}

	public void cancela(RowEditEvent event) {

		String msg = "Atualização cancelada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void buscar() {

		if (this.materia.getDescricao() != null) {
			if (this.materia.getDescricao().isEmpty() && this.materia.getLinhaPesquisa() == null) {
				this.materias = this.materiaDao.list();
				this.linhasPesquisa = this.linhaPesquisaDao.list();
			} else {
				this.materias = this.materiaDao.findBySearch(this.materia);
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
