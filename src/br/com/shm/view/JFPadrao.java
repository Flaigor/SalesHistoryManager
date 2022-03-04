package br.com.shm.view;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class JFPadrao extends JFrame {
	
	private Integer larguraFrame = 1000;
	private Integer comprimentoFrame = 500;
	private JPPadrao tela;
	
	public static void main( String[ ] args )
	{
		EventQueue.invokeLater( new Runnable( )
		{
			public void run( )
			{
				JFPadrao frame = new JFPadrao( );
			}
		} );
	}
	
	public JFPadrao( )
	{
		this.setVisible( true );
		this.setSize( larguraFrame, comprimentoFrame );
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( null );

		this.addWindowListener( new WindowAdapter( )
		{
			@Override
			public void windowClosing( WindowEvent e )
			{
				if ( tela != null )
				{
					tela.finaliza( );
				}
				System.exit( 0 );
			}
		} );
	}

}
