package br.com.shm.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.shm.dao.VendasDAO;
import br.com.shm.model.Venda;

public class JPHistorico extends JPPadrao {
	
	private JTable tResultado;
	private String[] colunas = {"Coluna 1", "Coluna 2", "Coluna 3"};
	private String[] stTipo = {"Cliente", "Venda", "Produto"};
	private String[] stClientePesquisa = {"Comprador", "Devedor"};
	private String[] stVendaPesquisa = {"Por Mês", "Por Ano", "Média do valor"};
	private String[] stProdutoPesquisa = {"Quantidade vendida", "Rentabilidade",};
	private String[] stOrdem = {"Crescente", "Decrescente"};
	
	public JPHistorico( JFPadrao frame )
	{
		montaTelaHistorico( frame );
	}
	
	public void listar(String tipo, String pesquisa, String ordem)
	{
		
	}
	
	public void montaTelaHistorico( JFPadrao frame )
	{
		limpaTela( );
		width = frame.getBounds( ).width;
		height = frame.getBounds( ).height;
		this.setBounds( 0, 0, width, height );
		
		JLabel labelTipo = new JLabel("Tipo: ");
		labelTipo.setBounds( 10, 10, 70, 30);
		
		JComboBox cbTipo = new JComboBox(stTipo);
		cbTipo.setBounds( 80, 10, 120, 30);
		
		JLabel labelPesquisa = new JLabel("Pesquisa: ");
		labelPesquisa.setBounds( 250, 10, 70, 30);
		
		JComboBox cbPesquisa = new JComboBox(stClientePesquisa);
		cbPesquisa.setBounds( 320, 10, 120, 30);
		
		JLabel labelOrden = new JLabel("Ordem: ");
		labelOrden.setBounds( 490, 10, 70, 30);
		
		JComboBox cbOrden = new JComboBox(stOrdem);
		cbOrden.setBounds( 560, 10, 120, 30);
		
		JTextField tfPesquisaLivre = new JTextField();
		tfPesquisaLivre.setBounds( 10, 50, width - 40, 30);
		
		tResultado = new JTable(new DefaultTableModel(null, colunas));
		JScrollPane scrollResultado = new JScrollPane(tResultado);
		
		tResultado.setBounds( 10 , 90 , width - 40, height - 180 );
		scrollResultado.setBounds( 10 , 90 , width - 40, height - 180 );
		
		JLabel labelResultado = new JLabel("Aqui");
		labelResultado.setBounds( 670 , height - 80 , 120, 30 );
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds( width - 150 , 10 , 120, 30 );
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds( 10 , height - 80 , 120, 30 );
		
		JButton btnPqsPerso = new JButton("Personalizada");
		btnPqsPerso.setBounds( 230 , height - 80 , 120, 30 );
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds( 450 , height - 80 , 120, 30 );
		
		add(labelTipo);
		add(cbTipo);
		add(labelPesquisa);
		add(cbPesquisa);
		add(labelOrden);
		add(cbOrden);
		add(tfPesquisaLivre);
		add(scrollResultado);
		add(labelResultado);
		add(btnVoltar);
		add(btnPesquisar);
		add(btnPqsPerso);
		add(btnLimpar);
		
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
