package br.com.shm.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JFPadrao extends JFrame {
	
	private Integer width = 1200;
	private Integer height = 600;
	private JPPadrao tela;
	private String versao = "V1.0";
	private final String ICONE = "img\\Shm_20.png";
	
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
		
		ImageIcon iconeApp = new ImageIcon(ICONE);
		iconeApp = new ImageIcon( iconeApp.getImage( ).getScaledInstance( 20, 20,
				iconeApp.getImage( ).SCALE_DEFAULT ) );
		
		this.setIconImage(iconeApp.getImage());
		
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
