package br.ucb.MB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.TipoProducaoDao;
import br.ucb.dao.impl.TipoProducaoDaoImpl;
import br.ucb.entity.TipoProducao;

@ManagedBean(name = "tipoProducaoMB")
@ViewScoped
public class TipoProducaoMB extends BaseMB {

	private static final long serialVersionUID = 7938037840502400717L;

	private List<TipoProducao> tipos;
	private TipoProducaoDao    tipoProducaoDao;
	private TipoProducao       tipoProducao;

	@PostConstruct
	public void init() {
		this.tipoProducaoDao = new TipoProducaoDaoImpl();
		this.tipoProducao    = new TipoProducao();
		this.tipos           = this.tipoProducaoDao.list();
	}

	public void cadastrar() {
		this.tipoProducaoDao.save(this.tipoProducao);
	}

	public void editar(TipoProducao tipo) {
		this.tipoProducaoDao.update(tipo);
	}

	public void excluir(TipoProducao tipo) {
		this.tipoProducaoDao.remove(tipo);
	}
	
	public void buscar(){
		this.tipos = this.tipoProducaoDao.findByDescricaoAndTipo(this.tipoProducao.getTipo(), this.tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}

	public List<TipoProducao> getTipos() {
		return this.tipos;
	}

	public void setTipos(List<TipoProducao> tipos) {
		this.tipos = tipos;
	}

	public TipoProducao getTipoProducao() {
		return this.tipoProducao;
	}

	public void setTipoProducao(TipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

}
