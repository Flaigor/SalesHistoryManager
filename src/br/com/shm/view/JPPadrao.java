package br.com.shm.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public abstract class JPPadrao extends JPanel {
	
	protected Integer width = 1200 - 16;
	protected Integer height = 600 - 39;
	protected Color color = new Color( 176, 224, 230 );

	public void limpaTela( )
	{
		revalidate( );
		repaint( );
		setLayout( null );
		setBackground( this.color );
	}
	
	public void finaliza( )
	{
		
	}
}
