package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JPMenuPrincipal extends JPPadrao {

	public JPMenuPrincipal( JFPadrao frame )
	{
		montaTelaMenuPrincipal( frame );
	}
	
	public void montaTelaMenuPrincipal( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JButton btnCliente = new JButton("Clientes");
		btnCliente.setBounds( 10 , 10 , 120, 30 );
		
		JButton btnPoduto = new JButton("Produtos");
		btnPoduto.setBounds( 10 , 50 , 120, 30 );
		
		JButton btnVenda = new JButton("Vendas");
		btnVenda.setBounds( 10 , 90 , 120, 30 );
		
		JButton btnlogOff = new JButton("Logoff");
		btnlogOff.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnMudarSenha = new JButton("Mudar Senha");
		btnMudarSenha.setBounds( width - 150 , 50 , 120, 30 );
		
		JButton btnNovaVenda = new JButton("Nova Venda!");
		btnNovaVenda.setBounds( 140 , 10 , width - 300, 70 );
		
		add(btnCliente);
		add(btnPoduto);
		add(btnVenda);
		add(btnlogOff);
		add(btnMudarSenha);
		add(btnNovaVenda);
		
		frame.repaint();
		
		btnCliente.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPMenuPrincipal.this);
				frame.setTela(new JPCliente(frame), false);		
			}
		} );
		
		btnPoduto.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPMenuPrincipal.this);
				frame.setTela(new JPProduto(frame), false);		
			}
		} );
		
		btnlogOff.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPMenuPrincipal.this);
				frame.setTela(new JPLogin(frame), false);		
			}
		} );
	}
}
