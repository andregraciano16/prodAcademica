package br.ucb.MB;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.dao.AutorDao;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.impl.AutorDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.enums.AcaoEnum;
import br.ucb.enums.DivulgacaoEnum;
import br.ucb.filtro.ProdAcFiltro;
import br.ucb.util.DataUtil;
import br.ucb.util.FacesUtil;

@ManagedBean(name = "relatorioProdAcMB")
@ViewScoped
public class RelatorioProdAcMB extends BaseMB {

	private static final long serialVersionUID = -6390641863928581914L;

	private AcaoEnum acaoEnum;
	private ProdAcFiltro filtro;
	private List<ProducaoAcademica> producoes;

	private ProducaoAcademicaDao producaoAcademicaDao;
	private AutorDao autorDao;

	@PostConstruct
	public void init() {
		this.producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
		this.autorDao = new AutorDaoImpl();
		this.filtro = new ProdAcFiltro();
		this.acaoEnum = AcaoEnum.LISTAR;
		buscar();
	}

	public void buscar() {
		filtro.setCodigoParticipante(getUsuario().getCodigo());
		if (isDiretor()) {
			this.filtro.setTipoAutor("DIRETOR");
			this.producoes = this.producaoAcademicaDao.findByFiltro(this.filtro);
		} else if (isDiscente()) {
			this.filtro.setTipoAutor("ALUNO");
			this.producoes = this.autorDao.findProducaoByFiltro(this.filtro);
		} else if (isProfessor()) {
			this.filtro.setTipoAutor("PROFESSOR");
			this.producoes = this.autorDao.findProducaoByFiltro(this.filtro);
		}

	}
	
	public String visualizar(ProducaoAcademica producao){
		FacesUtil.getExternalContext().getRequestMap().put("acao", acaoEnum.VISUALIZAR);
		FacesUtil.getExternalContext().getRequestMap().put("producao", producao);
		return "/producaoAcademica.xhtml";
	}
	
	public void limpar() {
		this.filtro = new ProdAcFiltro();
		buscar();
	}

	public String getDescrDivulgacao(Integer codigo) {
		DivulgacaoEnum divulgacao = DivulgacaoEnum.valueOfByCod(codigo);
		if (divulgacao != null) {
			return divulgacao.getDescricao();
		} else {
			return "";
		}
	}

	public String getFomataDataCadastro(Date data) {
		return DataUtil.convertDateInString(data, "dd/MM/yyyy");
	}

	public void habilitarNovo() {
		this.acaoEnum = AcaoEnum.CADASTRAR;
	}

	public List<ProducaoAcademica> getProducoes() {
		return this.producoes;
	}

	public void setProducoes(List<ProducaoAcademica> producoes) {
		this.producoes = producoes;
	}

	public AcaoEnum getAcaoEnum() {
		return this.acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}

	public ProdAcFiltro getFiltro() {
		return this.filtro;
	}

	public void setFiltro(ProdAcFiltro filtro) {
		this.filtro = filtro;
	}

}
