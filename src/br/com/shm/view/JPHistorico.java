package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JPHistorico extends JPPadrao {
	
	public JPHistorico( JFPadrao frame )
	{
		montaTelaHistorico( frame );
	}
	
	public void montaTelaHistorico( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		add(btnVoltar);
		
		frame.repaint();
		
		btnVoltar.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPHistorico.this);
				frame.setTela(new JPMenuPrincipal(frame), false);		
			}
		} );
	}

}
