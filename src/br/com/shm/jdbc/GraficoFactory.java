package br.com.shm.jdbc;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoFactory {
	
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
