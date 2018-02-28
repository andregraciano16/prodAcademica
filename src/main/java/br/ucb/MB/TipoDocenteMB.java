package br.ucb.MB;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.TipoDocenteDao;
import br.ucb.dao.impl.TipoDocenteDaoImpl;
import br.ucb.entity.TipoDocente;

@ManagedBean(name = "tipoDocenteMB")
@ViewScoped
public class TipoDocenteMB extends BaseMB {

	private static final long serialVersionUID = -3236475006551893563L;

	private List<TipoDocente> tipos;
	private TipoDocente tipoDocente;
	private String descricao;
	private TipoDocenteDao tipoDocenteDao;

	@PostConstruct
	public void init() {
		this.tipos          = new ArrayList<TipoDocente>();
		this.tipoDocente    = new TipoDocente();
		this.descricao      = new String();
		this.tipoDocenteDao = new TipoDocenteDaoImpl();
		buscar();
	}

	public void cadastrar() {
		if (this.descricao != null && !this.descricao.isEmpty()) {
			montarTipoDocente();
			this.tipoDocenteDao.save(this.tipoDocente);
		} else {
			// informe a descrição!
		}
	}

	private void montarTipoDocente() {
		if (this.tipoDocente == null) {
			this.tipoDocente = new TipoDocente();
		}
		this.tipoDocente.setIdTipoDocente(null);
		this.tipoDocente.setTipo(this.descricao);
	}

	public void excluir(TipoDocente tipoDocente) {
		this.tipoDocenteDao.remove(tipoDocente);
		buscar();
	}

	public void editar(TipoDocente tipoDocente) {
		this.tipoDocenteDao.update(tipoDocente);
		buscar();
	}

	public void buscar() {
		if (this.descricao != null) {
			if (!this.descricao.isEmpty()) {
				this.tipos = this.tipoDocenteDao.findByTipo(this.descricao);
			} else if (this.descricao.isEmpty()) {
				this.tipos = this.tipoDocenteDao.list();
			}
		}
	}

	public void limpar() {
		init();
	}

	public List<TipoDocente> getTipos() {
		return this.tipos;
	}

	public void setTipos(List<TipoDocente> tipos) {
		this.tipos = tipos;
	}

	public TipoDocente getTipoDocente() {
		return this.tipoDocente;
	}

	public void setTipoDocente(TipoDocente tipoDocente) {
		this.tipoDocente = tipoDocente;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
