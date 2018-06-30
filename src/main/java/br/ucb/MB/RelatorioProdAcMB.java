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
	
	private int qtdLivro;
	private int qtdRevistaJornal;
	private int qtdPeriodico;
	private int qtdTrabalhoAnais;
	private int qtdTraducao;
	private int qtdServicosTecnicos;
	private int qtdCartasMapas;
	private int qtdCursoCurto;
	private int qtdDesenApp;
	private int qtdDesenMaterial;
	private int qtdDesenProduto;
	private int qtdDesenTecnica;
	private int qtdEditoria;
	private int qtdOrgEvento;
	private int qtdRelatorioPesquisa;
	private int qtdApresentacaoTrabalho;
	private int qtdOutro;

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
		recolheTipoProducao();
	}
	
	public void recolheTipoProducao() {
		for(ProducaoAcademica prod : this.producoes){
			if(prod.getTipoProducao().equals("LIVRO")){
				this.qtdLivro++;
			}else if(prod.getTipoProducao().getTipo().equals("REVISTA/JORNAL")){
				this.qtdRevistaJornal++;
			}else if(prod.getTipoProducao().getTipo().equals("PERIODICO")){
				this.qtdPeriodico++;
			}else if(prod.getTipoProducao().getTipo().equals("TRABALHO EM ANAIS")){
				this.qtdTrabalhoAnais++;
			}else if(prod.getTipoProducao().getTipo().equals("TRADUÇÃO")){
				this.qtdTraducao++;
			}else if(prod.getTipoProducao().getTipo().equals("SERVIÇOS TÉCNICOS")){
				this.qtdServicosTecnicos++;
			}else if(prod.getTipoProducao().getTipo().equals("CARTAS, MAPAS OU SIMILARES")){
				this.qtdCartasMapas++;
			}else if(prod.getTipoProducao().getTipo().equals("CURSO DE CURTA DURAÇÃO")){
				this.qtdCursoCurto++;
			}else if(prod.getTipoProducao().getTipo().equals("DESENVOLVIMENTO DE APLICATIVO")){
				this.qtdDesenApp++;
			}else if(prod.getTipoProducao().getTipo().equals("DESENV. MATERIAL DIDÁTICO E INSTITUCIONAL")){
				this.qtdDesenMaterial++;
			}else if(prod.getTipoProducao().getTipo().equals("DESENVOLVIMENTO DE PRODUTO")){
				this.qtdDesenProduto++;
			}else if(prod.getTipoProducao().getTipo().equals("DESENVOLVIMENTO DE TÉCNICA")){
				this.qtdDesenTecnica++;
			}else if(prod.getTipoProducao().getTipo().equals("EDITORIA")){
				this.qtdEditoria++;
			}else if(prod.getTipoProducao().getTipo().equals("ORGANIZAÇÃO DE EVENTO")){
				this.qtdOrgEvento++;
			}else if(prod.getTipoProducao().getTipo().equals("RELATÓRIO DE PESQUISA")){
				this.qtdRelatorioPesquisa++;
			}else if(prod.getTipoProducao().getTipo().equals("APRESENTAÇÃO DE TRABALHO")){
				this.qtdApresentacaoTrabalho++;
			}else if(prod.getTipoProducao().getTipo().equals("OUTRO")){
				this.qtdOutro++;
			}
		}
		
	}

	public String visualizar(ProducaoAcademica producao){
		FacesUtil.getExternalContext().getRequestMap().put("acao", acaoEnum.VISUALIZAR);
		FacesUtil.getExternalContext().getRequestMap().put("producao", producao);
		return "/producaoAcademica.xhtml";
	}
	
	public String editar(ProducaoAcademica producao){
		FacesUtil.getExternalContext().getRequestMap().put("acao", acaoEnum.EDITAR);
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

	public int getQtdLivro() {
		return qtdLivro;
	}

	public void setQtdLivro(int qtdLivro) {
		this.qtdLivro = qtdLivro;
	}

	public int getQtdRevistaJornal() {
		return qtdRevistaJornal;
	}

	public void setQtdRevistaJornal(int qtdRevistaJornal) {
		this.qtdRevistaJornal = qtdRevistaJornal;
	}

	public int getQtdPeriodico() {
		return qtdPeriodico;
	}

	public void setQtdPeriodico(int qtdPeriodico) {
		this.qtdPeriodico = qtdPeriodico;
	}

	public int getQtdTrabalhoAnais() {
		return qtdTrabalhoAnais;
	}

	public void setQtdTrabalhoAnais(int qtdTrabalhoAnais) {
		this.qtdTrabalhoAnais = qtdTrabalhoAnais;
	}

	public int getQtdTraducao() {
		return qtdTraducao;
	}

	public void setQtdTraducao(int qtdTraducao) {
		this.qtdTraducao = qtdTraducao;
	}

	public int getQtdServicosTecnicos() {
		return qtdServicosTecnicos;
	}

	public void setQtdServicosTecnicos(int qtdServicosTecnicos) {
		this.qtdServicosTecnicos = qtdServicosTecnicos;
	}

	public int getQtdCartasMapas() {
		return qtdCartasMapas;
	}

	public void setQtdCartasMapas(int qtdCartasMapas) {
		this.qtdCartasMapas = qtdCartasMapas;
	}

	public int getQtdCursoCurto() {
		return qtdCursoCurto;
	}

	public void setQtdCursoCurto(int qtdCursoCurto) {
		this.qtdCursoCurto = qtdCursoCurto;
	}

	public int getQtdDesenApp() {
		return qtdDesenApp;
	}

	public void setQtdDesenApp(int qtdDesenApp) {
		this.qtdDesenApp = qtdDesenApp;
	}

	public int getQtdDesenMaterial() {
		return qtdDesenMaterial;
	}

	public void setQtdDesenMaterial(int qtdDesenMaterial) {
		this.qtdDesenMaterial = qtdDesenMaterial;
	}

	public int getQtdDesenProduto() {
		return qtdDesenProduto;
	}

	public void setQtdDesenProduto(int qtdDesenProduto) {
		this.qtdDesenProduto = qtdDesenProduto;
	}

	public int getQtdDesenTecnica() {
		return qtdDesenTecnica;
	}

	public void setQtdDesenTecnica(int qtdDesenTecnica) {
		this.qtdDesenTecnica = qtdDesenTecnica;
	}

	public int getQtdEditoria() {
		return qtdEditoria;
	}

	public void setQtdEditoria(int qtdEditoria) {
		this.qtdEditoria = qtdEditoria;
	}

	public int getQtdOrgEvento() {
		return qtdOrgEvento;
	}

	public void setQtdOrgEvento(int qtdOrgEvento) {
		this.qtdOrgEvento = qtdOrgEvento;
	}

	public int getQtdRelatorioPesquisa() {
		return qtdRelatorioPesquisa;
	}

	public void setQtdRelatorioPesquisa(int qtdRelatorioPesquisa) {
		this.qtdRelatorioPesquisa = qtdRelatorioPesquisa;
	}

	public int getQtdApresentacaoTrabalho() {
		return qtdApresentacaoTrabalho;
	}

	public void setQtdApresentacaoTrabalho(int qtdApresentacaoTrabalho) {
		this.qtdApresentacaoTrabalho = qtdApresentacaoTrabalho;
	}

	public int getQtdOutro() {
		return qtdOutro;
	}

	public void setQtdOutro(int qtdOutro) {
		this.qtdOutro = qtdOutro;
	}
	
	

}
