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
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.UploadedFile;

import br.ucb.VO.AutorVO;
import br.ucb.dao.AlunoDao;
import br.ucb.dao.AutorDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.ExternoDao;
import br.ucb.dao.JornalRevistaDao;
import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.LivroDao;
import br.ucb.dao.PeriodicoDao;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.TipoProducaoDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.AutorDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.ExternoDaoImpl;
import br.ucb.dao.impl.JornalRevistaDaoImpl;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.LivroDaoImpl;
import br.ucb.dao.impl.PeriodicoDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.dao.impl.TipoProducaoDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.ArtigoJornalRevista;
import br.ucb.entity.ArtigoPeriodico;
import br.ucb.entity.Autor;
import br.ucb.entity.Docente;
import br.ucb.entity.Externo;
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
	private static final String ALUNO = "ALUNO";

	private ProducaoAcademicaDao producaoAcDao;
	private TipoProducaoDao      tipoProducaoDao;
	private StatusProducaoDao    statusProducaoDao;
	private LinhaPesquisaDao     linhaPesquisaDao;
	private DocenteDao           docenteDao;
	private JornalRevistaDao     jornalRevistaDao;
	private PeriodicoDao         periodicoDao;
	private LivroDao             livroDao;
	private AlunoDao             alunoDao;
	private AutorDao autorDao;
	
	private Livro               livro;
	private ProducaoAcademica   producaoAcademica;
	private TipoProducao        tipoProducao;
	private LinhaPesquisa       linhaPesquisa;
	private StatusProducao      statusProducao;
	private UploadedFile        uploadFile;
	private List<UploadedFile>  uploadFiles;
	private ArtigoPeriodico     periodico;
	private ArtigoJornalRevista jornalRevista;
	private Autor               orientador;
	private Autor               coorientador;
	private Externo             externo;
	private AutorVO             autorSelecionado;
	private List<AutorVO>       autoresVO;
	private List<Autor>         autores;
	private List<Externo>       externos;
	private ExternoDao          externoDao;
	
	@PostConstruct
	public void init() {
		this.externoDao        = new ExternoDaoImpl();
		this.autorDao          = new AutorDaoImpl();
		this.alunoDao          = new AlunoDaoImpl();
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
		this.docenteDao        = new DocenteDaoImpl          ();
		this.externo           = new Externo                 ();
		this.autoresVO         = new ArrayList<AutorVO>      ();
		this.coorientador      = new Autor                   ();
		this.orientador        = new Autor                   ();
		this.autorSelecionado  = new AutorVO                 ();
		this.uploadFiles       = new ArrayList<UploadedFile> ();
		this.autores           = new ArrayList<Autor>();
		this.externos          = new ArrayList<Externo>();
	}

	public void upload(FileUploadEvent event) {
		this.uploadFile = event.getFile();
		try {
			producaoAcademica.setArquivo("D:\\teste" + this.uploadFile.getFileName());
			FileUtil.upload("D:\\teste", this.uploadFile.getFileName(), this.uploadFile.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    uploadFiles.add(this.uploadFile);
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public double convertBiteEmKBites(Long tamanho){
		return tamanho / 1024;
	}
	
	public boolean habilitarTabelaAutores(){
		if(this.autoresVO != null && !this.autoresVO.isEmpty()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public void removerArquivo(NativeUploadedFile file){
		uploadFiles.remove(file);
	}
	
	public void removerExterno(Externo ex){
		this.externos.remove(ex);
	}
	
	public void cadastrar() {
		this.producaoAcademica.setDataCadastro(new Date());
		this.producaoAcademica.setLinhaPesquisa(this.linhaPesquisaDao.findById(this.linhaPesquisa.getIdLinhaPesquisa()));
		this.producaoAcademica.setTipoProducao(this.tipoProducao);
		this.producaoAcademica.setStatusProducao(this.statusProducao);
		salvarTipoProducao();
		salvarAutores();
		salvarExterno();
	}
	
	private void salvarExterno() {
		ProducaoAcademica pa = this.producaoAcDao.findByProdAc(this.producaoAcademica);
		for (Externo ex : externos) {
			ex.setProducaoAcademica(pa);
			this.externoDao.save(ex);		
		}
	}

	private void salvarAutores(){
		ProducaoAcademica pa = this.producaoAcDao.findByProdAc(this.producaoAcademica);
		montarAutores(pa);
		this.autores.add(montarOrientador("ORIENTADOR", pa));
		this.autores.add(montarOrientador("COORIENTADOR", pa));
		for (Autor autor : autores) {
			this.autorDao.save(autor);
		}
	}
	
	private void montarAutores(ProducaoAcademica pa){
		Autor autorCad;
		for (AutorVO autor : autoresVO) {
			Docente docente  = this.docenteDao.findById(autor.getId());
			if(docente != null){
				autorCad = new Autor();
				autorCad.setCodAutor(docente.getIdDocente());
				autorCad.setTipoAutor(docente.getTipoDocente().getTipo());
				autorCad.setTipoAcao("AUTOR");
				autorCad.setProducaoAcademica(pa);
				this.autores.add(autorCad);
			}else{
				Aluno aluno = this.alunoDao.findById(autor.getId());
				autorCad = new Autor();
				autorCad.setCodAutor(aluno.getIdAluno());
				autorCad.setTipoAutor("ALUNO");
				autorCad.setTipoAcao("AUTOR");
				autorCad.setProducaoAcademica(pa);
				this.autores.add(autorCad);
			}
		}
	}
	
	private void montarCoorientador(ProducaoAcademica pa){
		Docente docente  = this.docenteDao.findById(this.orientador.getCodAutor());
		if(docente != null){
			this.orientador.setTipoAcao("COORIENTADOR");
			this.orientador.setTipoAutor(docente.getTipoDocente().getTipo());
            this.orientador.setProducaoAcademica(pa);
			this.autores.add(this.orientador);
	    }
	}

	private void montarOrientador(ProducaoAcademica pa){
	     Docente docente  = this.docenteDao.findById(this.orientador.getCodAutor());
	     this.orientador.setTipoAcao("ORIENTADOR");
	     this.orientador.setTipoAutor(docente.getTipoDocente().getTipo());
	     this.orientador.setProducaoAcademica(pa);
	     this.autores.add(this.orientador);
	}
	
	private void salvarTipoProducao(){
		if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(1))){
			this.livro.setProducaoAcademica(this.producaoAcademica);
		    this.livroDao.save(this.livro);	
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(2))){
			this.jornalRevista.setProducaoAcademica(this.producaoAcademica);
			this.jornalRevistaDao.save(this.jornalRevista);
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(3))){
			this.periodico.setProducaoAcademica(this.producaoAcademica);
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
	
	public List<Docente> getListaOrientadores(){
		return docenteDao.list();
	}
	
	public List<AutorVO> getListAutores(){
		return montarListaAutores();
	}
	
	public Autor montarOrientador(String tipo, ProducaoAcademica pa){
		if(this.coorientador != null){
			Docente  docente = docenteDao.findById(this.coorientador.getCodAutor());
			if(docente != null){
				Autor autor = new Autor();
				autor.setCodAutor(docente.getIdDocente());
				autor.setTipoAcao(tipo);
				autor.setTipoAutor(docente.getTipoDocente().getTipo());
				autor.setProducaoAcademica(this.producaoAcademica);
				autor.setProducaoAcademica(pa);
				return autor;
			}
		}
		return null;
	}
	
	public List<AutorVO> montarListaAutores(){
		List<AutorVO> autores = new ArrayList<AutorVO>(); 
		autores.addAll(convertDocenteEmAutorVO());
	   // autores.addAll(convertAlunoEmAutorVO());
	    return autores;
	}
	
	public List<AutorVO> convertDocenteEmAutorVO(){
		List<AutorVO> autores = new ArrayList<AutorVO>();
		List<Docente> docentes =  docenteDao.list();
		AutorVO vo;
		for (Docente docente : docentes) {
			vo = new AutorVO();
			vo.setId(docente.getIdDocente());
			vo.setMatricula(docente.getMatricula());
			vo.setNome(docente.getNome());
			vo.setTipo(docente.getTipoDocente().getTipo());
			autores.add(vo);
		}
		return autores;
	}
	
	public List<AutorVO> convertAlunoEmAutorVO(){
		List<AutorVO> autores = new ArrayList<AutorVO>();
		List<Aluno> alunos =  alunoDao.list();
		AutorVO vo;
		for (Aluno aluno : alunos) {
			vo = new AutorVO();
			vo.setId(aluno.getIdAluno());
			vo.setMatricula(aluno.getMatricula());
			vo.setNome(aluno.getNome());
			vo.setTipo(ALUNO);
			autores.add(vo);
		}
		return autores;
	}
	
	public boolean habilitarTabelaArquivos(){
		if(uploadFiles != null && !uploadFiles.isEmpty())
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public void adicionarExterno(){
		this.externos.add(this.externo);
	}
	
	public boolean habilitarTabelaExterno(){
		if(this.externos != null && !this.externos.isEmpty()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public void adicionarAutor(){
		if(this.autorSelecionado != null && this.autoresVO != null){
			List<AutorVO> autores = getListAutores();
			for (AutorVO vo : autores) {
				if(vo.getMatricula().equals(autorSelecionado.getMatricula())){
					if(!autoresVO.contains(vo))
						this.autoresVO.add(vo);					
				}
			}
		}
	}
	
	public void removerAutor(){
		if(this.autorSelecionado != null && this.autoresVO != null){
			List<AutorVO> autores = getListAutores();
			for (AutorVO vo : autores) {
				if(vo.getMatricula().equals(autorSelecionado.getMatricula())){
					this.autoresVO.remove(vo);
				}
			}
		}
	}
	
	public List<Docente> getListDocentes(){
		return docenteDao.list();
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

	public AutorVO getAutorSelecionado() {
		return this.autorSelecionado;
	}

	public void setAutorSelecionado(AutorVO autorSelecionado) {
		this.autorSelecionado = autorSelecionado;
	}

	public List<AutorVO> getAutoresVO() {
		return this.autoresVO;
	}

	public void setAutoresVO(List<AutorVO> autoresVO) {
		this.autoresVO = autoresVO;
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

	public Autor getOrientador() {
		return this.orientador;
	}

	public void setOrientador(Autor orientador) {
		this.orientador = orientador;
	}

	public Autor getCoorientador() {
		return this.coorientador;
	}

	public void setCoorientador(Autor coorientador) {
		this.coorientador = coorientador;
	}

	public Externo getExterno() {
		return this.externo;
	}

	public void setExterno(Externo externo) {
		this.externo = externo;
	}

	public List<UploadedFile> getUploadFiles() {
		return this.uploadFiles;
	}

	public void setUploadFiles(List<UploadedFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public List<Externo> getExternos() {
		return this.externos;
	}

	public void setExternos(List<Externo> externos) {
		this.externos = externos;
	}
	
}
