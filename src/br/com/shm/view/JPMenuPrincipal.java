package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;

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
		
		JButton btnHistorico = new JButton("Histórico");
		btnHistorico.setBounds( 140 , height - 80 , width - 300, 30 );
		
		JButton btnlogOff = new JButton("Logoff");
		btnlogOff.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnUsers = new JButton("Usuários");
		btnUsers.setBounds( width - 150 , 50 , 120, 30 );
		
		JButton btnNovaVenda = new JButton("Nova Venda!");
		btnNovaVenda.setBounds( 140 , 10 , width - 300, 70 );
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnAgradecimentos = new JButton("Menções");
		btnAgradecimentos.setBounds( width - 150 , height - 80 , 120, 30 );
		
		JScrollPane scrollClientes = new JScrollPane();
		scrollClientes.setBounds( 10 , 90 , width - 40, height - 180 );
		
		add(btnCliente);
		add(btnPoduto);
		add(btnVenda);
		add(btnlogOff);
		add(btnUsers);
		add(btnNovaVenda);
		add(btnHistorico);
		add(btnAgradecimentos);
		add(scrollClientes);
		
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
		
		btnNovaVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPMenuPrincipal.this);
				frame.setTela(new JPNovaVenda(frame), false);	
			}
		} );
		
		
	}
}
