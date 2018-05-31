package br.ucb.MB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ucb.VO.AprovacaoProducaoVO;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.HistoricoDao;
import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.StatusAprovacaoDao;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.HistoricoDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.dao.impl.StatusAprovacaoDaoImpl;
import br.ucb.entity.Historico;
import br.ucb.entity.StatusAprovacao;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;

@ManagedBean(name = "aprovarProducaoProfessorMB")
@ViewScoped
public class AprovarProducaoProfessorMB extends BaseMB {

	private static final long serialVersionUID = 3018502226740620474L;

	private List<AprovacaoProducaoVO> aprovaProducoes;
	private ProducaoAcademicaDao producaoAcademicaDao;
	private StatusAprovacaoDao statusAprovacaoDao;
	private StatusAprovacao aprovar;
	private StatusAprovacao reprovar;
	private UsuarioSistema user;
	private DocenteDao docenteDao;
	private Historico historico;
	private HistoricoDao historicoDao;
	private boolean resultado;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
	}

	public void inicializa() {
		this.aprovaProducoes = new ArrayList<AprovacaoProducaoVO>();
		this.producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
		this.statusAprovacaoDao = new StatusAprovacaoDaoImpl();
		this.aprovar = new StatusAprovacao();
		this.reprovar = new StatusAprovacao();
		this.user = new Seguranca().getUsuarioLogado();
		this.docenteDao = new DocenteDaoImpl();
		this.historico = new Historico();
		this.historicoDao = new HistoricoDaoImpl();

	}

	public void buscar() {
		this.aprovaProducoes = this.producaoAcademicaDao
				.listAprovaProfessor(this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
		try {
			this.aprovar = this.statusAprovacaoDao.findByDescricao("Aprovado").get(0);
			this.reprovar = this.statusAprovacaoDao.findByDescricao("Reprovado").get(0);
		} catch (Exception e) {
			setMessageError(
					"Por favor, comunique ao diretor para adicionar os status 'Aprovado', 'Reprovado' e 'Pendente' na tela de Status da Aprovação  para prosseguir.");
			this.aprovar = new StatusAprovacao();
			this.reprovar = new StatusAprovacao();
			this.aprovaProducoes = new ArrayList<AprovacaoProducaoVO>();
		}

	}

	public void aprovar(AprovacaoProducaoVO producao) {
		producao.setStatusAprovacao(this.aprovar);
		this.resultado = this.producaoAcademicaDao.updateResultadoM(producao);
		if (this.resultado) {
			setMessageSuccess("Produção foi aprovada com sucesso.");
			this.aprovaProducoes.remove(producao);
			cadastraHistorico("Produção foi aprovada.", producao);
		} else
			setMessageError("Houve um problema ao aprovar a produção.");

	}

	public void reprovar(AprovacaoProducaoVO producao) {
		producao.setStatusAprovacao(this.reprovar);
		this.resultado = this.producaoAcademicaDao.updateResultadoM(producao);
		if (this.resultado) {
			setMessageSuccess("Produção foi reprovada com sucesso.");
			this.aprovaProducoes.remove(producao);
			cadastraHistorico("Produção foi reprovada.", producao);
		} else
			setMessageError("Houve um problema ao reprovar a produção.");
	}

	public void cadastraHistorico(String mensagem, AprovacaoProducaoVO producao) {
		this.historico.setDataAlteracao(new Date());
		this.historico.setProducaoAcademica(this.producaoAcademicaDao.findById(producao.getId()));
		this.historico.setDocente(this.docenteDao.getDocentebyMatricula(user.getUsuario().getMatricula()));
		this.historico.setAlteracao("Produção Acadêmica: " + producao.getTitulo() + "\n" + mensagem + "\n"
				+ "Responsável: " + this.historico.getDocente().getNome());
		this.historicoDao.save(historico);
	}

	public List<AprovacaoProducaoVO> getAprovaProducoes() {
		return aprovaProducoes;
	}

	public void setAprovaProducoes(List<AprovacaoProducaoVO> aprovaProducoes) {
		this.aprovaProducoes = aprovaProducoes;
	}

	public ProducaoAcademicaDao getProducaoAcademicaDao() {
		return producaoAcademicaDao;
	}

	public void setProducaoAcademicaDao(ProducaoAcademicaDao producaoAcademicaDao) {
		this.producaoAcademicaDao = producaoAcademicaDao;
	}

	public StatusAprovacaoDao getStatusAprovacaoDao() {
		return statusAprovacaoDao;
	}

	public void setStatusAprovacaoDao(StatusAprovacaoDao statusAprovacaoDao) {
		this.statusAprovacaoDao = statusAprovacaoDao;
	}

	public StatusAprovacao getAprovar() {
		return aprovar;
	}

	public void setAprovar(StatusAprovacao aprovar) {
		this.aprovar = aprovar;
	}

	public StatusAprovacao getReprovar() {
		return reprovar;
	}

	public void setReprovar(StatusAprovacao reprovar) {
		this.reprovar = reprovar;
	}

}
