package br.ucb.MB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
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
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.entity.Aluno;

@ManagedBean(name = "relatorioAlunoMB")
@ViewScoped
public class RelatorioAlunoMB extends BaseMB {

	private static final long serialVersionUID = 1L;
	private List<Aluno> alunos;
	private LineChartModel grafico;
	private String graficoFiltro = "1";
	private AlunoDao alunoDao;
	private String anoInicio;
	private String anoFim;
	private String anoTempInicio;
	private String anoTempFim;
	private Calendar anoAtual;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		createGrafico();
	}

	public void selecionaFiltro() {
		createGrafico();
	}

	public void buscar() {
		this.alunos = this.alunoDao.list();
	}

	private void createGrafico() {

		if (getGraficoFiltro().equals("1")) {
			if (this.anoInicio.equals("") || this.anoInicio == null)
				anoInicio = anoTempInicio;
			if (this.anoFim.equals("") || this.anoFim == null)
				anoFim = anoTempFim;
			verificaGraficoFiltro();
			grafico = initGraficoAnosFiltro();

		} else if (getGraficoFiltro().equals("2")) {
			if (this.anoInicio.equals("") || this.anoInicio == null)
				anoInicio = anoTempInicio;
			if (this.anoFim.equals("") || this.anoFim == null)
				anoFim = anoTempFim;
			verificaGraficoFiltro();
			grafico = initGraficoTotalFiltro();

		} else if (getGraficoFiltro().equals("3")) {
			if (this.anoInicio.equals("") || this.anoInicio == null)
				anoInicio = anoTempInicio;
			if (this.anoFim.equals("") || this.anoFim == null)
				anoFim = anoTempFim;
			verificaGraficoFiltro();
			grafico = initGraficoSemestreTotalFiltro();

		} else if (getGraficoFiltro().equals("4")) {
			grafico = initGraficoAnos();
		} else if (getGraficoFiltro().equals("5")) {
			grafico = initGraficoTotal();
		} else if (getGraficoFiltro().equals("6")) {
			grafico = initGraficoSemestreTotal();
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
		yAxis.setTickFormat("%.0f");
		yAxis.setTickInterval("1");
		yAxis.setMin(0);

	}

	private void verificaGraficoFiltro() {

		if (anoTempInicio.isEmpty() || anoTempFim.isEmpty()) {
			this.anoTempFim = String.valueOf(anoAtual.get(Calendar.YEAR));
			this.anoTempInicio = String.valueOf(anoAtual.get(Calendar.YEAR) - 3);
		}

		if (!anoInicio.isEmpty() && !anoFim.isEmpty()) {
			if (Integer.valueOf(anoInicio) > Integer.valueOf(anoFim)) {
				setMessageError("O ano do inicio não pode ser maior que o ano do fim.");
				anoInicio = anoTempInicio;
				anoFim = anoTempFim;
			}
			if (Integer.valueOf(anoInicio) < 1950 || Integer.valueOf(anoFim) < 1950) {
				setMessageError("O ano mínimo é 1950.");
				anoInicio = anoTempInicio;
				anoFim = anoTempFim;
			}
			if (Integer.valueOf(anoInicio) > anoAtual.get(Calendar.YEAR)
					|| Integer.valueOf(anoFim) > anoAtual.get(Calendar.YEAR)) {
				setMessageError("O ano máximo é o ano atual (" + anoAtual.get(Calendar.YEAR) + ").");
				anoInicio = anoTempInicio;
				anoFim = anoTempFim;
			}
		}

		if (!this.anoInicio.isEmpty())
			anoTempInicio = anoInicio;
		if (!this.anoFim.isEmpty())
			anoTempFim = anoFim;

	}

	private LineChartModel initGraficoTotalFiltro() {

		if (this.anoInicio.isEmpty() && this.anoFim.isEmpty()) {
			anoInicio = String.valueOf(anoAtual.get(Calendar.YEAR) - 3);
			anoFim = String.valueOf(anoAtual.get(Calendar.YEAR));
		}

		LineChartModel model = new LineChartModel();
		List<Aluno> alunosCadastro = new ArrayList<Aluno>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de alunos");

		alunosCadastro = this.alunoDao.listFiltro(this.anoInicio, this.anoFim);

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

		if (alunosCadastro.isEmpty() || alunosCadastro == null) {
			series1.set(" ", 0);
			series1.setLabel("Não foram encontrados resultados.");
		}

		model.addSeries(series1);

		return model;
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

	private LineChartModel initGraficoSemestreTotalFiltro() {

		if (this.anoInicio.isEmpty() && this.anoFim.isEmpty()) {
			anoInicio = String.valueOf(anoAtual.get(Calendar.YEAR) - 3);
			anoFim = String.valueOf(anoAtual.get(Calendar.YEAR));
		}

		LineChartModel model = new LineChartModel();
		List<Aluno> alunosCadastro = new ArrayList<Aluno>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;
		int mes = 0;
		int semestreUm = 0;
		int semestreDois = 0;
		double resultado = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de alunos");

		alunosCadastro = this.alunoDao.listFiltro(this.anoInicio, this.anoFim);

		for (Aluno aluno : alunosCadastro) {

			c.setTime(aluno.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			aluno.setDataCadastro(c.getTime());

			if (c.get(Calendar.YEAR) != ultimoAno) {
				semestreUm = 0;
				semestreDois = 0;
			}

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				mes = c.get((Calendar.MONTH)) + 1;
				resultado = (double) mes / 6;
				if (resultado <= 1) {
					semestreUm += getCadastroMes(alunosCadastro, c);
					series1.set("1°/" + c.get(Calendar.YEAR), semestreUm);
				} else {
					semestreDois += getCadastroMes(alunosCadastro, c);
					series1.set("2°/" + c.get(Calendar.YEAR), semestreDois);

				}
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}

		if (alunosCadastro.isEmpty() || alunosCadastro == null) {
			series1.set(" ", 0);
			series1.setLabel("Não foram encontrados resultados.");
		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoSemestreTotal() {
		LineChartModel model = new LineChartModel();
		List<Aluno> alunosCadastro = new ArrayList<Aluno>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;
		int mes = 0;
		int semestreUm = 0;
		int semestreDois = 0;
		double resultado = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de alunos");

		alunosCadastro = this.alunos;
		Collections.sort(alunosCadastro);

		for (Aluno aluno : alunosCadastro) {

			c.setTime(aluno.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			aluno.setDataCadastro(c.getTime());

			if (c.get(Calendar.YEAR) != ultimoAno) {
				semestreUm = 0;
				semestreDois = 0;
			}

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				mes = c.get((Calendar.MONTH)) + 1;
				resultado = (double) mes / 6;
				if (resultado <= 1) {
					semestreUm += getCadastroMes(alunosCadastro, c);
					series1.set("1°/" + c.get(Calendar.YEAR), semestreUm);
				} else {
					semestreDois += getCadastroMes(alunosCadastro, c);
					series1.set("2°/" + c.get(Calendar.YEAR), semestreDois);

				}
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoAnosFiltro() {

		if (this.anoInicio.isEmpty() && this.anoFim.isEmpty()) {
			anoInicio = String.valueOf(anoAtual.get(Calendar.YEAR) - 3);
			anoFim = String.valueOf(anoAtual.get(Calendar.YEAR));
		}

		LineChartModel model = new LineChartModel();
		List<Aluno> alunosCadastro = new ArrayList<Aluno>();

		Calendar c = Calendar.getInstance();

		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries meses = new ChartSeries();
		DateFormat df2 = new SimpleDateFormat("MMM", new Locale("pt", "BR"));

		meses.set("jan", 0);
		meses.set("fev", 0);
		meses.set("mar", 0);
		meses.set("abr", 0);
		meses.set("mai", 0);
		meses.set("jun", 0);
		meses.set("jul", 0);
		meses.set("ago", 0);
		meses.set("set", 0);
		meses.set("out", 0);
		meses.set("nov", 0);
		meses.set("dez", 0);

		alunosCadastro = this.alunoDao.listFiltro(this.anoInicio, this.anoFim);

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
				meses.set("jan", 0);
				meses.set("fev", 0);
				meses.set("mar", 0);
				meses.set("abr", 0);
				meses.set("mai", 0);
				meses.set("jun", 0);
				meses.set("jul", 0);
				meses.set("ago", 0);
				meses.set("set", 0);
				meses.set("out", 0);
				meses.set("nov", 0);
				meses.set("dez", 0);
				meses.set(df2.format(aluno.getDataCadastro()), getCadastroMes(alunosCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

			if (c.get(Calendar.MONTH) != ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {
				meses.set(df2.format(aluno.getDataCadastro()), getCadastroMes(alunosCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}
		if (alunosCadastro.isEmpty() || alunosCadastro == null) {
			meses = new ChartSeries();
			meses.set("jan", 0);
			meses.set("fev", 0);
			meses.set("mar", 0);
			meses.set("abr", 0);
			meses.set("mai", 0);
			meses.set("jun", 0);
			meses.set("jul", 0);
			meses.set("ago", 0);
			meses.set("set", 0);
			meses.set("out", 0);
			meses.set("nov", 0);
			meses.set("dez", 0);
			meses.setLabel("Não foram encontrados resultados.");
		} else {
			meses.setLabel(String.valueOf(c.get(Calendar.YEAR)));
		}
		model.addSeries(meses);
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

		meses.set("jan", 0);
		meses.set("fev", 0);
		meses.set("mar", 0);
		meses.set("abr", 0);
		meses.set("mai", 0);
		meses.set("jun", 0);
		meses.set("jul", 0);
		meses.set("ago", 0);
		meses.set("set", 0);
		meses.set("out", 0);
		meses.set("nov", 0);
		meses.set("dez", 0);

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
				meses.set("jan", 0);
				meses.set("fev", 0);
				meses.set("mar", 0);
				meses.set("abr", 0);
				meses.set("mai", 0);
				meses.set("jun", 0);
				meses.set("jul", 0);
				meses.set("ago", 0);
				meses.set("set", 0);
				meses.set("out", 0);
				meses.set("nov", 0);
				meses.set("dez", 0);
				meses.set(df2.format(aluno.getDataCadastro()), getCadastroMes(alunosCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
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

	private void inicializa() {
		this.alunos = new ArrayList<Aluno>();
		this.alunoDao = new AlunoDaoImpl();
		this.anoAtual = GregorianCalendar.getInstance();
		this.anoInicio = "";
		this.anoFim = "";
		this.anoTempInicio = "";
		this.anoTempFim = "";
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public LineChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(LineChartModel grafico) {
		this.grafico = grafico;
	}

	public String getGraficoFiltro() {
		return graficoFiltro;
	}

	public void setGraficoFiltro(String graficoFiltro) {
		this.graficoFiltro = graficoFiltro;
	}

	public AlunoDao getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public String getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(String anoInicio) {
		this.anoInicio = anoInicio;
	}

	public String getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(String anoFim) {
		this.anoFim = anoFim;
	}

	public Calendar getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(Calendar anoAtual) {
		this.anoAtual = anoAtual;
	}

}
