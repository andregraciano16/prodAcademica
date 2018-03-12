package br.ucb.MB;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.entity.StatusProducao;

@ManagedBean(name = "statusProducaoMB")
@ViewScoped
public class StatusProducaoMB extends BaseMB {

	private static final long serialVersionUID = -8801373626871413789L;

	private StatusProducaoDao statusProducaoDao;
	private StatusProducao statusProducao;

	@PostConstruct
	public void init() {
		this.statusProducaoDao = new StatusProducaoDaoImpl();
		this.statusProducao = new StatusProducao();
	}

	public void cadastrar() {
		this.statusProducaoDao.save(this.statusProducao);
	}

	public void editar(StatusProducao producaoAcademica) {
		this.statusProducaoDao.update(producaoAcademica);
	}

	public void excluir(StatusProducao producaoAcademica) {
		this.statusProducaoDao.remove(producaoAcademica);
	}

	public void buscar() {
		// this.docentes =
		// this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(),
		// tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}

	public StatusProducao getStatusProducao() {
		return this.statusProducao;
	}

	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}

}
