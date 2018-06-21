package br.ucb.MB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.security.core.GrantedAuthority;

import br.ucb.VO.AutorVO;
import br.ucb.dao.AlunoDao;
import br.ucb.dao.ApresentacaoTrabalhoDao;
import br.ucb.dao.AutorDao;
import br.ucb.dao.CartasMapasSimilaresDao;
import br.ucb.dao.CursoCurtaDuracaoDao;
import br.ucb.dao.DesenvAppDao;
import br.ucb.dao.DesenvDidaticoInstDao;
import br.ucb.dao.DesenvProdutoDao;
import br.ucb.dao.DesenvTecnicaDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.EditoriaDao;
import br.ucb.dao.ExternoDao;
import br.ucb.dao.HistoricoDao;
import br.ucb.dao.JornalRevistaDao;
import br.ucb.dao.LinhaPesquisaDao;
import br.ucb.dao.LivroDao;
import br.ucb.dao.OrganizacaoEventoDao;
import br.ucb.dao.PeriodicoDao;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.RelatorioPesquisaDao;
import br.ucb.dao.ServicoTecnicosDao;
import br.ucb.dao.StatusAprovacaoDao;
import br.ucb.dao.StatusProducaoDao;
import br.ucb.dao.TipoProducaoDao;
import br.ucb.dao.TrabalhoEmAnaisDao;
import br.ucb.dao.TraducaoDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.ApresentacaoTrabalhoDaoImpl;
import br.ucb.dao.impl.AutorDaoImpl;
import br.ucb.dao.impl.CartasMapasSimilaresDaoImpl;
import br.ucb.dao.impl.CursoCurtaDuracaoDaoImpl;
import br.ucb.dao.impl.DesenvAppDaoImpl;
import br.ucb.dao.impl.DesenvDidaticoInstDaoImpl;
import br.ucb.dao.impl.DesenvProdutoDaoImpl;
import br.ucb.dao.impl.DesenvTecnicaDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.EditoriaDaoImpl;
import br.ucb.dao.impl.ExternoDaoImpl;
import br.ucb.dao.impl.HistoricoDaoImpl;
import br.ucb.dao.impl.JornalRevistaDaoImpl;
import br.ucb.dao.impl.LinhaPesquisaDaoImpl;
import br.ucb.dao.impl.LivroDaoImpl;
import br.ucb.dao.impl.OrganizacaoEventoDaoImpl;
import br.ucb.dao.impl.PeriodicoDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.RelatorioPesquisaDaoImpl;
import br.ucb.dao.impl.ServicoTecnicosDaoImpl;
import br.ucb.dao.impl.StatusAprovacaoDaoImpl;
import br.ucb.dao.impl.StatusProducaoDaoImpl;
import br.ucb.dao.impl.TipoProducaoDaoImpl;
import br.ucb.dao.impl.TrabalhoEmAnaisDaoImpl;
import br.ucb.dao.impl.TraducaoDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.ApresentacaoTrabalho;
import br.ucb.entity.ArtigoJornalRevista;
import br.ucb.entity.ArtigoPeriodico;
import br.ucb.entity.Autor;
import br.ucb.entity.CartaMapaSimilares;
import br.ucb.entity.CursoCurtaDuracao;
import br.ucb.entity.DesenvApp;
import br.ucb.entity.DesenvDidaticoInstitucional;
import br.ucb.entity.DesenvProduto;
import br.ucb.entity.DesenvTecnica;
import br.ucb.entity.Docente;
import br.ucb.entity.Editoria;
import br.ucb.entity.Externo;
import br.ucb.entity.Historico;
import br.ucb.entity.LinhaPesquisa;
import br.ucb.entity.Livro;
import br.ucb.entity.OrganizacaoEvento;
import br.ucb.entity.ProducaoAcademica;
import br.ucb.entity.RelatorioPesquisa;
import br.ucb.entity.ServicosTecnicos;
import br.ucb.entity.StatusAprovacao;
import br.ucb.entity.StatusProducao;
import br.ucb.entity.TipoProducao;
import br.ucb.entity.TrabalhoEmAnais;
import br.ucb.entity.Traducao;
import br.ucb.enums.AcaoEnum;
import br.ucb.enums.DescisaoEnum;
import br.ucb.enums.DisponibilidadeEnum;
import br.ucb.enums.DivulgacaoEnum;
import br.ucb.enums.IdiomaEnum;
import br.ucb.enums.NaturezaApresentacaoTrabalhoEnum;
import br.ucb.enums.NaturezaCartaEnum;
import br.ucb.enums.NaturezaConteudoEnum;
import br.ucb.enums.NaturezaDesenvolvimentoAppEnum;
import br.ucb.enums.NaturezaDesenvolvimentoProdutoEnum;
import br.ucb.enums.NaturezaDesenvolvimentoTecnicaEnum;
import br.ucb.enums.NaturezaEditoriaEnum;
import br.ucb.enums.NaturezaEnum;
import br.ucb.enums.NaturezaOrganizacaoEventoEnum;
import br.ucb.enums.NaturezaServicoTecnicosEnum;
import br.ucb.enums.NaturezaTraducaoEnum;
import br.ucb.enums.NivelCursoCurtaDuaracaoEnum;
import br.ucb.enums.TipoContribuicaoObraEnum;
import br.ucb.enums.TipoDesenvolvimentoProdutoEnum;
import br.ucb.enums.TipoEditoraEnum;
import br.ucb.enums.TipoOrganizacaoEventoEnum;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;
import br.ucb.util.FacesUtil;
import br.ucb.util.FileUtil;

@ManagedBean(name = "producaoAcademicaMB")
@ViewScoped
public class ProducaoAcademicaMB extends BaseMB {

	private static final long serialVersionUID = -8382150439301230737L;
	private static final String ALUNO = "ALUNO";
	private static final String PASTA_DESTINO_ARQUIVO = "D:\\teste";

	private ProducaoAcademicaDao producaoAcDao;
	private TipoProducaoDao      tipoProducaoDao;
	private StatusProducaoDao    statusProducaoDao;
	private LinhaPesquisaDao     linhaPesquisaDao;
	private StatusAprovacaoDao	 statusAprovacaoDao;
	private DocenteDao           docenteDao;
	private JornalRevistaDao     jornalRevistaDao;
	private PeriodicoDao         periodicoDao;
	private LivroDao             livroDao;
	private AlunoDao             alunoDao;
	private AutorDao             autorDao;
	private TrabalhoEmAnaisDao   trabalhoEmAnaisDao;       
	private ExternoDao           externoDao;
	private TraducaoDao          traducaoDao;
	private ServicoTecnicosDao  servicosTecnicosDao;
	private CartasMapasSimilaresDao cartaMapaSimilaresDao;
	private CursoCurtaDuracaoDao    cursoCurtaDuracaoDao;
	private DesenvAppDao            desenvAppDao;
	private DesenvDidaticoInstDao   desenvDidaticoInstiDao;
	private DesenvProdutoDao        desenvProdutoDao;
	private DesenvTecnicaDao        desenvTecnicaDao;
	private EditoriaDao              editoriaDao;
	private OrganizacaoEventoDao    organizEnventDao;
	private RelatorioPesquisaDao    relatorioPesquisaDao;
	private ApresentacaoTrabalhoDao apresentacaoTrabalhoDao;    
	
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
	private TrabalhoEmAnais     trabalhoEmAnais;
	private Traducao            traducao;
	private ServicosTecnicos    servicosTecnicos;
	private CartaMapaSimilares  cartaMapaSimilares;  
	private CursoCurtaDuracao   cursoCurtaDuracao;
	private DesenvApp           desenvApp;
	private DesenvDidaticoInstitucional desenvDidaticoInst;
	private DesenvProduto       desenvProduto;
	private DesenvTecnica       desenvTecnica;
	private Editoria             editoria;
	private OrganizacaoEvento   organizacaoEvento;
	private RelatorioPesquisa   relatorioPesquisa;
	private ApresentacaoTrabalho apresentacaoTrabalho;
	private Historico 			historico;
	private HistoricoDao 		historicoDao;
	private UsuarioSistema 		user;
	private AcaoEnum acaoEnum;
	private List<File> files;
	private StreamedContent file;
	
	private String orientadorDesc;
	private String coorientadorDesc;
	
	@PostConstruct
	public void init() {
		this.externoDao        = new ExternoDaoImpl          ();
		this.autorDao          = new AutorDaoImpl            ();
		this.alunoDao          = new AlunoDaoImpl            ();
		this.producaoAcDao     = new ProducaoAcademicaDaoImpl();
		this.producaoAcademica = new ProducaoAcademica       ();
		this.linhaPesquisaDao  = new LinhaPesquisaDaoImpl    ();
		this.statusAprovacaoDao= new StatusAprovacaoDaoImpl	 ();
		this.statusProducaoDao = new StatusProducaoDaoImpl   ();
		this.tipoProducaoDao   = new TipoProducaoDaoImpl     ();
		this.linhaPesquisa     = new LinhaPesquisa           ();
		this.statusProducao    = new StatusProducao          ();
		this.tipoProducao      = new TipoProducao            ();
		this.docenteDao        = new DocenteDaoImpl          ();
		this.externo           = new Externo                 ();
		this.autoresVO         = new ArrayList<AutorVO>      ();
		this.coorientador      = new Autor                   ();
		this.orientador        = new Autor                   ();
		this.autorSelecionado  = new AutorVO                 ();
		this.uploadFiles       = new ArrayList<UploadedFile> ();
		this.autores           = new ArrayList<Autor>        ();
		this.externos          = new ArrayList<Externo>      ();
		this.historico 		   = new Historico();
		this.historicoDao 	   = new HistoricoDaoImpl();
		this.user 			   = new Seguranca().getUsuarioLogado();
		initTiposProducao();
		initDados();
	}
	
	public void initDados(){
		this.acaoEnum = (AcaoEnum) FacesUtil.getExternalContext().getRequestMap().get("acao");
		this.producaoAcademica = (ProducaoAcademica) FacesUtil.getExternalContext().getRequestMap().get("producao");
		if(this.producaoAcademica != null){
			this.tipoProducao = this.producaoAcademica.getTipoProducao();
			initTiposProducaoNewPage();
			initAutores();
			initArquivos();
		}else{
			this.producaoAcademica = new ProducaoAcademica();
		}
		if(this.acaoEnum == null){
			this.acaoEnum = AcaoEnum.CADASTRAR;
		}
	}      
	
	private void initArquivos() {
		if(this.producaoAcademica.getArquivo() != null){
			String[] arquivos = this.producaoAcademica.getArquivo().split(";");
			File arquivo;
			this.files = new ArrayList<File>();
			for (String string : arquivos) {
				if(string != null && !string.isEmpty()){
					arquivo = new File(PASTA_DESTINO_ARQUIVO + "\\" +string);
					this.files.add(arquivo);
				}
			}
		}
	}

	private void initAutores() {
		List<Autor> autores = autorDao.findAutorByIDProducao(this.producaoAcademica.getIdProducaoAcademica());
	    this.autoresVO = new ArrayList<AutorVO>();
		for(Autor autor : autores){
			if((autor.getTipoAutor().equals("PROFESSOR") || autor.getTipoAutor().equals("DIRETOR")) && (autor.getTipoAcao().equals("AUTOR"))){
				Docente docente = docenteDao.findById(autor.getCodAutor());
				this.autoresVO.add(montarAutorVO(docente));
			} else if(autor.getTipoAutor().equals("ALUNO")&& (autor.getTipoAcao().equals("AUTOR"))){
				Aluno aluno = alunoDao.findById(autor.getCodAutor());
				this.autoresVO.add(montarAutorVO(aluno));
			}else if(autor.getTipoAcao().equals("COORIENTADOR")){
				Docente docente = docenteDao.findById(autor.getCodAutor());
				AutorVO vo = montarAutorVO(docente);
				this.coorientadorDesc = vo.getMatricula() + " - " + vo.getNome();
			}else if(autor.getTipoAcao().equals("ORIENTADOR")){
				Docente docente = docenteDao.findById(autor.getCodAutor());
				AutorVO vo = montarAutorVO(docente);
				this.orientadorDesc = vo.getMatricula() + " - " + vo.getNome();
			}
		}
	}
	
	private AutorVO montarAutorVO(Aluno aluno){
		AutorVO vo = new AutorVO();
		vo.setId(aluno.getIdAluno());
		vo.setMatricula(aluno.getMatricula());
		vo.setNome(aluno.getNome());
		vo.setTipo("ALUNO");
		return vo;
	}
	
	private AutorVO montarAutorVO(Docente docente){
		AutorVO vo = new AutorVO();
		vo.setId(docente.getIdDocente());
		vo.setMatricula(docente.getMatricula());
		vo.setNome(docente.getNome());
		vo.setTipo(docente.getTipoDocente().getTipo());
		return vo;
	}

	private void initTiposProducaoNewPage(){
		if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(1))){
			this.livroDao = new LivroDaoImpl();
			this.livro    = this.livroDao.buscarByidProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(2))){
			this.jornalRevistaDao = new JornalRevistaDaoImpl(); 
			this.jornalRevista    = this.jornalRevistaDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(3))){
			this.periodicoDao = new PeriodicoDaoImpl();
			this.periodico    = this.periodicoDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		} else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(4))){
			this.trabalhoEmAnaisDao = new TrabalhoEmAnaisDaoImpl();
			this.trabalhoEmAnais    = this.trabalhoEmAnaisDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(5))){
			this.traducaoDao = new TraducaoDaoImpl();
			this.traducao    = this.traducaoDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(6))){
			this.servicosTecnicosDao = new ServicoTecnicosDaoImpl();
			this.servicosTecnicos    = this.servicosTecnicosDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(7))){
			this.cartaMapaSimilaresDao = new CartasMapasSimilaresDaoImpl();
			this.cartaMapaSimilares    = this.cartaMapaSimilaresDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(8))){
			this.cursoCurtaDuracaoDao = new CursoCurtaDuracaoDaoImpl();
			this.cursoCurtaDuracao    = this.cursoCurtaDuracaoDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(9))){
			this.desenvAppDao = new DesenvAppDaoImpl();	
			this.desenvApp    = this.desenvAppDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(10))){
			this.desenvDidaticoInstiDao = new DesenvDidaticoInstDaoImpl();
			this.desenvDidaticoInst     = this.desenvDidaticoInstiDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(11))){
			this.desenvProdutoDao = new DesenvProdutoDaoImpl();
			this.desenvProduto    = this.desenvProdutoDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(12))){
			this.desenvTecnicaDao = new DesenvTecnicaDaoImpl();
			this.desenvTecnica    = this.desenvTecnicaDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(13))){
			this.editoriaDao = new EditoriaDaoImpl();
			this.editoria    = this.editoriaDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(14))){
			this.organizEnventDao  = new OrganizacaoEventoDaoImpl();
			this.organizacaoEvento = this.organizEnventDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(15))){
			this.relatorioPesquisaDao = new RelatorioPesquisaDaoImpl();
			this.relatorioPesquisa    = this.relatorioPesquisaDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(16))){
		    this.apresentacaoTrabalhoDao = new ApresentacaoTrabalhoDaoImpl();
			this.apresentacaoTrabalho    = this.apresentacaoTrabalhoDao.buscarByIdProducao(this.producaoAcademica.getIdProducaoAcademica());
		}
	}

	
	private void initTiposProducao(){
		this.periodico          = new ArtigoPeriodico         ();
		this.jornalRevista      = new ArtigoJornalRevista     ();
		this.livro              = new Livro                   ();
		this.trabalhoEmAnais    = new TrabalhoEmAnais         ();
		this.traducao           = new Traducao                ();
		this.servicosTecnicos   = new ServicosTecnicos        ();
		this.cartaMapaSimilares = new CartaMapaSimilares      ();
		this.cursoCurtaDuracao  = new CursoCurtaDuracao       ();
		this.desenvApp          = new DesenvApp               ();
		this.desenvDidaticoInst = new DesenvDidaticoInstitucional();
		this.desenvProduto      = new DesenvProduto           ();
		this.desenvTecnica      = new DesenvTecnica           ();
		this.editoria           = new Editoria                ();
		this.organizacaoEvento  = new OrganizacaoEvento       ();
		this.relatorioPesquisa  = new RelatorioPesquisa       ();
		this.apresentacaoTrabalho = new ApresentacaoTrabalho  ();		
	}

	public void upload(FileUploadEvent event) {
		this.uploadFile = event.getFile();
		try {
			producaoAcademica.setArquivo(PASTA_DESTINO_ARQUIVO + this.uploadFile.getFileName());
			FileUtil.upload(PASTA_DESTINO_ARQUIVO, this.uploadFile.getFileName(), this.uploadFile.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    uploadFiles.add(this.uploadFile);
	}
	
		
	public void download(File arq) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            externalContext.responseReset();
            
            externalContext.setResponseContentType("application/octet-stream");
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename="+ arq.getName() );

            FileInputStream inputStream = new FileInputStream(new File(arq.getAbsolutePath()));
            OutputStream outputStream = externalContext.getResponseOutputStream();

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
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
		this.producaoAcademica.setStatusAprovacao(this.statusAprovacaoDao.findById(new Integer(1)));
		montarNomeArquivos();
		salvarTipoProducao();
		salvarAutores();
		salvarExterno();
		cadastraHistorico("Foi cadastrado com sucesso.",this.producaoAcDao.findByProdAc(this.producaoAcademica));
		setMessageSuccess("Cadastrado com sucesso!");
	}
	
	private void montarNomeArquivos(){
		StringBuilder sb = new StringBuilder();
		if(this.uploadFiles != null){
			for (UploadedFile file : uploadFiles) {
				sb.append(file.getFileName());
				sb.append(";");
			}
			this.producaoAcademica.setArquivo(sb.toString());
		}
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
	
	private void salvarTipoProducao(){
		if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(1))){
			salvarLivro();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(2))){
			salvarRevista();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(3))){
			salvarPeriodico();
		} else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(4))){
			salvarTrabalhoEmAnais();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(5))){
			salvarTraducao();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(6))){
			salvarServicosTecnicos();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(7))){
			salvarMapaCartaSimilares();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(8))){
			salvarCursoCurtaDuracao();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(9))){
			salvarDesenvApp();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(10))){
			salvarDesenvMdi();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(11))){
			salvarDesenvProduto();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(12))){
			salvarDesenvTecnica();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(13))){
			salvarEditoria();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(14))){
			salvarOranizacaoEvento();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(15))){
			salvarRelatorioPesquisa();
		}else if(this.producaoAcademica.getTipoProducao().getIdTipoProducao().equals(new Integer(16))){
			salvarApresentacaoTrabalho();
		}
	}
	
	private void salvarApresentacaoTrabalho() {
         this.apresentacaoTrabalhoDao = new ApresentacaoTrabalhoDaoImpl();
         this.apresentacaoTrabalho.setProducaoAcademica(this.producaoAcademica);
         this.apresentacaoTrabalhoDao.save(this.apresentacaoTrabalho);
	}

	private void salvarRelatorioPesquisa() {
		this.relatorioPesquisaDao = new RelatorioPesquisaDaoImpl();
		this.relatorioPesquisa.setProducaoAcademica(this.producaoAcademica);
		this.relatorioPesquisaDao.save(this.relatorioPesquisa);
	}

	private void salvarOranizacaoEvento() {
		this.organizEnventDao = new OrganizacaoEventoDaoImpl();
		this.organizacaoEvento.setProducaoAcademica(this.producaoAcademica);
		this.organizEnventDao.save(this.organizacaoEvento);
	}

	private void salvarEditoria() {
       this.editoriaDao = new EditoriaDaoImpl();
       this.editoria.setProducaoAcademica(this.producaoAcademica);
       this.editoriaDao.save(this.editoria);
	}

	private void salvarDesenvTecnica() {
        this.desenvTecnicaDao = new DesenvTecnicaDaoImpl();
        this.desenvTecnica.setProducaoAcademica(this.producaoAcademica);
        this.desenvTecnicaDao.save(this.desenvTecnica);
	}

	private void salvarDesenvProduto() {
        this.desenvProdutoDao = new DesenvProdutoDaoImpl();
        this.desenvProduto.setProducaoAcademica(this.producaoAcademica);
        this.desenvProdutoDao.save(this.desenvProduto);
	}

	private void salvarDesenvMdi() {
		this.desenvDidaticoInstiDao = new DesenvDidaticoInstDaoImpl();
		this.desenvDidaticoInst.setProducaoAcademica(this.producaoAcademica);
		this.desenvDidaticoInstiDao.save(this.desenvDidaticoInst);
	}

	private void salvarDesenvApp() {
        this.desenvAppDao = new DesenvAppDaoImpl();	
        this.desenvApp.setProducaoAcademica(this.producaoAcademica);
        this.desenvAppDao.save(this.desenvApp);
	}

	private void salvarCursoCurtaDuracao() {
        this.cursoCurtaDuracaoDao = new CursoCurtaDuracaoDaoImpl();
        this.cursoCurtaDuracao.setProducaoAcademica(this.producaoAcademica);
        this.cursoCurtaDuracaoDao.save(this.cursoCurtaDuracao);
	}

	private void salvarMapaCartaSimilares() {
		this.cartaMapaSimilaresDao = new CartasMapasSimilaresDaoImpl();
		this.cartaMapaSimilares.setProducaoAcademica(this.producaoAcademica);
		this.cartaMapaSimilaresDao.save(this.cartaMapaSimilares);
	}

	public void salvarServicosTecnicos(){
		this.servicosTecnicosDao = new ServicoTecnicosDaoImpl();
		this.servicosTecnicos.setProducaoAcademica(this.producaoAcademica);
		this.servicosTecnicosDao.save(this.servicosTecnicos);
	}
	
	public void salvarTraducao(){
		this.traducaoDao = new TraducaoDaoImpl();
		this.traducao.setProducaoAcademica(this.producaoAcademica);
		this.traducaoDao.save(this.traducao);
	}
	
	public void salvarTrabalhoEmAnais(){
		this.trabalhoEmAnaisDao = new TrabalhoEmAnaisDaoImpl();
		this.trabalhoEmAnais.setProducaoAcademica(this.producaoAcademica);
		this.trabalhoEmAnaisDao.save(this.trabalhoEmAnais);
	}
	
	public void salvarPeriodico(){
		this.periodicoDao = new PeriodicoDaoImpl();
		this.periodico.setProducaoAcademica(this.producaoAcademica);
	    this.periodicoDao.save(this.periodico);
	}
	
	public void salvarRevista(){
		this.jornalRevistaDao = new JornalRevistaDaoImpl(); 
		this.jornalRevista.setProducaoAcademica(this.producaoAcademica);
		this.jornalRevistaDao.save(this.jornalRevista);
	}

	public void salvarLivro(){
		this.livroDao = new LivroDaoImpl();
		this.livro.setProducaoAcademica(this.producaoAcademica);
	    this.livroDao.save(this.livro);	
	}
	
	public void editar(ProducaoAcademica producaoAcademica) {
		this.producaoAcDao.update(producaoAcademica);
		cadastraHistorico("Foi alterado com sucesso.", producaoAcademica);
		
	}

	public void excluir(ProducaoAcademica producaoAcademica) {
		this.producaoAcDao.remove(producaoAcademica);
	}

	public boolean isTipoProducaoByCod(Integer codigo) {
		if (this.tipoProducao.getIdTipoProducao() != null)
			return this.tipoProducao.getIdTipoProducao().equals(new Integer(codigo));
		return Boolean.FALSE;
	}

	public List<Docente> getListaOrientadores(){
		return docenteDao.list();
	}
	
	public List<AutorVO> getListAutores(){
		return montarListaAutores();
	}
	
	public Autor montarOrientador(String tipo, ProducaoAcademica pa){
		Autor participante = null;
		if(tipo.equals("COORIENTADOR")){
			participante = this.coorientador;
		}else{
			participante = this.orientador;
		}
		if(participante != null){
			Docente  docente = docenteDao.findById(participante.getCodAutor());
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
	    autores.addAll(convertAlunoEmAutorVO());
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
	
	public void cadastraHistorico(String mensagem, ProducaoAcademica producao) {
		this.historico.setDataAlteracao(new Date());
		this.historico.setProducaoAcademica(producao);
		if (isDiretor() || isProfessor()) {
			this.historico.setDocente(this.docenteDao.getDocentebyMatricula(user.getUsuario().getMatricula()));
		}else{
			this.historico.setAluno(this.alunoDao.getAlunobyMatricula(user.getUsuario().getMatricula()));
		}	
		if(isDiscente()){
			this.historico.setAlteracao("Produção Acadêmica: " + producao.getTitulo() + "\n" + mensagem + "\n" + "Responsável: "
					+ this.historico.getAluno().getNome());
		}else{
			this.historico.setAlteracao("Produção Acadêmica: " + producao.getTitulo() + "\n" + mensagem + "\n" + "Responsável: "
				+ this.historico.getDocente().getNome());
		}
		this.historicoDao.save(historico);
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
	
	public List<DescisaoEnum> getDescisao(){
		return DescisaoEnum.list();
	}
	
	public List<NaturezaApresentacaoTrabalhoEnum> getNaturezaApresentacaoTrabalho(){
		return NaturezaApresentacaoTrabalhoEnum.list();
	}
	
	public List<NaturezaOrganizacaoEventoEnum> getNaturezaOrganizacaoEvento(){
		return NaturezaOrganizacaoEventoEnum.list();
	}
	
	public List<NaturezaDesenvolvimentoAppEnum> getNaturezaDesenvApp(){
		return NaturezaDesenvolvimentoAppEnum.list();
	}
	
	public List<NaturezaDesenvolvimentoProdutoEnum> getNaturezaDesenvProduto(){
		return NaturezaDesenvolvimentoProdutoEnum.list();
	}
	
	public List<NaturezaEditoriaEnum> getNaturezaEditoria(){
		return NaturezaEditoriaEnum.list();
	}
	
	public List<NaturezaDesenvolvimentoTecnicaEnum> getNaturezaDesenvTecnica(){
		return NaturezaDesenvolvimentoTecnicaEnum.list();
	}

	public List<NaturezaEnum> getNatureza() {
		return new ArrayList<NaturezaEnum>(Arrays.asList(NaturezaEnum.values()));
	}
	
	public List<NaturezaTraducaoEnum> getNaturezaTraducao(){
		return NaturezaTraducaoEnum.list();
	}
	
	public List<NaturezaServicoTecnicosEnum> getNaturezaServicosTecnicos(){
		return NaturezaServicoTecnicosEnum.list();
	}
	
	public List<NaturezaCartaEnum> getNaturezaCarta(){
		return NaturezaCartaEnum.list();
	}
	
	public List<DisponibilidadeEnum> getDisponibilidade(){
		return DisponibilidadeEnum.list();
	}

	public List<DivulgacaoEnum> getDivulgacao() {
		return DivulgacaoEnum.list();
	}
	
	public List<NivelCursoCurtaDuaracaoEnum> getNivelCursoCurtaDuracao(){
		return NivelCursoCurtaDuaracaoEnum.list();
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
	
	public List<TipoOrganizacaoEventoEnum> getTipoOrganizacaoEvento(){
		return TipoOrganizacaoEventoEnum.list();
	}
	
	public List<TipoDesenvolvimentoProdutoEnum> getTipoDesenvProd(){
		return TipoDesenvolvimentoProdutoEnum.list();
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
	
	public List<StatusAprovacao> getVariosStatusAprovacao() {
		return this.statusAprovacaoDao.list();
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

	public List<Autor> getAutores() {
		return this.autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public TrabalhoEmAnais getTrabalhoEmAnais() {
		return this.trabalhoEmAnais;
	}

	public void setTrabalhoEmAnais(TrabalhoEmAnais trabalhoEmAnais) {
		this.trabalhoEmAnais = trabalhoEmAnais;
	}

	public Traducao getTraducao() {
		return this.traducao;
	}

	public void setTraducao(Traducao traducao) {
		this.traducao = traducao;
	}

	public ServicosTecnicos getServicosTecnicos() {
		return this.servicosTecnicos;
	}

	public void setServicosTecnicos(ServicosTecnicos servicosTecnicos) {
		this.servicosTecnicos = servicosTecnicos;
	}

	public DesenvAppDao getDesenvAppDao() {
		return this.desenvAppDao;
	}

	public void setDesenvAppDao(DesenvAppDao desenvAppDao) {
		this.desenvAppDao = desenvAppDao;
	}

	public DesenvApp getDesenvApp() {
		return this.desenvApp;
	}

	public void setDesenvApp(DesenvApp desenvApp) {
		this.desenvApp = desenvApp;
	}

	public DesenvDidaticoInstitucional getDesenvDidaticoInst() {
		return this.desenvDidaticoInst;
	}

	public void setDesenvDidaticoInst(DesenvDidaticoInstitucional desenvDidaticoInst) {
		this.desenvDidaticoInst = desenvDidaticoInst;
	}

	public DesenvProduto getDesenvProduto() {
		return this.desenvProduto;
	}

	public void setDesenvProduto(DesenvProduto desenvProduto) {
		this.desenvProduto = desenvProduto;
	}

	public DesenvTecnica getDesenvTecnica() {
		return this.desenvTecnica;
	}

	public void setDesenvTecnica(DesenvTecnica desenvTecnica) {
		this.desenvTecnica = desenvTecnica;
	}

	public OrganizacaoEvento getOrganizacaoEvento() {
		return this.organizacaoEvento;
	}

	public void setOrganizacaoEvento(OrganizacaoEvento organizacaoEvento) {
		this.organizacaoEvento = organizacaoEvento;
	}

	public RelatorioPesquisa getRelatorioPesquisa() {
		return this.relatorioPesquisa;
	}

	public void setRelatorioPesquisa(RelatorioPesquisa relatorioPesquisa) {
		this.relatorioPesquisa = relatorioPesquisa;
	}

	public CartaMapaSimilares getCartaMapaSimilares() {
		return this.cartaMapaSimilares;
	}

	public void setCartaMapaSimilares(CartaMapaSimilares cartaMapaSimilares) {
		this.cartaMapaSimilares = cartaMapaSimilares;
	}

	public CursoCurtaDuracao getCursoCurtaDuracao() {
		return this.cursoCurtaDuracao;
	}

	public void setCursoCurtaDuracao(CursoCurtaDuracao cursoCurtaDuracao) {
		this.cursoCurtaDuracao = cursoCurtaDuracao;
	}

	public Editoria getEditoria() {
		return this.editoria;
	}

	public void setEditoria(Editoria editoria) {
		this.editoria = editoria;
	}

	public ApresentacaoTrabalho getApresentacaoTrabalho() {
		return this.apresentacaoTrabalho;
	}

	public void setApresentacaoTrabalho(ApresentacaoTrabalho apresentacaoTrabalho) {
		this.apresentacaoTrabalho = apresentacaoTrabalho;
	}
	
	public AcaoEnum getAcaoEnum() {
		return this.acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}

	public boolean isDiretor(){
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_DIRETOR")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isAluno(){
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_ALUNO")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isProfessor(){
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if(iterator.next().getAuthority().equals("ROLE_PROFESSOR")){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public String getOrientadorDesc() {
		return this.orientadorDesc;
	}

	public void setOrientadorDesc(String orientadorDesc) {
		this.orientadorDesc = orientadorDesc;
	}

	public String getCoorientadorDesc() {
		return this.coorientadorDesc;
	}

	public void setCoorientadorDesc(String coorientadorDesc) {
		this.coorientadorDesc = coorientadorDesc;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public StreamedContent getFile() {
		return this.file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
