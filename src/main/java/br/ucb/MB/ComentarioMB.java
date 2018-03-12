package br.ucb.MB;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.ComentarioDao;
import br.ucb.dao.impl.ComentarioDaoImpl;
import br.ucb.entity.Comentario;

@ManagedBean(name = "comentarioMB")
@ViewScoped
public class ComentarioMB extends BaseMB {

	private static final long serialVersionUID = 6415110752671331286L;
	private ComentarioDao comentarioDao;
	private Comentario comentario;

	@PostConstruct
	public void init() {
		this.comentarioDao = new ComentarioDaoImpl();
		this.comentario = new Comentario();
	}

	public void cadastrar() {
		this.comentarioDao.save(this.comentario);
	}

	public void editar(Comentario comentario) {
		this.comentarioDao.update(comentario);
	}

	public void excluir(Comentario comentario) {
		this.comentarioDao.remove(comentario);
	}

	public void buscar() {
		// this.docentes =
		// this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(),
		// tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}

	public Comentario getComentario() {
		return this.comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

}
