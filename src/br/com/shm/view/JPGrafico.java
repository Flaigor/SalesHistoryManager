package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import org.jfree.chart.ChartPanel;

import br.com.shm.dao.HistoricoDAO;
import br.com.shm.jdbc.GraficoFactory;
import br.com.shm.jdbc.PrevisaoFactory;

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
		
		/*
		btnPrevisao.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				PrevisaoFactory pf = new PrevisaoFactory();
				HistoricoDAO dao = new HistoricoDAO();
				
				List<Integer> lp = new ArrayList<Integer>();
				
				List<Integer> laux = new ArrayList<Integer>();
				
				for(int i = 3; i >= 0; i--)
				{
					laux = dao.getListPrevisao(now.getYear() - i);
					for(Integer j : laux)
					{
						lp.add(j);
					}
					laux.clear();
				}
				
				for(int i = lp.size(); i <= 47; i++)
				{
					lp.add(0);
				}
				
				pf.previsaoAno(lp, now.getMonthValue() + 1);
							
				for(int i = lp.size() - 12; i < lp.size(); i++)
				{
					laux.add(lp.get(i));
				}
				
				GraficoFactory gf = new GraficoFactory();
				
				frame.remove(JPGrafico.this);
				frame.setTela(new JPGrafico(frame, admin, gf.GeraGraficoBarra(dao.getMeses(), laux, "Previsão de " + now.getYear(), "Numero de Vendas", "Mês")), false );
				
			}
		} );
		*/
	}
	
}
