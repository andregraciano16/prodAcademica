package br.ucb.MB;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.EnderecoDao;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.entity.Endereco;

@ManagedBean(name = "enderecoMB")
@ViewScoped
public class EnderecoMB extends BaseMB {

	private static final long serialVersionUID = 3167824801019437547L;
	private Endereco endereco;
	private EnderecoDao enderecoDao;

	@PostConstruct
	public void init() {
		this.enderecoDao = new EnderecoDaoImpl();
		this.endereco = new Endereco();
	}

	public void cadastrar() {
		this.enderecoDao.save(this.endereco);
	}

	public void editar(Endereco endereco) {
		this.enderecoDao.update(endereco);
	}

	public void excluir(Endereco endereco) {
		this.enderecoDao.remove(endereco);
	}

	public void buscar() {
		// this.docentes =
		// this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(),
		// tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
