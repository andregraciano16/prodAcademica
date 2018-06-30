package br.ucb.MB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import org.springframework.security.core.GrantedAuthority;

import br.ucb.dao.AlunoDao;
import br.ucb.dao.DocenteDao;
import br.ucb.dao.impl.AlunoDaoImpl;
import br.ucb.dao.impl.DocenteDaoImpl;
import br.ucb.dao.impl.ProducaoAcademicaDaoImpl;
import br.ucb.security.Seguranca;
import br.ucb.security.UsuarioSistema;

@ManagedBean(name = "relatorioProducaoAcademicaMB")
@ViewScoped
public class RelatorioProducaoAcademicaMB extends BaseMB {

	private static final long serialVersionUID = 1L;
	private LineChartModel grafico;
	private LineChartModel graficoMeu;
	private BarChartModel graficoBarra;
	private BarChartModel graficoBarraTipo;
	private BarChartModel graficoBarraMeu;
	private String graficoFiltroLine = "1";
	private String graficoFiltroBarQualis = "1";
	private String graficoFiltroBarTipo = "1";
	private String graficoFiltroBarQualisMeu = "1";
	private String graficoFiltroLineMeu = "1";
	private ProducaoAcademicaDaoImpl producaoAcademicaDaoImpl;
	private List<Object[]> listaSimples;
	private List<Object[]> listaSimplesMeu;
	private List<Date> listaDatas;
	private List<Date> listaDatasMeu;
	private String anoInicioProd;
	private String anoFimProd;
	private String anoTempInicioProd;
	private String anoTempFimProd;
	private String anoInicioMeuProd;
	private String anoFimMeuProd;
	private String anoTempInicioMeuProd;
	private String anoTempFimMeuProd;
	private String anoInicioQualis;
	private String anoFimQualis;
	private String anoTempInicioQualis;
	private String anoTempFimQualis;
	private String anoInicioMeuQualis;
	private String anoFimMeuQualis;
	private String anoTempInicioMeuQualis;
	private String anoTempFimMeuQualis;
	private Calendar anoAtual;
	private UsuarioSistema user;
	private DocenteDao docenteDao;
	private AlunoDao alunoDao;

	@PostConstruct
	public void init() {
		inicializa();
		createGrafico();
	}

	public void selecionaFiltro() {
		createGrafico();
	}

	private void createGrafico() {

		createGraficoLine();
		createGraficoBarQualis();
		createGraficoBarTipo();
		createGraficoBarMeuQualis();
		createGraficoMeuLine();

	}

	private void createGraficoLine() {

		initIntervalo(this.anoTempInicioProd, this.anoTempFimProd, this.anoInicioProd, this.anoFimProd, 1);

		if (getGraficoFiltroLine().equals("1")) {
			this.listaDatas = this.producaoAcademicaDaoImpl.listSimpleProdFiltro(this.anoInicioProd, this.anoFimProd);
			this.grafico = initGraficoAnosFiltro(this.listaDatas);
		} else if (getGraficoFiltroLine().equals("2")) {
			this.listaDatas = this.producaoAcademicaDaoImpl.listSimpleProdFiltro(this.anoInicioProd, this.anoFimProd);
			this.grafico = initGraficoTotalFiltro(this.listaDatas);
		} else if (getGraficoFiltroLine().equals("3")) {
			this.listaDatas = this.producaoAcademicaDaoImpl.listSimpleProdFiltro(this.anoInicioProd, this.anoFimProd);
			this.grafico = initGraficoSemestreTotalFiltro(this.listaDatas);
		} else if (getGraficoFiltroLine().equals("4")) {
			this.listaDatas = this.producaoAcademicaDaoImpl.listSimpleDatas();
			this.grafico = initGraficoAnos(this.listaDatas);
		} else if (getGraficoFiltroLine().equals("5")) {
			this.listaDatas = this.producaoAcademicaDaoImpl.listSimpleDatas();
			this.grafico = initGraficoTotal(this.listaDatas);
		} else if (getGraficoFiltroLine().equals("6")) {
			this.listaDatas = this.producaoAcademicaDaoImpl.listSimpleDatas();
			this.grafico = initGraficoSemestreTotal(this.listaDatas);
		}

		initLineChartModel();

	}

	private void createGraficoMeuLine() {

		initIntervalo(this.anoTempInicioMeuProd, this.anoTempFimMeuProd, this.anoInicioMeuProd, this.anoFimMeuProd, 4);

		if (getGraficoFiltroLineMeu().equals("1")) {

			if (!isAluno()) {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl.listSimpleProdFiltroMeu(this.anoInicioProd,
						this.anoFimProd, this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl.listSimpleProdFiltroMeu(this.anoInicioProd,
						this.anoFimProd, this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoMeu = initGraficoAnosFiltro(this.listaDatasMeu);

		} else if (getGraficoFiltroLineMeu().equals("2")) {

			if (!isAluno()) {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl.listSimpleProdFiltroMeu(this.anoInicioProd,
						this.anoFimProd, this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl.listSimpleProdFiltroMeu(this.anoInicioProd,
						this.anoFimProd, this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoMeu = initGraficoTotalFiltro(this.listaDatasMeu);

		} else if (getGraficoFiltroLineMeu().equals("3")) {

			if (!isAluno()) {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl.listSimpleProdFiltroMeu(this.anoInicioProd,
						this.anoFimProd, this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl.listSimpleProdFiltroMeu(this.anoInicioProd,
						this.anoFimProd, this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoMeu = initGraficoSemestreTotalFiltro(this.listaDatasMeu);

		} else if (getGraficoFiltroLineMeu().equals("4")) {

			if (!isAluno()) {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl
						.listSimplesProdMeu(this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl
						.listSimplesProdMeu(this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoMeu = initGraficoAnos(this.listaDatasMeu);

		} else if (getGraficoFiltroLineMeu().equals("5")) {

			if (!isAluno()) {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl
						.listSimplesProdMeu(this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl
						.listSimplesProdMeu(this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoMeu = initGraficoTotal(this.listaDatasMeu);

		} else if (getGraficoFiltroLineMeu().equals("6")) {

			if (!isAluno()) {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl
						.listSimplesProdMeu(this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaDatasMeu = this.producaoAcademicaDaoImpl
						.listSimplesProdMeu(this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoMeu = initGraficoSemestreTotal(this.listaDatasMeu);

		}

		initLineChartModelMeu();

	}

	private void createGraficoBarQualis() {

		initIntervalo(this.anoTempInicioQualis, this.anoTempFimQualis, this.anoInicioQualis, this.anoFimQualis, 2);

		if (getGraficoFiltroBarQualis().equals("1")) {
			this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleQualisFiltro(this.anoInicioQualis,
					this.anoFimQualis);
			this.graficoBarra = initGraficoQualisFiltro(this.listaSimples);

		} else if (getGraficoFiltroBarQualis().equals("2")) {
			this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleQualisFiltro(this.anoInicioQualis,
					this.anoFimQualis);
			this.graficoBarra = initGraficoQualisTotalFiltro(this.listaSimples);

		} else if (getGraficoFiltroBarQualis().equals("3")) {
			this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleQualis();
			this.graficoBarra = initGraficoQualisAnos(this.listaSimples);

		} else if (getGraficoFiltroBarQualis().equals("4")) {
			this.listaSimples = this.producaoAcademicaDaoImpl.listSimpleQualis();
			this.graficoBarra = initGraficoQualis(this.listaSimples);
		}

		initBarChartModel();

	}

	private void createGraficoBarMeuQualis() {

		initIntervalo(this.anoTempInicioMeuQualis, this.anoTempFimMeuQualis, this.anoInicioMeuQualis,
				this.anoFimMeuQualis, 3);

		if (getGraficoFiltroBarQualisMeu().equals("1")) {
			if (!isAluno()) {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl.listSimpleQualisFiltroMeu(this.anoInicioQualis,
						this.anoFimQualis, this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl.listSimpleQualisFiltroMeu(this.anoInicioQualis,
						this.anoFimQualis, this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoBarraMeu = initGraficoQualisFiltro(this.listaSimplesMeu);

		} else if (getGraficoFiltroBarQualisMeu().equals("2")) {
			if (!isAluno()) {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl.listSimpleQualisFiltroMeu(this.anoInicioQualis,
						this.anoFimQualis, this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl.listSimpleQualisFiltroMeu(this.anoInicioQualis,
						this.anoFimQualis, this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoBarraMeu = initGraficoQualisTotalFiltro(this.listaSimplesMeu);

		} else if (getGraficoFiltroBarQualisMeu().equals("3")) {
			if (!isAluno()) {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl
						.listSimpleQualisMeu(this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl
						.listSimpleQualisMeu(this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoBarraMeu = initGraficoQualisAnos(this.listaSimplesMeu);

		} else if (getGraficoFiltroBarQualisMeu().equals("4")) {
			if (!isAluno()) {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl
						.listSimpleQualisMeu(this.docenteDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			} else {
				this.listaSimplesMeu = this.producaoAcademicaDaoImpl
						.listSimpleQualisMeu(this.alunoDao.getIdbyMatricula(user.getUsuario().getMatricula()));
			}
			this.graficoBarraMeu = initGraficoQualis(this.listaSimplesMeu);
		}

		initBarChartModelMeu();

	}

	private void initIntervalo(String anoTempInicio, String anoTempFim, String anoInicio, String anoFim,
			Integer valor) {

		if (anoInicio.equals("") || anoInicio == null)
			anoInicio = anoTempInicio;
		if (anoFim.equals("") || anoFim == null)
			anoFim = anoTempFim;

		if (anoInicio.isEmpty() && anoFim.isEmpty()) {
			anoInicio = String.valueOf(anoAtual.get(Calendar.YEAR) - 3);
			anoFim = String.valueOf(anoAtual.get(Calendar.YEAR));
		}

		if (valor == 1) {
			this.anoTempInicioProd = anoTempInicio;
			this.anoTempFimProd = anoTempFim;
			this.anoInicioProd = anoInicio;
			this.anoFimProd = anoFim;
			verificaGraficoFiltro(anoTempInicio, anoTempFim, anoInicio, anoFim, 1);
		} else if (valor == 2) {
			this.anoTempInicioQualis = anoTempInicio;
			this.anoTempFimQualis = anoTempFim;
			this.anoInicioQualis = anoInicio;
			this.anoFimQualis = anoFim;
			verificaGraficoFiltro(anoTempInicio, anoTempFim, anoInicio, anoFim, 2);
		} else if (valor == 3) {
			this.anoTempInicioMeuQualis = anoTempInicio;
			this.anoTempFimMeuQualis = anoTempFim;
			this.anoInicioMeuQualis = anoInicio;
			this.anoFimMeuQualis = anoFim;
			verificaGraficoFiltro(anoTempInicio, anoTempFim, anoInicio, anoFim, 3);
		} else if (valor == 4) {
			this.anoTempInicioMeuProd = anoTempInicio;
			this.anoTempFimMeuProd = anoTempFim;
			this.anoInicioMeuProd = anoInicio;
			this.anoFimMeuProd = anoFim;
			verificaGraficoFiltro(anoTempInicio, anoTempFim, anoInicio, anoFim, 4);
		}
	}

	private void createGraficoBarTipo() {

		if (getGraficoFiltroBarTipo().equals("1")) {
			graficoBarraTipo = initGraficoTipo();
			initBarChartModelTipo();
		} else if (getGraficoFiltroBarTipo().equals("2")) {
			graficoBarraTipo = initGraficoLinha();
			initBarChartModelTipo();
		}
	}

	private void verificaGraficoFiltro(String anoTempInicio, String anoTempFim, String anoInicio, String anoFim,
			Integer valor) {

		if (anoTempInicio.isEmpty() || anoTempFim.isEmpty()) {
			anoTempFim = String.valueOf(anoAtual.get(Calendar.YEAR));
			anoTempInicio = String.valueOf(anoAtual.get(Calendar.YEAR) - 3);
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

		if (!anoInicio.isEmpty())
			anoTempInicio = anoInicio;
		if (!anoFim.isEmpty())
			anoTempFim = anoFim;

		if (valor == 1) {
			this.anoTempInicioProd = anoTempInicio;
			this.anoTempFimProd = anoTempFim;
			this.anoInicioProd = anoInicio;
			this.anoFimProd = anoFim;
		} else if (valor == 2) {
			this.anoTempInicioQualis = anoTempInicio;
			this.anoTempFimQualis = anoTempFim;
			this.anoInicioQualis = anoInicio;
			this.anoFimQualis = anoFim;
		} else if (valor == 3) {
			this.anoTempInicioMeuQualis = anoTempInicio;
			this.anoTempFimMeuQualis = anoTempFim;
			this.anoInicioMeuQualis = anoInicio;
			this.anoFimMeuQualis = anoFim;
		} else if (valor == 4) {
			this.anoTempInicioMeuProd = anoTempInicio;
			this.anoTempFimMeuProd = anoTempFim;
			this.anoInicioMeuProd = anoInicio;
			this.anoFimMeuProd = anoFim;
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
		yAxis.setTickFormat("%.0f");
		yAxis.setTickInterval("1");
		yAxis.setMin(0);

	}

	private void initLineChartModelMeu() {

		graficoMeu.setTitle("Gráfico: Acompanhamento de produções");
		graficoMeu.setLegendPosition("w");
		graficoMeu.setShowPointLabels(true);
		graficoMeu.getAxes().put(AxisType.X, new CategoryAxis("Tempo"));
		graficoMeu.setZoom(true);
		graficoMeu.setAnimate(true);
		graficoMeu.setResetAxesOnResize(true);
		graficoMeu.setShadow(true);

		Axis yAxis = graficoMeu.getAxis(AxisType.Y);
		yAxis.setLabel("n° de cadastros");
		yAxis.setTickFormat("%.0f");
		yAxis.setTickInterval("1");
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
		yAxis.setTickFormat("%.0f");
		yAxis.setTickInterval("1");
		yAxis.setLabel("Qtd de produções");
		yAxis.setMin(0);

	}

	private void initBarChartModelMeu() {

		graficoBarraMeu.setTitle("Gráfico: Avaliações do Qualis");
		graficoBarraMeu.setLegendPosition("w");
		graficoBarraMeu.setShowPointLabels(true);
		graficoBarraMeu.getAxes().put(AxisType.X, new CategoryAxis("Avaliações"));
		graficoBarraMeu.setZoom(true);
		graficoBarraMeu.setAnimate(true);
		graficoBarraMeu.setResetAxesOnResize(true);
		graficoBarraMeu.setShadow(true);

		Axis yAxis = graficoBarraMeu.getAxis(AxisType.Y);
		yAxis.setTickFormat("%.0f");
		yAxis.setTickInterval("1");
		yAxis.setLabel("Qtd de produções");
		yAxis.setMin(0);

	}

	private void initBarChartModelTipo() {
		if (getGraficoFiltroBarTipo().equals("1")) {
			graficoBarraTipo.setTitle("Gráfico: Quantidade de produções por tipo");
		} else if (getGraficoFiltroBarTipo().equals("2")) {
			graficoBarraTipo.setTitle("Gráfico: Quantidade de produções por linha de pesquisa");
		}

		graficoBarraTipo.setLegendPosition("w");
		graficoBarraTipo.setShowPointLabels(true);
		graficoBarraTipo.getAxes().put(AxisType.X, new CategoryAxis("Tipos"));
		graficoBarraTipo.setZoom(true);
		graficoBarraTipo.setAnimate(true);
		graficoBarraTipo.setResetAxesOnResize(true);
		graficoBarraTipo.setShadow(true);
		graficoBarraTipo.setExtender("removeLegend");

		Axis yAxis = graficoBarra.getAxis(AxisType.Y);
		yAxis.setTickFormat("%.0f");
		yAxis.setTickInterval("1");
		yAxis.setLabel("Qtd de produções");
		yAxis.setMin(0);

	}

	private LineChartModel initGraficoTotalFiltro(List<Date> listaDatas) {

		// producaoAcademica => dataCadastro das produções

		LineChartModel model = new LineChartModel();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de producoes");

		for (Date producaoAcademica : listaDatas) {

			c.setTime(producaoAcademica);
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica = c.getTime();

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				series1.set(c.get(Calendar.MONTH) + 1 + "/" + c.get(Calendar.YEAR), getCadastroMes(listaDatas, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

		}
		if (listaDatas.isEmpty() || listaDatas == null) {
			series1.set(" ", 0);
			series1.setLabel("Não foram encontrados resultados.");
		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoTotal(List<Date> listaDatas) {

		// producaoAcademica => dataCadastro das produções

		LineChartModel model = new LineChartModel();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de producoes");

		for (Date producaoAcademica : listaDatas) {

			c.setTime(producaoAcademica);
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica = c.getTime();

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				series1.set(c.get(Calendar.MONTH) + 1 + "/" + c.get(Calendar.YEAR), getCadastroMes(listaDatas, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoSemestreTotalFiltro(List<Date> listaDatas) {

		LineChartModel model = new LineChartModel();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;
		int mes = 0;
		int semestreUm = 0;
		int semestreDois = 0;
		double resultado = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de producoes");

		for (Date producaoAcademica : listaDatas) {

			c.setTime(producaoAcademica);
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica = c.getTime();

			if (c.get(Calendar.YEAR) != ultimoAno) {
				semestreUm = 0;
				semestreDois = 0;
			}

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				mes = c.get((Calendar.MONTH)) + 1;
				resultado = (double) mes / 6;
				if (resultado <= 1) {
					semestreUm += getCadastroMes(listaDatas, c);
					series1.set("1°/" + c.get(Calendar.YEAR), semestreUm);
				} else {
					semestreDois += getCadastroMes(listaDatas, c);
					series1.set("2°/" + c.get(Calendar.YEAR), semestreDois);

				}
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}

		if (listaDatas.isEmpty() || listaDatas == null) {
			series1.set(" ", 0);
			series1.setLabel("Não foram encontrados resultados.");
		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoSemestreTotal(List<Date> listaDatas) {
		LineChartModel model = new LineChartModel();

		Calendar c = Calendar.getInstance();
		int ultimoMes = 0;
		int ultimoAno = 0;
		int mes = 0;
		int semestreUm = 0;
		int semestreDois = 0;
		double resultado = 0;

		ChartSeries series1 = new ChartSeries();
		series1.setLabel("N° de producoes");

		for (Date producaoAcademica : listaDatas) {

			c.setTime(producaoAcademica);
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica = c.getTime();

			if (c.get(Calendar.YEAR) != ultimoAno) {
				semestreUm = 0;
				semestreDois = 0;
			}

			if (c.get(Calendar.MONTH) == ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {

			} else {
				mes = c.get((Calendar.MONTH)) + 1;
				resultado = (double) mes / 6;
				if (resultado <= 1) {
					semestreUm += getCadastroMes(listaDatas, c);
					series1.set("1°/" + c.get(Calendar.YEAR), semestreUm);
				} else {
					semestreDois += getCadastroMes(listaDatas, c);
					series1.set("2°/" + c.get(Calendar.YEAR), semestreDois);

				}
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}

		model.addSeries(series1);

		return model;
	}

	private LineChartModel initGraficoAnosFiltro(List<Date> listaDatas) {

		LineChartModel model = new LineChartModel();

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

		for (Date producaoAcademica : listaDatas) {

			c.setTime(producaoAcademica);
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica = (Date) c.getTime();

			if (ultimoMes == 0 && ultimoAno == 0) {

				meses.set(df2.format(producaoAcademica), getCadastroMes(listaDatas, c));
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
				meses.set(df2.format(producaoAcademica), getCadastroMes(listaDatas, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

			if (c.get(Calendar.MONTH) != ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {
				meses.set(df2.format(producaoAcademica), getCadastroMes(listaDatas, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}

		if (listaDatas.isEmpty() || listaDatas == null) {
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

	private LineChartModel initGraficoAnos(List<Date> listaDatas) {
		LineChartModel model = new LineChartModel();

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

		for (Date producaoAcademica : listaDatas) {

			c.setTime(producaoAcademica);
			c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + 3);
			producaoAcademica = (Date) c.getTime();

			if (ultimoMes == 0 && ultimoAno == 0) {

				meses.set(df2.format(producaoAcademica), getCadastroMes(listaDatas, c));
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
				meses.set(df2.format(producaoAcademica), getCadastroMes(listaDatas, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);
			}

			if (c.get(Calendar.MONTH) != ultimoMes && c.get(Calendar.YEAR) == ultimoAno) {
				meses.set(df2.format(producaoAcademica), getCadastroMes(listaDatas, c));
				ultimoMes = c.get(Calendar.MONTH);
				ultimoAno = c.get(Calendar.YEAR);

			}

		}
		meses.setLabel(String.valueOf(c.get(Calendar.YEAR)));
		model.addSeries(meses);
		return model;

	}

	private int getCadastroMes(List<Date> listaDatasD, Calendar data) {
		int qtdCadastro = 0;
		Calendar cadastro = Calendar.getInstance();

		for (Date producaoAcademica : listaDatasD) {
			cadastro.setTime((Date) producaoAcademica);
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
		for (Object[] producaoAcademica : this.listaSimples) {

			if (producaoAcademica[1].equals(ultimo)) {

			} else {
				tipos.set(producaoAcademica[1], getQuantidadeProducao(this.listaSimples, producaoAcademica[1]));
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
		for (Object[] producaoAcademica : this.listaSimples) {

			if (producaoAcademica[1].equals(ultimo)) {

			} else {
				tipos.set(producaoAcademica[1], getQuantidadeProducao(this.listaSimples, producaoAcademica[1]));
				ultimo = (String) producaoAcademica[1];
			}

		}
		model.addSeries(tipos);

		return model;

	}

	private BarChartModel initGraficoQualisTotalFiltro(List<Object[]> listaSimples) {

		BarChartModel model = new BarChartModel();
		ChartSeries qualis = new ChartSeries();
		String ultimaNota = "";
		int ultimoAno = 0;

		qualis.set("A1", 0);
		qualis.set("A2", 0);
		qualis.set("B1", 0);
		qualis.set("B2", 0);
		qualis.set("B3", 0);
		qualis.set("B4", 0);
		qualis.set("B5", 0);
		qualis.set("C", 0);
		qualis.set("Sem conceito", 0);

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
				qualis.set("A1", 0);
				qualis.set("A2", 0);
				qualis.set("B1", 0);
				qualis.set("B2", 0);
				qualis.set("B3", 0);
				qualis.set("B4", 0);
				qualis.set("B5", 0);
				qualis.set("C", 0);
				qualis.set("Sem conceito", 0);
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

		if (listaSimples.isEmpty() || listaSimples == null) {
			qualis = new ChartSeries();
			qualis.set("A1", 0);
			qualis.set("A2", 0);
			qualis.set("B1", 0);
			qualis.set("B2", 0);
			qualis.set("B3", 0);
			qualis.set("B4", 0);
			qualis.set("B5", 0);
			qualis.set("C", 0);
			qualis.set("Sem conceito", 0);

			qualis.setLabel("Não foram encontrados resultados");
		} else {
			qualis.setLabel(String.valueOf(ultimoAno));
		}

		model.addSeries(qualis);
		return model;

	}

	private BarChartModel initGraficoQualis(List<Object[]> listaSimples) {

		BarChartModel model = new BarChartModel();
		ChartSeries qualis = new ChartSeries();
		String ultimaNota = "";
		int ultimoAno = 0;

		qualis.set("A1", 0);
		qualis.set("A2", 0);
		qualis.set("B1", 0);
		qualis.set("B2", 0);
		qualis.set("B3", 0);
		qualis.set("B4", 0);
		qualis.set("B5", 0);
		qualis.set("C", 0);
		qualis.set("Sem conceito", 0);

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
				qualis.set("A1", 0);
				qualis.set("A2", 0);
				qualis.set("B1", 0);
				qualis.set("B2", 0);
				qualis.set("B3", 0);
				qualis.set("B4", 0);
				qualis.set("B5", 0);
				qualis.set("C", 0);
				qualis.set("Sem conceito", 0);
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

	private BarChartModel initGraficoQualisFiltro(List<Object[]> listaSimples) {

		BarChartModel model = new BarChartModel();
		ChartSeries qualis = new ChartSeries();
		String ultimaNota = "";
		int ultimoAno = 0;
		int contador = 0;
		int anoAtual = 0;
		if (!listaSimples.isEmpty() && listaSimples != null) {
			Object[] producaoAcademica = listaSimples.get(0);
			int anoComeco = (Integer) producaoAcademica[0];
			producaoAcademica = listaSimples.get(listaSimples.size() - 1);
			int anoFim = (Integer) producaoAcademica[0];
			boolean resultadoAno = false;
			boolean salvou = false;
			boolean primeiro = true;
			qualis.set("A1", 0);
			qualis.set("A2", 0);
			qualis.set("B1", 0);
			qualis.set("B2", 0);
			qualis.set("B3", 0);
			qualis.set("B4", 0);
			qualis.set("B5", 0);
			qualis.set("C", 0);
			qualis.set("Sem conceito", 0);
			
			producaoAcademica = listaSimples.get(0);
			ultimoAno = (Integer) producaoAcademica[0];
			ultimaNota = (String) producaoAcademica[1];

			for (anoAtual = anoComeco; anoAtual <= anoFim; anoAtual++) {
				resultadoAno = temAno(listaSimples, anoAtual);

				if (resultadoAno) {
					salvou = false;

					while (ultimoAno == (Integer) producaoAcademica[0] || anoAtual == (Integer) producaoAcademica[0]) {
						if (contador < listaSimples.size()) {
							ultimaNota = (String) producaoAcademica[1];
							ultimoAno = (Integer) producaoAcademica[0];
							producaoAcademica = listaSimples.get(contador);
						} else {
							break;
						}
						if (ultimoAno != (Integer) producaoAcademica[0] && salvou) {
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
								qualis.setLabel(String.valueOf(ultimoAno - 3) + " - " + String.valueOf(ultimoAno));
								model.addSeries(qualis);
								salvou = true;
								qualis = new ChartSeries();
								qualis.set("A1", 0);
								qualis.set("A2", 0);
								qualis.set("B1", 0);
								qualis.set("B2", 0);
								qualis.set("B3", 0);
								qualis.set("B4", 0);
								qualis.set("B5", 0);
								qualis.set("C", 0);
								qualis.set("Sem conceito", 0);
								
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
					qualis.setLabel(String.valueOf(anoAtual - 3) + " - " + String.valueOf(anoAtual));
					model.addSeries(qualis);
					qualis = new ChartSeries();
					qualis.set("A1", 0);
					qualis.set("A2", 0);
					qualis.set("B1", 0);
					qualis.set("B2", 0);
					qualis.set("B3", 0);
					qualis.set("B4", 0);
					qualis.set("B5", 0);
					qualis.set("C", 0);
					qualis.set("Sem conceito", 0);
				}
			}
		}
		if ((anoAtual - 1) % 4 == 0) {
			qualis.setLabel(String.valueOf(anoAtual - 4) + " - " + String.valueOf(anoAtual - 1));
		} else {
			qualis.setLabel(String.valueOf(anoAtual - 1));
		}
		if (listaSimples.isEmpty() || listaSimples == null) {
			qualis = new ChartSeries();
			qualis.set("A1", 0);
			qualis.set("A2", 0);
			qualis.set("B1", 0);
			qualis.set("B2", 0);
			qualis.set("B3", 0);
			qualis.set("B4", 0);
			qualis.set("B5", 0);
			qualis.set("C", 0);
			qualis.set("Sem conceito", 0);
			qualis.setLabel("Não foram encontrados resultados");
		}

		model.addSeries(qualis);

		return model;

	}

	public ProducaoAcademicaDaoImpl getProducaoAcademicaDaoImpl() {
		return producaoAcademicaDaoImpl;
	}

	public void setProducaoAcademicaDaoImpl(ProducaoAcademicaDaoImpl producaoAcademicaDaoImpl) {
		this.producaoAcademicaDaoImpl = producaoAcademicaDaoImpl;
	}

	public List<Date> getListaDatas() {
		return listaDatas;
	}

	public void setListaDatas(List<Date> listaDatas) {
		this.listaDatas = listaDatas;
	}

	private BarChartModel initGraficoQualisAnos(List<Object[]> listaSimples) {

		BarChartModel model = new BarChartModel();
		ChartSeries qualis = new ChartSeries();
		String ultimaNota = "";
		int ultimoAno = 0;
		int contador = 0;
		int anoAtual = 0;
		if (!this.listaSimples.isEmpty() && this.listaSimples != null) {
			Object[] producaoAcademica = this.listaSimples.get(0);
			int anoComeco = (Integer) producaoAcademica[0];
			producaoAcademica = this.listaSimples.get(this.listaSimples.size() - 1);
			int anoFim = (Integer) producaoAcademica[0];
			boolean resultadoAno = false;
			boolean salvou = false;
			boolean primeiro = true;
			qualis.set("A1", 0);
			qualis.set("A2", 0);
			qualis.set("B1", 0);
			qualis.set("B2", 0);
			qualis.set("B3", 0);
			qualis.set("B4", 0);
			qualis.set("B5", 0);
			qualis.set("C", 0);
			qualis.set("Sem conceito", 0);

			producaoAcademica = this.listaSimples.get(0);
			ultimoAno = (Integer) producaoAcademica[0];
			ultimaNota = (String) producaoAcademica[1];

			for (anoAtual = anoComeco; anoAtual <= anoFim; anoAtual++) {
				resultadoAno = temAno(this.listaSimples, anoAtual);

				if (resultadoAno) {
					salvou = false;

					while (ultimoAno == (Integer) producaoAcademica[0] || anoAtual == (Integer) producaoAcademica[0]) {
						if (contador < this.listaSimples.size()) {
							ultimaNota = (String) producaoAcademica[1];
							ultimoAno = (Integer) producaoAcademica[0];
							producaoAcademica = this.listaSimples.get(contador);
						} else {
							break;
						}
						if (ultimoAno != (Integer) producaoAcademica[0] && salvou) {
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
							if (ultimoAno % 4 == 0 && temAno(this.listaSimples, ultimoAno)) {
								qualis.setLabel(String.valueOf(ultimoAno - 3) + " - " + String.valueOf(ultimoAno));
								model.addSeries(qualis);
								salvou = true;
								qualis = new ChartSeries();
								qualis.set("A1", 0);
								qualis.set("A2", 0);
								qualis.set("B1", 0);
								qualis.set("B2", 0);
								qualis.set("B3", 0);
								qualis.set("B4", 0);
								qualis.set("B5", 0);
								qualis.set("C", 0);
								qualis.set("Sem conceito", 0);
								
								qualis.set(producaoAcademica[1], getQuantidadeProducaoQualisAnos(this.listaSimples,
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
							qualis.set(ultimaNota, getQuantidadeProducaoQualisAnos(this.listaSimples,
									producaoAcademica[0], producaoAcademica[1]));

						}

						contador++;
					}
				} else if (anoAtual % 4 == 0 && !resultadoAno) {
					qualis.setLabel(String.valueOf(anoAtual - 3) + " - " + String.valueOf(anoAtual));
					model.addSeries(qualis);
					qualis = new ChartSeries();
					qualis.set("A1", 0);
					qualis.set("A2", 0);
					qualis.set("B1", 0);
					qualis.set("B2", 0);
					qualis.set("B3", 0);
					qualis.set("B4", 0);
					qualis.set("B5", 0);
					qualis.set("C", 0);
					qualis.set("Sem conceito", 0);
				}
			}
		}
		if ((anoAtual - 1) % 4 == 0) {
			qualis.setLabel(String.valueOf(anoAtual - 4) + " - " + String.valueOf(anoAtual - 1));
		} else {
			qualis.setLabel(String.valueOf(anoAtual - 1));
		}
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
		this.producaoAcademicaDaoImpl = new ProducaoAcademicaDaoImpl();
		this.listaSimples = new ArrayList<Object[]>();
		this.anoAtual = GregorianCalendar.getInstance();
		this.anoInicioQualis = "";
		this.anoFimQualis = "";
		this.anoTempInicioQualis = "";
		this.anoTempFimQualis = "";
		this.anoInicioProd = "";
		this.anoFimProd = "";
		this.anoTempInicioProd = "";
		this.anoTempFimProd = "";
		this.anoInicioMeuQualis = "";
		this.anoFimMeuQualis = "";
		this.anoTempInicioMeuQualis = "";
		this.anoTempFimMeuQualis = "";
		this.anoInicioMeuProd = "";
		this.anoFimMeuProd = "";
		this.anoTempInicioMeuProd = "";
		this.anoTempFimMeuProd = "";
		this.user = new Seguranca().getUsuarioLogado();
		this.alunoDao = new AlunoDaoImpl();
		this.docenteDao = new DocenteDaoImpl();

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

	public List<Object[]> getListaSimples() {
		return listaSimples;
	}

	public void setListaSimples(List<Object[]> listaSimples) {
		this.listaSimples = listaSimples;
	}

	public String getAnoInicio() {
		return anoInicioQualis;
	}

	public void setAnoInicio(String anoInicio) {
		this.anoInicioQualis = anoInicio;
	}

	public String getAnoFim() {
		return anoFimQualis;
	}

	public void setAnoFim(String anoFim) {
		this.anoFimQualis = anoFim;
	}

	public String getAnoInicioProd() {
		return anoInicioProd;
	}

	public void setAnoInicioProd(String anoInicioProd) {
		this.anoInicioProd = anoInicioProd;
	}

	public String getAnoFimProd() {
		return anoFimProd;
	}

	public void setAnoFimProd(String anoFimProd) {
		this.anoFimProd = anoFimProd;
	}

	public String getAnoInicioQualis() {
		return anoInicioQualis;
	}

	public void setAnoInicioQualis(String anoInicioQualis) {
		this.anoInicioQualis = anoInicioQualis;
	}

	public String getAnoFimQualis() {
		return anoFimQualis;
	}

	public void setAnoFimQualis(String anoFimQualis) {
		this.anoFimQualis = anoFimQualis;
	}

	public Calendar getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(Calendar anoAtual) {
		this.anoAtual = anoAtual;
	}

	public String getGraficoFiltroBarQualisMeu() {
		return graficoFiltroBarQualisMeu;
	}

	public void setGraficoFiltroBarQualisMeu(String graficoFiltroBarQualisMeu) {
		this.graficoFiltroBarQualisMeu = graficoFiltroBarQualisMeu;
	}

	public String getAnoInicioMeuQualis() {
		return anoInicioMeuQualis;
	}

	public void setAnoInicioMeuQualis(String anoInicioMeuQualis) {
		this.anoInicioMeuQualis = anoInicioMeuQualis;
	}

	public String getAnoFimMeuQualis() {
		return anoFimMeuQualis;
	}

	public void setAnoFimMeuQualis(String anoFimMeuQualis) {
		this.anoFimMeuQualis = anoFimMeuQualis;
	}

	public String getAnoTempInicioMeuQualis() {
		return anoTempInicioMeuQualis;
	}

	public void setAnoTempInicioMeuQualis(String anoTempInicioMeuQualis) {
		this.anoTempInicioMeuQualis = anoTempInicioMeuQualis;
	}

	public String getAnoTempFimMeuQualis() {
		return anoTempFimMeuQualis;
	}

	public void setAnoTempFimMeuQualis(String anoTempFimMeuQualis) {
		this.anoTempFimMeuQualis = anoTempFimMeuQualis;
	}

	public BarChartModel getGraficoBarraMeu() {
		return graficoBarraMeu;
	}

	public void setGraficoBarraMeu(BarChartModel graficoBarraMeu) {
		this.graficoBarraMeu = graficoBarraMeu;
	}

	public boolean isDiretor() {
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if (iterator.next().getAuthority().equals("ROLE_DIRETOR")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public boolean isAluno() {
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if (iterator.next().getAuthority().equals("ROLE_ALUNO")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public boolean isProfessor() {
		Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
		if (iterator.next().getAuthority().equals("ROLE_PROFESSOR")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public List<Object[]> getListaSimplesMeu() {
		return listaSimplesMeu;
	}

	public void setListaSimplesMeu(List<Object[]> listaSimplesMeu) {
		this.listaSimplesMeu = listaSimplesMeu;
	}

	public String getGraficoFiltroLineMeu() {
		return graficoFiltroLineMeu;
	}

	public void setGraficoFiltroLineMeu(String graficoFiltroLineMeu) {
		this.graficoFiltroLineMeu = graficoFiltroLineMeu;
	}

	public String getAnoInicioMeuProd() {
		return anoInicioMeuProd;
	}

	public void setAnoInicioMeuProd(String anoInicioMeuProd) {
		this.anoInicioMeuProd = anoInicioMeuProd;
	}

	public String getAnoFimMeuProd() {
		return anoFimMeuProd;
	}

	public void setAnoFimMeuProd(String anoFimMeuProd) {
		this.anoFimMeuProd = anoFimMeuProd;
	}

	public String getAnoTempInicioMeuProd() {
		return anoTempInicioMeuProd;
	}

	public void setAnoTempInicioMeuProd(String anoTempInicioMeuProd) {
		this.anoTempInicioMeuProd = anoTempInicioMeuProd;
	}

	public String getAnoTempFimMeuProd() {
		return anoTempFimMeuProd;
	}

	public void setAnoTempFimMeuProd(String anoTempFimMeuProd) {
		this.anoTempFimMeuProd = anoTempFimMeuProd;
	}

	public List<Date> getListaDatasMeu() {
		return listaDatasMeu;
	}

	public void setListaDatasMeu(List<Date> listaDatasMeu) {
		this.listaDatasMeu = listaDatasMeu;
	}

	public LineChartModel getGraficoMeu() {
		return graficoMeu;
	}

	public void setGraficoMeu(LineChartModel graficoMeu) {
		this.graficoMeu = graficoMeu;
	}

}
