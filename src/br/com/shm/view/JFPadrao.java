package br.com.shm.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class JFPadrao extends JFrame {
	
	private Integer width = 1200;
	private Integer height = 600;
	private JPPadrao tela;
	private String versao = "V1.0";
	
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
		this.setSize( width, height );
		this.setResizable(false);
		this.setLocationRelativeTo( null );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( null );
		this.setTitle("Sales History Manager " + versao);
		
		setTela( new JPLogin( this ), true );

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
	
	public JPPadrao getTela( )
	{
		return tela;
	}
	
	public void setTela( JPPadrao tela, boolean finaliza )
	{
		if ( finaliza && this.tela != null )
		{
			this.tela.finaliza( );
		}

		this.tela = tela;
		this.add( tela );
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

}
