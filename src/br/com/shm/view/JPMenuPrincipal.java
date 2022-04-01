package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.shm.dao.VendasDAO;
import br.com.shm.model.Venda;

public class JPMenuPrincipal extends JPPadrao {

	private JTable tVendas;
	private DefaultTableModel dadosVen;
	private String[] colunas = {"Nome", "Data", "Descição", "Valor", "Pago"};
	
	public JPMenuPrincipal( JFPadrao frame )
	{
		montaTelaMenuPrincipal( frame );
	}
	
	public void listar()
	{
		VendasDAO dao = new VendasDAO();
		List<Venda> listaVenda = dao.listarVendaJoinClienteNaoPagas();
		dadosVen = (DefaultTableModel) tVendas.getModel();
		dadosVen.setNumRows(0);
		
		for(Venda v: listaVenda)
		{
			dadosVen.addRow(new Object[]{
				v.getComprador().getNome(),
				v.getDataVenda(),
				v.getDescricao(),
				v.getValor(),
				"Não"
			});
		}
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
		
		tVendas = new JTable(new DefaultTableModel(null, colunas));
		JScrollPane scrollClientes = new JScrollPane(tVendas);
		
		tVendas.setBounds( 10 , 90 , width - 40, height - 180 );
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
		
		listar();
		
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
		
		btnVenda.addActionListener( new ActionListener( )
		{
			public void actionPerformed( ActionEvent e )
			{
				frame.remove(JPMenuPrincipal.this);
				frame.setTela(new JPVenda(frame), false);	
			}
		} );
	}
}
