package br.ucb.MB;

import java.util.Date;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.VO.DocenteVO;
import br.ucb.dao.CursoDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.EnderecoDao;
import br.ucb.dao.HistoricoDao;
import br.ucb.dao.TipoDocenteDao;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.dao.impl.HistoricoDaoImpl;
import br.ucb.dao.impl.TipoDocenteDaoImpl;
import br.ucb.entity.Curso;
import br.ucb.entity.Docente;
import br.ucb.entity.Endereco;
import br.ucb.entity.Historico;
import br.ucb.entity.TipoDocente;
import br.ucb.enums.AcaoEnum;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;

@ManagedBean(name = "docenteMB")
@ViewScoped
public class DocenteMB extends BaseMB{

	private static final long serialVersionUID = 4035284422141615787L;
	
	private DocenteDao docenteDao;
	private TipoDocenteDao tipoDocenteDao;
	private CursoDao cursoDao;
	private Docente docente;
	private List<Docente> docentes;
	private TipoDocente tipoDocente;
	private Curso curso;
	private EnderecoDao enderecoDao;
    private AcaoEnum acaoEnum;
    private DocenteVO docentePesq;
	private Historico historico;
	private HistoricoDao historicoDao;
	private UsuarioSistema user;

	
	@PostConstruct
	public void init() {
		this.docenteDao     = new DocenteDaoImpl();
		this.tipoDocenteDao = new TipoDocenteDaoImpl();
		this.cursoDao       = new CursoDaoImpl();
		this.enderecoDao    = new EnderecoDaoImpl();
		inicializar();
	}
	
	public void inicializar(){		
		this.tipoDocente    = new TipoDocente(); 
		this.docentes       = docenteDao.list();
		this.docente        = new Docente();
		this.curso          = new Curso();
		this.docentePesq    = new DocenteVO();
		this.docente.setEndereco(new Endereco());
		this.historico		= new Historico();
		this.historicoDao 	= new HistoricoDaoImpl();
		this.user 			= new Seguranca().getUsuarioLogado();
		this.acaoEnum       = AcaoEnum.LISTAR;
	}
	
	public void atualizar(){
		if(this.acaoEnum.getCodigo() == AcaoEnum.CADASTRAR.getCodigo()){
			cadastrar();
		}else if(this.acaoEnum.getCodigo() == AcaoEnum.EDITAR.getCodigo()){
			editar();
		}
		inicializar();
	}
	
	public void habiliarNovo(){
		this.acaoEnum = AcaoEnum.CADASTRAR;
	}
	
	public void editar(){
		this.enderecoDao.save(this.docente.getEndereco());
		this.docenteDao.update(this.docente);
		cadastraHistorico("Foi alterado com sucesso.", this.docente);
		setMessageSuccess("Atualizado com sucesso!");
	}

	public void cadastrar() {
		this.docente.setDataCadastro(new Date());
		this.tipoDocente = this.tipoDocenteDao.findByKey(TipoDocente.class, tipoDocente.getIdTipoDocente());
		this.curso       = this.cursoDao.findById(curso.getIdCurso());
		this.enderecoDao.save(this.docente.getEndereco());
		this.docente.setCurso(this.curso);
		this.docente.setDataNascimento(new Date());
		this.docente.setTipoDocente(this.tipoDocente);
		this.docente.setEndereco(this.enderecoDao.find(this.docente.getEndereco()));
		this.docenteDao.save(this.docente);
		cadastraHistorico("Foi cadastrado com sucesso.", this.docenteDao.getDocentebyMatricula(this.docente.getMatricula()));
		setMessageSuccess("Cadastrado com sucesso!");
	}
	
	public void cadastraHistorico(String mensagem, Docente docente) {
		this.historico.setDataAlteracao(new Date());
		this.historico.setDocente(docente);
		this.historico.setAlteracao("Docente: " + docente.getNome() + "\n" + "Matrícula: " + docente.getMatricula() + "\n"
				+ mensagem + "\n" + "Responsável: " + this.docenteDao.getDocentebyMatricula(user.getUsuario().getMatricula()).getNome());
		this.historicoDao.save(historico);
	}

	
	public AcaoEnum getAcaoEnum() {
		return this.acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}

	public void editar(Docente tipo) {
		this.docenteDao.update(tipo);
	}
	
	public void visualizar(Docente docente){
		this.docente = docente;
		acaoEnum = AcaoEnum.VISUALIZAR;
	}

	public void prepararEdicao(Docente docente){
		this.docente = docente;
		acaoEnum = AcaoEnum.EDITAR;
	}
	
	public void excluir(Docente tipo) {
        if(tipo.isAtivo()){
        	tipo.setAtivo(Boolean.FALSE);
			this.docenteDao.update(tipo);
			cadastraHistorico("Foi desativado.", tipo);
			setMessageSuccess("Inativado com sucesso!");
        }else{
        	setMessageError("Docente já está inativado!");
        }
	}
	
	public void voltar(){
		inicializar();
	}
	
	public void buscar(){
		this.docentes = this.docenteDao.find(docentePesq);
	}

	public void limpar() {
		inicializar();
	}
	
	
	
	public List<TipoDocente> getTipoDocentes(){
		return tipoDocenteDao.list();
	}
	
	public List<Curso> getCursos(){
		return cursoDao.list();
	}

	public Docente getDocente() {
		return this.docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public List<Docente> getDocentes() {
		return this.docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public TipoDocente getTipoDocente() {
		return this.tipoDocente;
	}

	public void setTipoDocente(TipoDocente tipoDocente) {
		this.tipoDocente = tipoDocente;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public DocenteVO getDocentePesq() {
		return this.docentePesq;
	}

	public void setDocentePesq(DocenteVO docentePesq) {
		this.docentePesq = docentePesq;
	}	
	
}
