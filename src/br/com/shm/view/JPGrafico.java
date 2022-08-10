package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jfree.chart.ChartPanel;

public class JPGrafico extends JPPadrao {
	
	
	public JPGrafico( JFPadrao frame, boolean admin, ChartPanel grafico  )
	{
		montaTelaGrafico( frame, admin, grafico );
	}
	
	public void montaTelaGrafico( JFPadrao frame, boolean admin, ChartPanel grafico )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		grafico.setBounds(10, 50, width - 40, height - 100);
		
		add(btnVoltar);
		add(grafico);
		
		frame.repaint();
	
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPGrafico.this);
				frame.setTela(new JPHistorico(frame, admin), false);		
			}
		} );
	}
	
}
