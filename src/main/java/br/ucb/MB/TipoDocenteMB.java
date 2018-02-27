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
		this.tipos = new ArrayList<TipoDocente>();
		this.tipoDocente = new TipoDocente();
		this.descricao = null;
		this.tipoDocenteDao = new TipoDocenteDaoImpl();
	}

	public void cadastrar() {
		if(this.descricao != null && !this.descricao.isEmpty()){
			montarTipoDocente();
			this.tipoDocenteDao.save(this.tipoDocente);
		}else{
			//informe a descrição!
		}
	}

	private void montarTipoDocente() {
		if(this.tipoDocente == null){
			this.tipoDocente = new TipoDocente();
		}
		this.tipoDocente.setId_tipoDocente(null);
		this.tipoDocente.setTipo(this.descricao);
	}

	public void excluir(TipoDocente tipoDocente) {
	}

	public void editar(TipoDocente tipoDocente) {

	}

	public void buscar(String descricao) {
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
