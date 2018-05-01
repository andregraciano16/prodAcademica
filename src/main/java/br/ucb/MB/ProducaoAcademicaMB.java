package br.ucb.MB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.ucb.dao.JornalRevistaDao;
import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.LivroDao;
import br.ucb.dao.PeriodicoDao;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.TipoProducaoDao;
import br.ucb.dao.impl.JornalRevistaDaoImpl;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.LivroDaoImpl;
import br.ucb.dao.impl.PeriodicoDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.dao.impl.TipoProducaoDaoImpl;
import br.ucb.entity.ArtigoJornalRevista;
import br.ucb.entity.ArtigoPeriodico;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Livro;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.entity.StatusProducao;
import br.ucb.entity.TipoProducao;
import br.ucb.enums.DivulgacaoEnum;
import br.ucb.enums.IdiomaEnum;
import br.ucb.enums.NaturezaConteudoEnum;
import br.ucb.enums.NaturezaEnum;
import br.ucb.enums.TipoContribuicaoObraEnum;
import br.ucb.enums.TipoEditoraEnum;
import br.ucb.util.FileUtil;

@ManagedBean(name = "producaoAcademicaMB")
@ViewScoped
public class ProducaoAcademicaMB extends BaseMB {

	private static final long serialVersionUID = -8382150439301230737L;

	private ProducaoAcademicaDao producaoAcDao;
	private TipoProducaoDao      tipoProducaoDao;
	private StatusProducaoDao    statusProducaoDao;
	private LinhaPesquisaDao     linhaPesquisaDao;
	private ProducaoAcademica    producaoAcademica;
	private TipoProducao         tipoProducao;
	private LinhaPesquisa        linhaPesquisa;
	private StatusProducao       statusProducao;
	private UploadedFile         uploadFile;
	private ArtigoPeriodico      periodico;
	private ArtigoJornalRevista  jornalRevista;
    private Livro                livro;
    private JornalRevistaDao     jornalRevistaDao;
    private PeriodicoDao         periodicoDao;
    private LivroDao             livroDao;
	
	@PostConstruct
	public void init() {
		this.producaoAcDao     = new ProducaoAcademicaDaoImpl();
		this.producaoAcademica = new ProducaoAcademica       ();
		this.linhaPesquisaDao  = new LinhaPesquisaDaoImpl    ();
		this.statusProducaoDao = new StatusProducaoDaoImpl   ();
		this.tipoProducaoDao   = new TipoProducaoDaoImpl     ();
		this.linhaPesquisa     = new LinhaPesquisa           ();
		this.statusProducao    = new StatusProducao          ();
		this.tipoProducao      = new TipoProducao            ();
		this.periodico         = new ArtigoPeriodico         ();
		this.jornalRevista     = new ArtigoJornalRevista     ();
		this.livro             = new Livro                   ();
		this.jornalRevistaDao  = new JornalRevistaDaoImpl    ();   
		this.periodicoDao      = new PeriodicoDaoImpl        ();
		this.livroDao          = new LivroDaoImpl            ();
	}

	public void upload(FileUploadEvent event) {
		this.uploadFile = event.getFile();
		try {
			producaoAcademica.setArquivo("D:\\teste" + this.uploadFile);
			FileUtil.upload("D:\\teste", this.uploadFile.getFileName(), this.uploadFile.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void cadastrar() {
		this.producaoAcademica.setDataCadastro(new Date());
		this.producaoAcademica.setLinhaPesquisa(this.linhaPesquisaDao.findById(this.linhaPesquisa.getIdLinhaPesquisa()));
		this.producaoAcademica.setTipoProducao(this.tipoProducao);
		this.producaoAcademica.setStatusProducao(this.statusProducao);
		this.producaoAcDao.save(this.producaoAcademica);
		salvarTipoProducao();
	}
	
	private void salvarTipoProducao(){
		if(this.producaoAcademica.getTipoProducao().equals(new Integer(1))){
		    this.livroDao.save(this.livro);	
		}else if(this.producaoAcademica.getTipoProducao().equals(new Integer(2))){
			this.jornalRevistaDao.save(this.jornalRevista);
		}else if(this.producaoAcademica.getTipoProducao().equals(new Integer(3))){
		   this.periodicoDao.save(this.periodico);
		}
	}

	public void editar(ProducaoAcademica producaoAcademica) {
		this.producaoAcDao.update(producaoAcademica);
	}

	public void excluir(ProducaoAcademica producaoAcademica) {
		this.producaoAcDao.remove(producaoAcademica);
	}

	public boolean isArtigoPeriodico() {
		if (this.tipoProducao.getIdTipoProducao() != null)
			return this.tipoProducao.getIdTipoProducao().equals(new Integer(3));
		return Boolean.FALSE;
	}

	public boolean isRevista() {
		if (this.tipoProducao.getIdTipoProducao() != null)
			return this.tipoProducao.getIdTipoProducao().equals(new Integer(2));
		return Boolean.FALSE;
	}

	public boolean isTipoLivro() {
		if (this.tipoProducao.getIdTipoProducao() != null)
			return this.tipoProducao.getIdTipoProducao().equals(new Integer(1));
		return Boolean.FALSE;
	}

	public void buscar() {
		// this.docentes =
		// this.docenteDao.findByDescricaoAndTipo(tipoProducao.getTipo(),
		// tipoProducao.getDescricao());
	}

	public void limpar() {
		init();
	}

	public List<NaturezaEnum> getNatureza() {
		return new ArrayList<NaturezaEnum>(Arrays.asList(NaturezaEnum.values()));
	}

	public List<DivulgacaoEnum> getDivulgacao() {
		return DivulgacaoEnum.list();
	}
	
	public List<DivulgacaoEnum> getDivulgacaoLivro() {
		return new ArrayList<>(Arrays.asList(DivulgacaoEnum.IMPRESSA, DivulgacaoEnum.DIGITAL));
	}
	
	public List<IdiomaEnum>  getIdiomas(){
		return new ArrayList<>(Arrays.asList(IdiomaEnum.values()));
	}
	
	public  List<NaturezaConteudoEnum> getListNaturezaConteudo(){
		return NaturezaConteudoEnum.list();
	}
	
	public List<TipoContribuicaoObraEnum> getTipoContribuicaoObra(){
		return TipoContribuicaoObraEnum.list();
	}

	public List<TipoEditoraEnum> getTipoEditoras(){
		return TipoEditoraEnum.list();
	}
	
	public List<TipoProducao> getTipo() {
		return this.tipoProducaoDao.list();
	}

	public List<StatusProducao> getStatus() {
		return this.statusProducaoDao.list();
	}

	public List<LinhaPesquisa> getLinhas() {
		return this.linhaPesquisaDao.list();
	}
	
	public Livro getLivro() {
		return this.livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public ProducaoAcademica getProducaoAcademica() {
		return this.producaoAcademica;
	}

	public void setProducaoAcademica(ProducaoAcademica producaoAcademica) {
		this.producaoAcademica = producaoAcademica;
	}

	public LinhaPesquisa getLinhaPesquisa() {
		return this.linhaPesquisa;
	}

	public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {
		this.linhaPesquisa = linhaPesquisa;
	}

	public StatusProducao getStatusProducao() {
		return this.statusProducao;
	}

	public void setStatusProducao(StatusProducao statusProducao) {
		this.statusProducao = statusProducao;
	}

	public TipoProducao getTipoProducao() {
		return this.tipoProducao;
	}

	public void setTipoProducao(TipoProducao tipoProducao) {
		this.tipoProducao = tipoProducao;
	}

	public UploadedFile getUploadFile() {
		return this.uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public ArtigoPeriodico getPeriodico() {
		return this.periodico;
	}

	public void setPeriodico(ArtigoPeriodico periodico) {
		this.periodico = periodico;
	}

	public ArtigoJornalRevista getJornalRevista() {
		return this.jornalRevista;
	}

	public void setJornalRevista(ArtigoJornalRevista jornalRevista) {
		this.jornalRevista = jornalRevista;
	}

}
