package br.com.shm.jdbc;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class GraficoFactory {

	public ChartPanel  GeraGraficoPizza() {
		DefaultPieDataset<String> pizza = new DefaultPieDataset<String>();
		pizza.setValue("Brasil", 5);
		pizza.setValue("Alemanha", 4);
		pizza.setValue("It�lia", 4);
		pizza.setValue("Argentina", 2);
		pizza.setValue("Uruguai", 2);
		pizza.setValue("Inglaterra", 1);
		pizza.setValue("Espanha", 1);
		pizza.setValue("Fran�a", 1);
		
		JFreeChart grafico = ChartFactory.createPieChart("Campe�es Mundiais", pizza, true, true, false);
		
		PiePlot<?> fatia = (PiePlot<?>) grafico.getPlot();
		fatia.setSectionPaint("Brasil", Color.YELLOW);
		fatia.setSectionPaint("Alemanha", Color.BLACK);
		fatia.setSectionPaint("It�lia", Color.GREEN);
		fatia.setSectionPaint("Argentina", Color.WHITE);
		fatia.setSectionPaint("Uruguai", Color.BLUE);
		fatia.setSectionPaint("Inglaterra", Color.MAGENTA);
		fatia.setSectionPaint("Espanha", Color.RED);
		fatia.setSectionPaint("Fran�a", Color.ORANGE);
		
		ChartPanel painel = new ChartPanel(grafico);
		
		return painel;
	}
	
	public ChartPanel GeraGraficoTesteBarra() {
		DefaultCategoryDataset barra = new DefaultCategoryDataset();
		barra.setValue(1400, "China", "");
		barra.setValue(1200, "�ndia", "");
		barra.setValue(320, "EUA", "");
		barra.setValue(210, "Brasil", "");
		barra.setValue(115, "Jap�o", "");
		barra.setValue(30, "Austr�lia", "");
		barra.setValue(18, "Portugal", "");
		
		JFreeChart grafico = ChartFactory.createBarChart("Popula��o Mundial", "Pa�ses", "Milh�es de Habitantes", barra, PlotOrientation.VERTICAL, true, true, false);
		
		CategoryPlot barraItem = grafico.getCategoryPlot();
		barraItem.getRenderer().setSeriesPaint(0, Color.RED);
		barraItem.getRenderer().setSeriesPaint(1, Color.BLACK);
		barraItem.getRenderer().setSeriesPaint(2, Color.BLUE);
		barraItem.getRenderer().setSeriesPaint(3, Color.YELLOW);
		barraItem.getRenderer().setSeriesPaint(4, Color.WHITE);
		barraItem.getRenderer().setSeriesPaint(5, Color.CYAN);
		barraItem.getRenderer().setSeriesPaint(6, Color.GREEN);
		
		ChartPanel painel = new ChartPanel(grafico);
		
		return painel;
	}
	
	public ChartPanel GeraGraficoBarra(List<String> barras, List<Integer> valores, String titulo, String linhaX, String linhaY) {
		DefaultCategoryDataset barra = new DefaultCategoryDataset();
		
		for(int i = 0; i < barras.size(); i++)
		{
			barra.setValue(valores.get(i), barras.get(i), "");
		}
		
		JFreeChart grafico = ChartFactory.createBarChart( titulo, linhaY, linhaX, barra, PlotOrientation.VERTICAL, true, true, false);
		
		ChartPanel painel = new ChartPanel(grafico);
		
		return painel;
	}
	
	
}
