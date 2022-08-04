package br.com.shm.jdbc;

import javax.swing.JFrame;

public class TestaGrafico extends JFrame  {
	
	public static void main(String[] args) {
		new TestaGrafico();
	}
	
	public TestaGrafico()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Teste Gráfico");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		montaTestaGrafico();
		setVisible(true);
	}
	
	public void montaTestaGrafico()
	{
		GraficoFactory gf = new GraficoFactory();
		
		add(gf.GeraGraficoBarra());
	}

}