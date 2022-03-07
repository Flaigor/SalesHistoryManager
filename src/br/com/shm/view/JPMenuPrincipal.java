package br.com.shm.view;

import javax.swing.JButton;

public class JPMenuPrincipal extends JPPadrao {

	public JPMenuPrincipal( JFPadrao frame )
	{
		montaTelaLogin( frame );
	}
	
	public void montaTelaLogin( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.setBounds( 1 , 1 , 120, 30 );
		
		JButton btnPoduto = new JButton("Produto");
		btnPoduto.setBounds( 1 , 40 , 120, 30 );
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.setBounds( 1 , 80 , 120, 30 );
		
		JButton btnMudarSenha = new JButton("Mudar Senha");
		btnMudarSenha.setBounds( width - 130 , 1 , 120, 30 );
		
		add(btnCliente);
		add(btnPoduto);
		add(btnVenda);
		add(btnMudarSenha);
		
		frame.repaint();
	}
}
