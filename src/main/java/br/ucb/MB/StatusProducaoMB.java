package br.ucb.MB;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.entity.StatusProducao;
import br.ucb.util.StringUtil;

@ManagedBean(name = "statusProducaoMB")
@ViewScoped
public class StatusProducaoMB extends BaseMB {

	private static final long serialVersionUID = -8801373626871413789L;

	private StatusProducaoDao statusProducaoDao;
	private StatusProducao statusProducao;
	private List<StatusProducao> status;

	@PostConstruct
	public void init() {
		this.statusProducaoDao = new StatusProducaoDaoImpl();
		this.statusProducao = new StatusProducao();
		this.status = this.statusProducaoDao.list();
	}

	public void cadastrar() {
		if(StringUtil.isNotNullIsNotEmpty(this.statusProducao.getTipo()) && StringUtil.isNotNullIsNotEmpty(this.statusProducao.getDescricao())){
			this.statusProducaoDao.save(this.statusProducao);
			limpar();
			setMessageSuccess("Cadastrado com sucesso!");
		}else{
			setMessageError("Informe os campos");
		}
	}

	public void editar(StatusProducao producaoAcademica) {
		if(StringUtil.isNotNullIsNotEmpty(producaoAcademica.getTipo()) && StringUtil.isNotNullIsNotEmpty(producaoAcademica.getDescricao())){
			this.statusProducaoDao.update(producaoAcademica);
			limpar();
			setMessageSuccess("Alterado com sucesso!");
		}else{
			setMessageError("Informe os campos");
		}
	}

	public void excluir(StatusProducao producaoAcademica) {
		this.statusProducaoDao.remove(producaoAcademica);
		limpar();
		setMessageSuccess("Excluído com sucesso!");
	}

	public void buscar() {
		this.status = this.statusProducaoDao.findByDescricaoAndTipo(statusProducao.getTipo(), statusProducao.getDescricao());
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

	public List<StatusProducao> getStatus() {
		return this.status;
	}

	public void setStatus(List<StatusProducao> status) {
		this.status = status;
	}

}
