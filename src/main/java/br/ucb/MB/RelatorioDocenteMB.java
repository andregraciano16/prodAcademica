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

import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.entity.Docente;

@ManagedBean(name = "relatorioDocenteMB")
@ViewScoped
public class RelatorioDocenteMB extends BaseMB{
	
	
	private static final long serialVersionUID = 1L;
	private List<Docente> docentes;
	private LineChartModel grafico;
	private String graficoFiltro = "1";
	private DocenteDao docenteDao;

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
		this.docentes = this.docenteDao.list();
	}

	private void createGrafico() {

		if (getGraficoFiltro().equals("1")) {
			grafico = initGraficoAnos();
		} else if (getGraficoFiltro().equals("2")) {
			grafico = initGraficoTotal();
		} else if (getGraficoFiltro().equals("3")) {
			grafico = initGraficoSemestreTotal();
		}

		grafico.setTitle("Gráfico: Acompanhamento de docentes");
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
		List<Docente> docentesCadastro = new ArrayList<Docente>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de docentes");

		docentesCadastro = this.docentes;
		Collections.sort(docentesCadastro);

		for (Docente docente : docentesCadastro) {

			c.setTime(docente.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			docente.setDataCadastro(c.getTime());

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				series1.set(c.get(Calendar.MONTH) + 1 + "/" + c.get(Calendar.YEAR), getCadastroMes(docentesCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoSemestreTotal() {
		LineChartModel model = new LineChartModel();
		List<Docente> docentesCadastro = new ArrayList<Docente>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;
		int mes = 0;
		int semestreUm = 0;
		int semestreDois = 0;
		double resultado = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de docentes");

		docentesCadastro = this.docentes;
		Collections.sort(docentesCadastro);

		for (Docente docente : docentesCadastro) {

			c.setTime(docente.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			docente.setDataCadastro(c.getTime());
			
			if (c.get(Calendar.YEAR) != ultimoAno) {
				semestreUm = 0;
				semestreDois = 0;
			}

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				mes = c.get((Calendar.MONTH)) + 1;
				resultado = (double) mes / 6;
				if (resultado <= 1) {
					semestreUm += getCadastroMes(docentesCadastro, c);
					series1.set("1°/" + c.get(Calendar.YEAR), semestreUm);
				} else {
					semestreDois += getCadastroMes(docentesCadastro, c);
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
		List<Docente> docentesCadastro = new ArrayList<Docente>();

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

		docentesCadastro = this.docentes;
		Collections.sort(docentesCadastro);

		for (Docente docente : docentesCadastro) {

			c.setTime(docente.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			docente.setDataCadastro(c.getTime());

			if (ultimoMes == 0 && ultimoAno == 0) {

				meses.set(df2.format(docente.getDataCadastro()), getCadastroMes(docentesCadastro, c));
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
				meses.set(df2.format(docente.getDataCadastro()), getCadastroMes(docentesCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

			if (c.get(Calendar.MONTH) != ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {
				meses.set(df2.format(docente.getDataCadastro()), getCadastroMes(docentesCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}
		meses.setLabel(String.valueOf(c.get(Calendar.YEAR)));
		model.addSeries(meses);
		return model;

	}

	private int getCadastroMes(List<Docente> docentesCadastro, Calendar data) {
		int qtdCadastro = 0;
		Calendar cadastro = Calendar.getInstance();

		for (Docente docente : docentesCadastro) {
			cadastro.setTime(docente.getDataCadastro());
			cadastro.set(Calendar.HOUR_OF_DAY, cadastro.get(Calendar.HOUR_OF_DAY) + 3);

			if (cadastro.get(Calendar.MONTH) == data.get(Calendar.MONTH)
					&& cadastro.get(Calendar.YEAR) == data.get(Calendar.YEAR)) {
				qtdCadastro++;
			}
		}

		return qtdCadastro;
	}

	
	private void inicializa() {
		this.docentes = new ArrayList<Docente>();
		this.docenteDao = new DocenteDaoImpl();	

	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
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
