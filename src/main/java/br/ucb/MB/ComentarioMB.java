package br.ucb.MB;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.ComentarioDao;
import br.ucb.dao.impl.ComentarioDaoImpl;
import br.ucb.entity.Comentario;
import br.ucb.util.StringUtil;

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
		if(StringUtil.isNotNullIsNotEmpty(this.comentario.getTitulo()) && StringUtil.isNotNullIsNotEmpty(comentario.getDecricao())){
			this.comentario.setDataCadastro(new Date());
			this.comentarioDao.save(this.comentario);
			limpar();
			setMessageSuccess("Comentario realizado com sucesso!");
		}else{
			setMessageError("Informe os campos obrigatórios");
		}
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
