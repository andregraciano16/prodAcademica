package br.ucb.MB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.CursoDao;
import br.ucb.dao.EnderecoDao;
import br.ucb.dao.StatusAlunoDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.CursoDaoImpl;
import br.ucb.dao.impl.EnderecoDaoImpl;
import br.ucb.dao.impl.StatusAlunoDaoImpl;
import br.ucb.entity.Aluno;
import br.ucb.entity.Curso;
import br.ucb.entity.Endereco;
import br.ucb.entity.StatusAluno;
import br.ucb.enums.AcaoEnum;

@ManagedBean(name = "alunoMB")
@ViewScoped
public class AlunoMB extends BaseMB {

	private static final long serialVersionUID = 479227576693611217L;

	private List<Aluno> alunos;
	private Aluno aluno;
	private AlunoDao alunoDao;
	private Aluno editavel;
	private List<Curso> cursos;
	private CursoDao cursoDao;
	private List<StatusAluno> variosStatus;
	private StatusAlunoDao statusAlunoDao;
	private List<Endereco> enderecos;
	private EnderecoDao enderecoDao;
	private Endereco endereco;
	private AcaoEnum acaoEnum;
	private LineChartModel grafico;
	private String graficoFiltro = "1";

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.LISTAR);
		createGrafico();
	}

	public void selecionaFiltro() {
		init();

	}

	public void cadastrar() {
		if (!verificaVazio(this.aluno, this.endereco)) {

			if (this.alunos.contains(this.aluno)) {
				setMessageError("Já contém este registro, por favor insira um novo.");
			} else {
				montar(this.aluno, this.endereco);
				this.alunoDao.save(this.aluno);
				setMessageSuccess("Cadastrado com sucesso.");
			}

		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();
	}

	public void excluir(Aluno aluno) {
		this.alunoDao.remove(aluno);
		setMessageSuccess("Excluído com sucesso.");
		init();
	}

	public void editar() {

		if (this.aluno != null && !verificaVazio(this.aluno, this.endereco)) {
			this.enderecoDao.update(this.endereco);
			this.alunoDao.update(this.aluno);
			setMessageSuccess("Atualizado com sucesso.");
		} else {
			setMessageError("Preencha os campos corretamente.");
		}
		init();

	}

	public void prepararEdicao(Aluno aluno) {
		this.aluno = aluno;
		this.endereco = aluno.getEndereco();
		acaoEnum = AcaoEnum.EDITAR;
	}

	public void visualizar(Aluno aluno) {
		this.aluno = aluno;
		this.endereco = aluno.getEndereco();
		acaoEnum = AcaoEnum.VISUALIZAR;
	}

	public void buscar() {

		if (this.aluno != null) {
			if (verificaVazio(this.aluno)) {
				this.alunos = this.alunoDao.list();
				this.enderecos = this.enderecoDao.list();
				this.cursos = this.cursoDao.list();
				this.variosStatus = this.statusAlunoDao.list();
			} else {
				this.alunos = this.alunoDao.findBySearch(this.aluno);
				this.enderecos = this.enderecoDao.list();
				this.cursos = this.cursoDao.list();
				this.variosStatus = this.statusAlunoDao.list();
			}
		}
	}

	public void limpar() {
		init();
	}

	public void limparCadastro() {
		inicializa();
		buscar();
		this.setAcaoEnum(AcaoEnum.CADASTRAR);
	}

	private void inicializa() {
		this.alunos = new ArrayList<Aluno>();
		this.aluno = new Aluno();
		this.aluno.setNome("");
		this.aluno.setTelefoneFixo("");
		this.aluno.setSexo('\0');
		this.aluno.setCelular("");
		this.aluno.setMatricula("");
		this.aluno.setEmail("");
		this.aluno.setAtivo(false);
		this.aluno.setDataCadastro(null);
		this.aluno.setDataNascimento(null);
		this.aluno.setEndereco(null);
		this.aluno.setCurso(null);
		this.aluno.setStatusAluno(null);
		this.alunoDao = new AlunoDaoImpl();
		this.cursoDao = new CursoDaoImpl();
		this.statusAlunoDao = new StatusAlunoDaoImpl();
		this.editavel = new Aluno();
		this.enderecos = new ArrayList<Endereco>();
		this.enderecoDao = new EnderecoDaoImpl();
		this.endereco = new Endereco();

	}

	private void montar(Aluno aluno, Endereco endereco) {
		if (aluno == null) {
			aluno = new Aluno();
		}
		aluno.setIdAluno(null);
		aluno.setNome(aluno.getNome().trim());
		aluno.setTelefoneFixo(aluno.getTelefoneFixo().trim());
		aluno.setCelular(aluno.getCelular().trim());
		aluno.setMatricula(aluno.getMatricula().trim());
		aluno.setEmail(aluno.getEmail().trim());
		aluno.setDataCadastro(new Date());
		endereco.setBairro(endereco.getBairro().trim());
		endereco.setCidade(endereco.getCidade().trim());
		endereco.setComplemento(endereco.getComplemento().trim());
		endereco.setEstado(endereco.getEstado().trim());
		endereco.setRua(endereco.getRua().trim());
		this.enderecoDao.save(endereco);
		endereco = this.enderecoDao.find(endereco);
		aluno.setEndereco(endereco);

	}

	public void habilitarNovo() {
		this.acaoEnum = AcaoEnum.CADASTRAR;
	}

	private void createGrafico() {

		if (getGraficoFiltro().equals("1")) {
			grafico = initGraficoAnos();
		} else {
			grafico = initGraficoTotal();
		}

		grafico.setTitle("Gráfico: Acompanhamento de alunos");
		grafico.setLegendPosition("w");
		grafico.setShowPointLabels(true);
		grafico.getAxes().put(AxisType.X, new CategoryAxis("Tempo"));
		grafico.setZoom(true);
		grafico.setAnimate(true);
		grafico.setResetAxesOnResize(true);
		grafico.setShadow(true);

		Axis yAxis = grafico.getAxis(AxisType.Y);
		yAxis.setLabel("n° de cadastros");
		yAxis.setMin(0);

	}

	private LineChartModel initGraficoTotal() {
		LineChartModel model = new LineChartModel();
		List<Aluno> alunosCadastro = new ArrayList<Aluno>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de alunos");

		alunosCadastro = this.alunos;
		Collections.sort(alunosCadastro);

		for (Aluno aluno : alunosCadastro) {

			c.setTime(aluno.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			aluno.setDataCadastro(c.getTime());

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				series1.set(c.get(Calendar.MONTH) + 1 + "/" + c.get(Calendar.YEAR), getCadastroMes(alunosCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoAnos() {
		LineChartModel model = new LineChartModel();
		List<Aluno> alunosCadastro = new ArrayList<Aluno>();

		Calendar c = Calendar.getInstance();

		int ultimoMes = 0;
		int ultimoAno = 0;
		ChartSeries meses = new ChartSeries();
		DateFormat df2 = new SimpleDateFormat("MMM", new Locale("pt", "BR"));

		meses.set("jan", -1);
		meses.set("fev", -1);
		meses.set("mar", -1);
		meses.set("abr", -1);
		meses.set("mai", -1);
		meses.set("jun", -1);
		meses.set("jul", -1);
		meses.set("ago", -1);
		meses.set("set", -1);
		meses.set("out", -1);
		meses.set("nov", -1);
		meses.set("dez", -1);

		alunosCadastro = this.alunos;
		Collections.sort(alunosCadastro);

		for (Aluno aluno : alunosCadastro) {

			c.setTime(aluno.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			aluno.setDataCadastro(c.getTime());

			if (ultimoMes == 0 && ultimoAno == 0) {

				meses.set(df2.format(aluno.getDataCadastro()), getCadastroMes(alunosCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

			if (c.get(Calendar.YEAR) != ultimoAno) {
				meses.setLabel(String.valueOf(ultimoAno));
				model.addSeries(meses);
				meses = new ChartSeries();
				meses.set("jan", -1);
				meses.set("fev", -1);
				meses.set("mar", -1);
				meses.set("abr", -1);
				meses.set("mai", -1);
				meses.set("jun", -1);
				meses.set("jul", -1);
				meses.set("ago", -1);
				meses.set("set", -1);
				meses.set("out", -1);
				meses.set("nov", -1);
				meses.set("dez", -1);
				meses.set(df2.format(aluno.getDataCadastro()), getCadastroMes(alunosCadastro, c));
				ultimoMes = 0;
				ultimoAno = 0;
			}

			if (c.get(Calendar.MONTH) != ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {
				meses.set(df2.format(aluno.getDataCadastro()), getCadastroMes(alunosCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}
		meses.setLabel(String.valueOf(c.get(Calendar.YEAR)));
		model.addSeries(meses);
		return model;

	}

	private int getCadastroMes(List<Aluno> alunosCadastro, Calendar data) {
		int qtdCadastro = 0;
		Calendar cadastro = Calendar.getInstance();

		for (Aluno aluno : alunosCadastro) {
			cadastro.setTime(aluno.getDataCadastro());
			cadastro.set(Calendar.HOUR_OF_DAY, cadastro.get(Calendar.HOUR_OF_DAY) + 3);

			if (cadastro.get(Calendar.MONTH) == data.get(Calendar.MONTH)
					&& cadastro.get(Calendar.YEAR) == data.get(Calendar.YEAR)) {
				qtdCadastro++;
			}
		}

		return qtdCadastro;
	}

	private boolean verificaVazio(Aluno aluno, Endereco endereco) {

		if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
			return true;
		}

		if (aluno.getTelefoneFixo() == null || aluno.getTelefoneFixo().trim().isEmpty()) {
			return true;
		}

		if (aluno.getSexo() == '\0') {
			return true;
		}

		if (aluno.getCelular() == null || aluno.getCelular().trim().isEmpty()) {
			return true;
		}

		if (aluno.getMatricula() == null || aluno.getMatricula().trim().isEmpty()) {
			return true;
		}

		if (aluno.getEmail() == null || aluno.getEmail().trim().isEmpty()) {
			return true;
		}

		if (aluno.getDataNascimento() == null) {
			return true;
		}

		if (aluno.getCurso() == null) {
			return true;
		}

		if (aluno.getStatusAluno() == null) {
			return true;
		}

		if (endereco.getBairro() == null || endereco.getBairro().trim().isEmpty()) {
			return true;
		}

		if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty()) {
			return true;
		}

		if (endereco.getEstado() == null || endereco.getEstado().trim().isEmpty()) {
			return true;
		}

		if (endereco.getComplemento() == null || endereco.getComplemento().trim().isEmpty()) {
			return true;
		}

		if (endereco.getNumero() == null) {
			return true;
		}

		if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean verificaVazio(Aluno aluno) {
		boolean flag = true;

		if (aluno.getNome() != null && !aluno.getNome().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getTelefoneFixo() != null && !aluno.getTelefoneFixo().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getSexo() != '\0') {
			flag = false;
		}

		if (aluno.getCelular() != null && !aluno.getCelular().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getMatricula() != null && !aluno.getMatricula().trim().isEmpty()) {
			flag = false;
		}

		if (aluno.getDataNascimento() != null) {
			flag = false;
		}

		if (aluno.getCurso() != null) {
			flag = false;
		}

		if (aluno.getStatusAluno() != null) {
			flag = false;
		}

		return flag;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public AlunoDao getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public Aluno getEditavel() {
		return editavel;
	}

	public void setEditavel(Aluno editavel) {
		this.editavel = editavel;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Endereco> getLinhasPesquisa() {
		return enderecos;
	}

	public void setLinhasPesquisa(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public EnderecoDao getEnderecoDao() {
		return enderecoDao;
	}

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	public List<Aluno> getTiposAluno() {
		return alunos;
	}

	public void setTiposAluno(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<StatusAluno> getVariosStatus() {
		return variosStatus;
	}

	public void setVariosStatus(List<StatusAluno> variosStatus) {
		this.variosStatus = variosStatus;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public CursoDao getCursoDao() {
		return cursoDao;
	}

	public void setCursoDao(CursoDao cursoDao) {
		this.cursoDao = cursoDao;
	}

	public StatusAlunoDao getStatusAlunoDao() {
		return statusAlunoDao;
	}

	public void setStatusAlunoDao(StatusAlunoDao statusAlunoDao) {
		this.statusAlunoDao = statusAlunoDao;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public AcaoEnum getAcaoEnum() {
		return acaoEnum;
	}

	public void setAcaoEnum(AcaoEnum acaoEnum) {
		this.acaoEnum = acaoEnum;
	}

	public String getGraficoFiltro() {
		return graficoFiltro;
	}

	public void setGraficoFiltro(String graficoFiltro) {
		this.graficoFiltro = graficoFiltro;
	}

	public LineChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(LineChartModel grafico) {
		this.grafico = grafico;
	}

}
