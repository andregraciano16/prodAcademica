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
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.ucb.dao.ProducaoAcademicaDao;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.entity.ProducaoAcademica;

@ManagedBean(name = "relatorioProducaoAcademicaMB")
@ViewScoped
public class RelatorioProducaoAcademicaMB extends BaseMB {

	private static final long serialVersionUID = 1L;
	private List<ProducaoAcademica> producoes;
	private LineChartModel grafico;
	private BarChartModel graficoBarra;
	private BarChartModel graficoBarraTipo;
	private String graficoFiltroLine = "1";
	private String graficoFiltroBarQualis = "1";
	private String graficoFiltroBarTipo = "1";
	private ProducaoAcademicaDao producaoAcademicaDao;
	private ProducaoAcademicaDaoImpl producaoAcademicaDaoImpl;
	private List<Object[]> listaSimples;

	@PostConstruct
	public void init() {
		inicializa();
		buscar();
		createGrafico();
	}

	public void selecionaFiltro() {
		init();
	}

	public void buscar() {
		this.producoes = this.producaoAcademicaDao.list();

	}

	private void createGrafico() {

		if (getGraficoFiltroLine().equals("1")) {
			grafico = initGraficoAnos();
			initLineChartModel();
		} else if (getGraficoFiltroLine().equals("2")) {
			grafico = initGraficoTotal();
			initLineChartModel();
		} else if (getGraficoFiltroLine().equals("3")) {
			grafico = initGraficoSemestreTotal();
			initLineChartModel();
		}

		if (getGraficoFiltroBarQualis().equals("1")) {
			graficoBarra = initGraficoQualisAnos();
			initBarChartModel();
		} else if (getGraficoFiltroBarQualis().equals("2")) {
			graficoBarra = initGraficoQualis();
			initBarChartModel();
		}

		if (getGraficoFiltroBarTipo().equals("1")) {
			graficoBarraTipo = initGraficoTipo();
			initBarChartModelTipo();
		} else if (getGraficoFiltroBarTipo().equals("2")) {
			graficoBarraTipo = initGraficoLinha();
			initBarChartModelTipo();
		}

	}

	private void initLineChartModel() {

		grafico.setTitle("Gráfico: Acompanhamento de produções");
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

	private void initBarChartModel() {

		graficoBarra.setTitle("Gráfico: Avaliações do Qualis");
		graficoBarra.setLegendPosition("w");
		graficoBarra.setShowPointLabels(true);
		graficoBarra.getAxes().put(AxisType.X, new CategoryAxis("Avaliações"));
		graficoBarra.setZoom(true);
		graficoBarra.setAnimate(true);
		graficoBarra.setResetAxesOnResize(true);
		graficoBarra.setShadow(true);

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setLabel("Qtd de produções");
		yAxis.setMin(0);

	}

	private void initBarChartModelTipo() {
		if (getGraficoFiltroBarTipo().equals("1")) {
			graficoBarra.setTitle("Gráfico: Quantidade de produções por tipo");
		} else if (getGraficoFiltroBarTipo().equals("2")) {
			graficoBarra.setTitle("Gráfico: Quantidade de produções por linha de pesquisa");
		} else if (getGraficoFiltroBarTipo().equals("3")) {
			graficoBarra.setTitle("Gráfico: Quantidade de produções por projeto");
		}
		graficoBarra.setLegendPosition("w");
		graficoBarra.setShowPointLabels(true);
		graficoBarra.getAxes().put(AxisType.X, new CategoryAxis("Tipos"));
		graficoBarra.setZoom(true);
		graficoBarra.setAnimate(true);
		graficoBarra.setResetAxesOnResize(true);
		graficoBarra.setShadow(true);
		graficoBarra.setExtender("removeLegend");

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setLabel("Qtd de produções");
		yAxis.setMin(0);

	}

	private LineChartModel initGraficoTotal() {
		LineChartModel model = new LineChartModel();
		List<ProducaoAcademica> producoesCadastro = new ArrayList<ProducaoAcademica>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de producoes");

		producoesCadastro = this.producoes;
		Collections.sort(producoesCadastro);

		for (ProducaoAcademica producaoAcademica : producoesCadastro) {

			c.setTime(producaoAcademica.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica.setDataCadastro(c.getTime());

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				series1.set(c.get(Calendar.MONTH) + 1 + "/" + c.get(Calendar.YEAR),
						getCadastroMes(producoesCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoSemestreTotal() {
		LineChartModel model = new LineChartModel();
		List<ProducaoAcademica> producoesCadastro = new ArrayList<ProducaoAcademica>();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;
		int mes = 0;
		int semestreUm = 0;
		int semestreDois = 0;
		double resultado = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de producoes");

		producoesCadastro = this.producoes;
		Collections.sort(producoesCadastro);

		for (ProducaoAcademica producaoAcademica : producoesCadastro) {

			c.setTime(producaoAcademica.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica.setDataCadastro(c.getTime());

			if (c.get(Calendar.YEAR) != ultimoAno) {
				semestreUm = 0;
				semestreDois = 0;
			}

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				mes = c.get((Calendar.MONTH)) + 1;
				resultado = (double) mes / 6;
				if (resultado <= 1) {
					semestreUm += getCadastroMes(producoesCadastro, c);
					series1.set("1°/" + c.get(Calendar.YEAR), semestreUm);
				} else {
					semestreDois += getCadastroMes(producoesCadastro, c);
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
		List<ProducaoAcademica> producoesCadastro = new ArrayList<ProducaoAcademica>();

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

		producoesCadastro = this.producoes;
		Collections.sort(producoesCadastro);

		for (ProducaoAcademica producaoAcademica : producoesCadastro) {

			c.setTime(producaoAcademica.getDataCadastro());
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica.setDataCadastro(c.getTime());

			if (ultimoMes == 0 && ultimoAno == 0) {

				meses.set(df2.format(producaoAcademica.getDataCadastro()), getCadastroMes(producoesCadastro, c));
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
				meses.set(df2.format(producaoAcademica.getDataCadastro()), getCadastroMes(producoesCadastro, c));
				ultimoMes = 0;
				ultimoAno = 0;
			}

			if (c.get(Calendar.MONTH) != ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {
				meses.set(df2.format(producaoAcademica.getDataCadastro()), getCadastroMes(producoesCadastro, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}
		meses.setLabel(String.valueOf(c.get(Calendar.YEAR)));
		model.addSeries(meses);
		return model;

	}

	private int getCadastroMes(List<ProducaoAcademica> producoesCadastro, Calendar data) {
		int qtdCadastro = 0;
		Calendar cadastro = Calendar.getInstance();

		for (ProducaoAcademica producaoAcademica : producoesCadastro) {
			cadastro.setTime(producaoAcademica.getDataCadastro());
			cadastro.set(Calendar.HOUR_OF_DAY, cadastro.get(Calendar.HOUR_OF_DAY) + 3);

			if (cadastro.get(Calendar.MONTH) == data.get(Calendar.MONTH)
					&& cadastro.get(Calendar.YEAR) == data.get(Calendar.YEAR)) {
				qtdCadastro++;
			}
		}

		return qtdCadastro;
	}

	private BarChartModel initGraficoTipo() {

		this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleTipo();
		BarChartModel model = new BarChartModel();

		ChartSeries tipos = new ChartSeries();
		String ultimo = "";
		for (Object[] producaoAcademica : listaSimples) {

			if (producaoAcademica[1].equals(ultimo)) {

			} else {
				tipos.set(producaoAcademica[1], getQuantidadeProducao(listaSimples, producaoAcademica[1]));
				ultimo = (String) producaoAcademica[1];
			}

		}
		model.addSeries(tipos);

		return model;

	}

	private int getQuantidadeProducao(List<Object[]> listSimples, Object producaoAcademica) {
		int qtdProducoes = 0;

		for (Object[] producaoI : listSimples) {
			if (producaoI[1].equals(producaoAcademica)) {
				qtdProducoes++;
			}
		}

		return qtdProducoes;
	}

	private BarChartModel initGraficoLinha() {

		this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleLinha();
		BarChartModel model = new BarChartModel();

		ChartSeries tipos = new ChartSeries();
		String ultimo = "";
		for (Object[] producaoAcademica : listaSimples) {

			if (producaoAcademica[1].equals(ultimo)) {

			} else {
				tipos.set(producaoAcademica[1], getQuantidadeProducao(listaSimples, producaoAcademica[1]));
				ultimo = (String) producaoAcademica[1];
			}

		}
		model.addSeries(tipos);

		return model;

	}

	private BarChartModel initGraficoQualis() {

		this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleQualis();
		BarChartModel model = new BarChartModel();
		ChartSeries qualis = new ChartSeries();
		String ultimaNota = "";
		int ultimoAno = 0;

		qualis.set("A1", -1);
		qualis.set("A2", -1);
		qualis.set("B1", -1);
		qualis.set("B2", -1);
		qualis.set("B3", -1);
		qualis.set("B4", -1);
		qualis.set("B5", -1);
		qualis.set("C", -1);

		// 0 - ano
		// 1 - conceito qualis
		// 2 - dataCadastro

		for (Object[] producaoAcademica : listaSimples) {

			if (ultimoAno == 0 && ultimaNota.isEmpty()) {
				qualis.set(producaoAcademica[1],
						getQuantidadeProducaoQualis(listaSimples, producaoAcademica[0], producaoAcademica[1]));
				ultimaNota = (String) producaoAcademica[1];
				ultimoAno = (Integer) producaoAcademica[0];
			}
			if ((Integer) producaoAcademica[0] != ultimoAno) {

				qualis.setLabel(String.valueOf(ultimoAno));
				model.addSeries(qualis);
				qualis = new ChartSeries();
				qualis.set("A1", -1);
				qualis.set("A2", -1);
				qualis.set("B1", -1);
				qualis.set("B2", -1);
				qualis.set("B3", -1);
				qualis.set("B4", -1);
				qualis.set("B5", -1);
				qualis.set("C", -1);
				qualis.set(producaoAcademica[1],
						getQuantidadeProducaoQualis(listaSimples, producaoAcademica[0], producaoAcademica[1]));
				ultimoAno = (Integer) producaoAcademica[0];
				ultimaNota = "";

			}

			if (!producaoAcademica[1].equals(ultimaNota) && (Integer) producaoAcademica[0] == ultimoAno) {
				qualis.set(producaoAcademica[1],
						getQuantidadeProducaoQualis(listaSimples, producaoAcademica[0], producaoAcademica[1]));
				ultimoAno = (Integer) producaoAcademica[0];
				ultimaNota = (String) producaoAcademica[1];
			}

		}

		qualis.setLabel(String.valueOf(ultimoAno));
		model.addSeries(qualis);

		return model;

	}

	private int getQuantidadeProducaoQualis(List<Object[]> listaSimplesL, Object ano, Object conceitoQualis) {
		int qtdCadastro = 0;

		for (Object[] atual : listaSimplesL) {
			if (atual[0].equals(ano) && atual[1].equals(conceitoQualis)) {
				qtdCadastro++;
			}
		}
		return qtdCadastro;

	}

	private BarChartModel initGraficoQualisAnos() {

		this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleQualis();
		BarChartModel model = new BarChartModel();
		ChartSeries qualis = new ChartSeries();
		String ultimaNota = "";
		int ultimoAno = 0;
		int contador = 0;
		Object[] producaoAcademica = listaSimples.get(0);
		int anoComeco = (Integer) producaoAcademica[0];
		producaoAcademica = listaSimples.get(listaSimples.size() - 1);
		int anoFim = (Integer) producaoAcademica[0];
		int proxAnoRevisao = 0;
		boolean resultadoAno = false;
		boolean salvou = false;
		boolean primeiro = true;

		qualis.set("A1", -1);
		qualis.set("A2", -1);
		qualis.set("B1", -1);
		qualis.set("B2", -1);
		qualis.set("B3", -1);
		qualis.set("B4", -1);
		qualis.set("B5", -1);
		qualis.set("C", -1);

		// 0 - ano
		// 1 - conceito qualis
		// 2 - dataCadastro

		producaoAcademica = listaSimples.get(0);
		ultimoAno = (Integer) producaoAcademica[0];
		ultimaNota = (String) producaoAcademica[1];
		proxAnoRevisao = ultimoAno + 4;

		for (int anoAtual = anoComeco; anoAtual <= anoFim; anoAtual++) {
			resultadoAno = temAno(listaSimples, anoAtual);
			if (resultadoAno) {
				salvou = false;

				while (ultimoAno == (Integer) producaoAcademica[0] || anoAtual == (Integer) producaoAcademica[0]) {
					if (contador < listaSimples.size()) {
						producaoAcademica = listaSimples.get(contador);
					} else {
						break;
					}
					if (ultimoAno != (Integer) producaoAcademica[0] && salvou
							|| (Integer) producaoAcademica[0] > proxAnoRevisao) {
						break;
					}

					if (primeiro) {
						qualis.set(producaoAcademica[1], getQuantidadeProducaoQualisAnos(listaSimples,
								producaoAcademica[0], producaoAcademica[1]));
						ultimaNota = (String) producaoAcademica[1];
						ultimoAno = (Integer) producaoAcademica[0];
						primeiro = false;
					}

					if ((Integer) producaoAcademica[0] != ultimoAno) {
						if (ultimoAno % 4 == 0 && temAno(listaSimples, ultimoAno)) {
							qualis.setLabel(String.valueOf(ultimoAno));
							model.addSeries(qualis);
							salvou = true;
							proxAnoRevisao = ultimoAno + 4;
							qualis = new ChartSeries();
							qualis.set("A1", -1);
							qualis.set("A2", -1);
							qualis.set("B1", -1);
							qualis.set("B2", -1);
							qualis.set("B3", -1);
							qualis.set("B4", -1);
							qualis.set("B5", -1);
							qualis.set("C", -1);
							qualis.set(producaoAcademica[1], getQuantidadeProducaoQualisAnos(listaSimples,
									producaoAcademica[0], producaoAcademica[1]));
							ultimoAno = (Integer) producaoAcademica[0];
							ultimaNota = (String) producaoAcademica[1];
						}

					}

					if (!producaoAcademica[1].equals(ultimaNota) && (Integer) producaoAcademica[0] == ultimoAno
							|| !producaoAcademica[1].equals(ultimaNota)
									&& (Integer) producaoAcademica[0] != ultimoAno) {
						ultimoAno = (Integer) producaoAcademica[0];
						ultimaNota = (String) producaoAcademica[1];
						qualis.set(ultimaNota, getQuantidadeProducaoQualisAnos(listaSimples, producaoAcademica[0],
								producaoAcademica[1]));

					}

					contador++;
				}
			} else if (anoAtual % 4 == 0 && !resultadoAno) {
				qualis.setLabel(String.valueOf(anoAtual));
				model.addSeries(qualis);
				qualis = new ChartSeries();
				qualis.set("A1", -1);
				qualis.set("A2", -1);
				qualis.set("B1", -1);
				qualis.set("B2", -1);
				qualis.set("B3", -1);
				qualis.set("B4", -1);
				qualis.set("B5", -1);
				qualis.set("C", -1);
				proxAnoRevisao = anoAtual + 4;
			}
		}

		qualis.setLabel(String.valueOf(ultimoAno));
		model.addSeries(qualis);

		return model;

	}

	private boolean temAno(List<Object[]> anos, int anoAtual) {
		for (Object[] producao : anos) {
			if ((Integer) producao[0] == anoAtual) {
				return true;
			}
		}
		return false;
	}

	private int getQuantidadeProducaoQualisAnos(List<Object[]> listaSimplesL, Object ano, Object conceitoQualis) {
		int qtdCadastro = 0;
		int restoAnos = (Integer) ano % 4;
		restoAnos = 4 - restoAnos;
		int periodo = 0;

		if (restoAnos == 4) {
			restoAnos = 3;
			ano = (Integer) ano - 3;
		}

		periodo = (Integer) ano + restoAnos;

		for (int i = (Integer) ano; i <= periodo; i++) {
			for (Object[] atual : listaSimplesL) {
				if (atual[0].equals(ano) && atual[1].equals(conceitoQualis)) {
					qtdCadastro++;
				}
				if ((Integer) ano < (Integer) atual[0]) {
					break;
				}
			}
			ano = (Integer) ano + 1;
		}
		return qtdCadastro;

	}

	private void inicializa() {
		this.producoes = new ArrayList<ProducaoAcademica>();
		this.producaoAcademicaDao = new ProducaoAcademicaDaoImpl();
		this.producaoAcademicaDaoImpl = new ProducaoAcademicaDaoImpl();
		this.listaSimples = new ArrayList<Object[]>();

	}

	public List<ProducaoAcademica> getProducoes() {
		return producoes;
	}

	public void setProducoes(List<ProducaoAcademica> producoes) {
		this.producoes = producoes;
	}

	public LineChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(LineChartModel grafico) {
		this.grafico = grafico;
	}

	public String getGraficoFiltroLine() {
		return graficoFiltroLine;
	}

	public void setGraficoFiltroLine(String graficoFiltroLine) {
		this.graficoFiltroLine = graficoFiltroLine;
	}

	public String getGraficoFiltroBarQualis() {
		return graficoFiltroBarQualis;
	}

	public void setGraficoFiltroBarQualis(String graficoFiltroBarQualis) {
		this.graficoFiltroBarQualis = graficoFiltroBarQualis;
	}

	public BarChartModel getGraficoBarra() {
		return graficoBarra;
	}

	public void setGraficoBarra(BarChartModel graficoBarra) {
		this.graficoBarra = graficoBarra;
	}

	public BarChartModel getGraficoBarraTipo() {
		return graficoBarraTipo;
	}

	public void setGraficoBarraTipo(BarChartModel graficoBarraTipo) {
		this.graficoBarraTipo = graficoBarraTipo;
	}

	public String getGraficoFiltroBarTipo() {
		return graficoFiltroBarTipo;
	}

	public void setGraficoFiltroBarTipo(String graficoFiltroBarTipo) {
		this.graficoFiltroBarTipo = graficoFiltroBarTipo;
	}

}
