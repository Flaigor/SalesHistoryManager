package br.com.shm.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;

public class JPLogin extends JPPadrao {

	public JPLogin( JFPadrao frame )
	{
		montaTelaLogin( frame );
	}
	
	public void montaTelaLogin( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width - 16;
		height = frame.getBounds( ).height - 39;
		this.setBounds( 0, 0, width, height );
		
		JButton btnLogin = new JButton( "Login" );
		btnLogin.setBounds( width/2 - 50 , height - 100 , 120, 30 );
		
		JTextField tfUsuario = new JTextField("Usuário");
		tfUsuario.setBounds( width/3 , height/3 , 300, 30 );
		
		JTextField tfSenha = new JTextField("Senha");
		tfSenha.setBounds( width/3 , height/2 , 300, 30 );
		
		add(btnLogin);
		add(tfUsuario);
		add(tfSenha);
		
		frame.repaint();
	}
}
