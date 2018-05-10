package br.ucb.MB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
			grafico = initGraficoAnos();
		} else if (getGraficoFiltro().equals("2")) {
			grafico = initGraficoTotal();
		} else if (getGraficoFiltro().equals("3")) {
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

}
